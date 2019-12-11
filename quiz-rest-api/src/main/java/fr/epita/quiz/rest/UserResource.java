package fr.epita.quiz.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;

import fr.epita.quiz.datamodel.Role;
import fr.epita.quiz.datamodel.User;
import fr.epita.quiz.filter.JWTTokenNeeded;
import fr.epita.quiz.filter.TokenServices;
import fr.epita.quiz.services.UserDAO;
import fr.epita.quiz.util.KeyGenerator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;


@Path("/")

public class UserResource {
	
    @Context
    private UriInfo uriInfo;
	
//	@Inject
//	private KeyGenerator keyGenerator;
	
	@Inject
	UserDAO userDao;
	
	private static final Logger LOGGER = LogManager.getLogger(ChoiceResource.class);
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(@RequestBody User user) throws URISyntaxException {
    	System.out.println("creating " + user.getUsername());
		List<User> searchList = userDao.search(user);
    	if (!searchList.isEmpty())
            return Response.status(Status.CONFLICT).build();
		userDao.create(user);
		LOGGER.info("received creation order for user : {}",  user);
		return Response.created(new URI("user/"  + String.valueOf(user.getUserId()))).build();
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response aunthenticateUser(@RequestBody User user) throws URISyntaxException {
		try {
			Role userRole = authenticate(user);
			String token = TokenServices.createToken(user.getUsername(), userRole.toString());
			return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();
        } catch (Exception e) {
        	System.out.println(e);
            return Response.status(UNAUTHORIZED).build();
        }
	}

    private Role authenticate(User user) throws Exception {
    	List<User> searchList = userDao.search(user);
    	if (searchList.isEmpty() || 
    			!searchList.get(0).getUsername().equals(user.getUsername()) ||
    			!searchList.get(0).getPassword().equals(user.getPassword()))
    		throw new SecurityException("Invalid user/password");
    	return searchList.get(0).getRole();
    }
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsersById(@PathParam("id") int id) {
		User user= userDao.getById(id, User.class);
		return Response.ok(user).build();
	}
	
	@GET
	@Path("/search")
//	@JWTTokenNeeded(Permissions = Role.ADMIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchUsers(@QueryParam("username") String username) {
		User user = new User();
		user.setUsername(username);
		List<User> searchList = userDao.search(user);
		return Response.ok(searchList).build();
	}

}
