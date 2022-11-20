package com;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.LogFactory;
import org.keycloak.models.ClientSessionContext;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.ProtocolMapperModel;
import org.keycloak.models.UserSessionModel;
import org.keycloak.protocol.oidc.OIDCLoginProtocol;
import org.keycloak.protocol.oidc.mappers.AbstractOIDCProtocolMapper;
import org.keycloak.protocol.oidc.mappers.OIDCAccessTokenMapper;
import org.keycloak.protocol.oidc.mappers.OIDCAttributeMapperHelper;
import org.keycloak.protocol.oidc.mappers.OIDCIDTokenMapper;
import org.keycloak.protocol.oidc.mappers.UserInfoTokenMapper;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dao.FederatedIdentityDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Client;
import com.model.Federated_Identity;
import com.model.IDPToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomOIDCProtocolMapper extends AbstractOIDCProtocolMapper implements OIDCAccessTokenMapper, OIDCIDTokenMapper, UserInfoTokenMapper {

    public static final String PROVIDER_ID = "oidc-customprotocolmapper";
    
 // creating a logger
    Logger logger
        = LoggerFactory.getLogger(CustomOIDCProtocolMapper.class);

    private static final List<ProviderConfigProperty> configProperties = new ArrayList<ProviderConfigProperty>();
    
    @Autowired
    FederatedIdentityDAO federatedIdentityDAO;

    /**
     * Maybe you want to have config fields for your Mapper
     */
    /*
    static {
        ProviderConfigProperty property;
        property = new ProviderConfigProperty();
        property.setName(ProtocolMapperUtils.USER_ATTRIBUTE);
        property.setLabel(ProtocolMapperUtils.USER_MODEL_ATTRIBUTE_LABEL);
        property.setHelpText(ProtocolMapperUtils.USER_MODEL_ATTRIBUTE_HELP_TEXT);
        property.setType(ProviderConfigProperty.STRING_TYPE);
        configProperties.add(property);

        property = new ProviderConfigProperty();
        property.setName(ProtocolMapperUtils.MULTIVALUED);
        property.setLabel(ProtocolMapperUtils.MULTIVALUED_LABEL);
        property.setHelpText(ProtocolMapperUtils.MULTIVALUED_HELP_TEXT);
        property.setType(ProviderConfigProperty.BOOLEAN_TYPE);
        configProperties.add(property);

    }
     */
    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return configProperties;
    }

    @Override
    public String getDisplayCategory() {
        return TOKEN_MAPPER_CATEGORY;
    }

    @Override
    public String getDisplayType() {
        return "Custom Mapper";
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }

    @Override
    public String getHelpText() {
        return "some help text";
    }

//    public IDToken transformAccessToken(IDToken token, ProtocolMapperModel mappingModel, KeycloakSession keycloakSession,
//                                            UserSessionModel userSession, ClientSessionContext clientSessionCtx) {
////IDToken token, ProtocolMapperModel mappingModel, UserSessionModel userSession, KeycloakSession keycloakSession, ClientSessionContext clientSessionCtx
//        token.getOtherClaims().put("stackoverflowCustomToken", "stackoverflow");
////        token.getOtherClaims().
//        // call profile API and enrich with permission claims
////        setClaim(token, mappingModel, userSession);
//        setClaim(token, mappingModel, userSession, keycloakSession,clientSessionCtx);
//        return token;
//    }

    public AccessToken transformAccessToken(AccessToken token, ProtocolMapperModel mappingModel, KeycloakSession keycloakSession,
                                            UserSessionModel userSession, ClientSessionContext clientSessionCtx) {
    	
    	logger.info("Entering TransformAccessToken in CustomOIDCProtocolMapper");
    	
    	Federated_Identity federatedIdentity=getFederatedIdentityById(userSession.getUser().getId());
    	
    	if(federatedIdentity !=null) {
    		logger.info("Federated Identity Retrieved Successfully from Database");
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		IDPToken idpToken = mapper.readValue(federatedIdentity.getToken(), IDPToken.class);
    	    String jwtBody=getJwtDecodedToken(idpToken.getId_token());
    	    HashMap<String,Object> result =
    	    	       new ObjectMapper().readValue(jwtBody, HashMap.class);
    	    logger.info("Consent Id "+(String)result.get("consentId"));
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}


        setClaim(token, mappingModel, userSession, keycloakSession, clientSessionCtx);
        return token;
    	} else {
    		logger.info("Federated Identity is not found in database");
    	return null;
    	}
    }
    
    private Federated_Identity getFederatedIdentityById(String id) {
		// TODO Auto-generated method stub
    	AnnotationConfigApplicationContext context=MapperContext.getApplicationContext();

		FederatedIdentityDAO federatedIdentityDAO = context.getBean(FederatedIdentityDAO.class);
		return federatedIdentityDAO.getFederatedIdentityByUserId(id);
		
	}
    
    private String getJwtDecodedToken(String jwtToken){
    	
    	
    	logger.info("------------ Decode JWT ------------");
        String[] split_string = jwtToken.split("\\.");
        String base64EncodedHeader = split_string[0];
        String base64EncodedBody = split_string[1];
        String base64EncodedSignature = split_string[2];
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(base64EncodedBody));
        String header = new String(decoder.decode(base64EncodedHeader));
        logger.info("------------ JWT Token Decoded Successfully ------------");
    	
    	return payload;
    }

	public void getFederatedIdentityById(){
    	
		AnnotationConfigApplicationContext context=MapperContext.getApplicationContext();

		FederatedIdentityDAO personDAO = context.getBean(FederatedIdentityDAO.class);
		

		logger.info("List of Federated Identity is:");

		for (Federated_Identity p : personDAO.getAllFederatedIdentity()) {
			logger.info("Federated Identity" +p);
		}
		
		for (Client cl : personDAO.getAllClients()) {
			logger.info("Federated Client" +cl.getId());
		}
		


		context.close();
    }
    

//    public static ProtocolMapperModel create(String name, boolean accessToken, boolean idToken, boolean userInfo) {
//        ProtocolMapperModel mapper = new ProtocolMapperModel();
//        mapper.setName(name);
//        mapper.setProtocolMapper(PROVIDER_ID);
//        mapper.setProtocol(OIDCLoginProtocol.LOGIN_PROTOCOL);
//        Map<String, String> config = new HashMap<String, String>();
//        config.put("id.token.claim", "true");
//        config.put("access.token.claim", "true");
//        mapper.setConfig(config);
//        return mapper;
//    }

    public static ProtocolMapperModel create(String name,
                                             boolean accessToken, boolean idToken, boolean userInfo) {
        ProtocolMapperModel mapper = new ProtocolMapperModel();
        mapper.setName(name);
        mapper.setProtocolMapper(PROVIDER_ID);
        mapper.setProtocol(OIDCLoginProtocol.LOGIN_PROTOCOL);
        Map<String, String> config = new HashMap<String, String>();
//        config.put(OIDCAttributeMapperHelper.TOKEN_CLAIM_NAME, hardcodedName);
//        config.put(CLAIM_VALUE, hardcodedValue);
//        config.put(OIDCAttributeMapperHelper.JSON_TYPE, claimType);
        config.put(OIDCAttributeMapperHelper.INCLUDE_IN_ACCESS_TOKEN, "true");
        config.put(OIDCAttributeMapperHelper.INCLUDE_IN_ID_TOKEN, "true");
        mapper.setConfig(config);
        return mapper;
    }

}
