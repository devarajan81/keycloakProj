package com;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MapperContext {
	private static AnnotationConfigApplicationContext context;

	public static AnnotationConfigApplicationContext getApplicationContext() {
		if (context == null) {
			context = new AnnotationConfigApplicationContext(AppConfig.class);
		}
		return context;

	}

}
