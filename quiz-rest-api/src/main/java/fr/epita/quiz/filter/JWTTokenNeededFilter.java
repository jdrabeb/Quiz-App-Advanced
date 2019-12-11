package fr.epita.quiz.filter;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import javax.ws.rs.container.ResourceInfo;


import fr.epita.quiz.datamodel.Role;
import fr.epita.quiz.datamodel.User;
import fr.epita.quiz.services.UserDAO;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@JWTTokenNeeded
@Provider
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter implements ContainerRequestFilter {

    @Inject
    UserDAO userDao;
//    private Logger logger;
//    
	@Context
	private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
//        logger.info("#### authorizationHeader : " + authorizationHeader);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
           // logger.severe("#### invalid authorizationHeader : " + authorizationHeader);
            throw new NotAuthorizedException("Authorization header must be provided");
        }
        String token = authorizationHeader.substring("Bearer".length()).trim();
        try {
        		String username = TokenServices.getUsernameFromToken(token);
        		Role userRole = Role.valueOf(TokenServices.getRoleFromToken(token));
        		System.out.println("from token" + userRole);
        		System.err.println("from token" + username);
        		User authenticatingUser = new User(username, "", userRole);
            	List<User> searchList = userDao.search(authenticatingUser);
            	if (searchList.isEmpty())
            		throw new SecurityException("Invalid user/password");
            	String foundRole = searchList.get(0).getRole().toString();
        		if (!foundRole.equals(userRole.toString().toUpperCase()))
            		throw new SecurityException("Invalid user permissions");
                Method method =resourceInfo.getResourceMethod();
                if( method != null){
                    JWTTokenNeeded JWTContext = method.getAnnotation(JWTTokenNeeded.class);
                    Role permission =  JWTContext.Permissions();
                    if(permission != Role.GUEST ) {
                        if (!permission.equals(userRole)) {
                            throw new Exception("no roles");
                        }
                    }
                }
        	} catch (Exception e) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
    }
   
}

