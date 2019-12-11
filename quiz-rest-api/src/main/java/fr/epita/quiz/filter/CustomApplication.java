package fr.epita.quiz.filter;

import org.glassfish.jersey.server.ResourceConfig;

public class CustomApplication extends ResourceConfig{
    public CustomApplication() {
    	packages("fr.epita.quiz.filter");
	}
	
}
