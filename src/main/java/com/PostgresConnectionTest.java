package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgresConnectionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method 
		 try (Connection conn = DriverManager.getConnection(
	                "jdbc:postgresql://localhost:5432/postgres", "postgres", "admin123")) {

	            if (conn != null) {
	            	Statement stmt=conn.createStatement();
	            	ResultSet resultset=stmt.executeQuery("SELECT federated_username FROM public.federated_identity");
	            	while(resultset.next()) {
	            		System.out.println(resultset.getString(1));
	            	}
	                System.out.println("Connected to the database!");
	            } else {
	                System.out.println("Failed to make connection!");
	            }

	        } catch (SQLException e) {
	            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	    }

	}


