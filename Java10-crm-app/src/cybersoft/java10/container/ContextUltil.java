package cybersoft.java10.container;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cybersoft.java10.config.AppConfig;

public class ContextUltil {
	public static AnnotationConfigApplicationContext getContextApp() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		return context;
	}
}
