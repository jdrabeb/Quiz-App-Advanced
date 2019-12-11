package fr.epita.quiz.rest;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;

import fr.epita.quiz.datamodel.Choice;
import fr.epita.quiz.datamodel.Quiz;
import fr.epita.quiz.datamodel.Role;
import fr.epita.quiz.filter.JWTTokenNeeded;
import fr.epita.quiz.services.ChoiceDAO;

@Path("/choices/")

public class ChoiceResource {
	
	@Inject
	ChoiceDAO choiceDao;
	
	private static final Logger LOGGER = LogManager.getLogger(ChoiceResource.class);	
	
	@POST
	@Path("/")
//	@JWTTokenNeeded(Permissions = Role.ADMIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createChoice(@RequestBody Choice choice) throws URISyntaxException {
		choiceDao.create(choice);
		LOGGER.info("received creation order for choice : {}",  choice);
		return Response.created(new URI("choices/"  + String.valueOf(choice.getChoiceId()))).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getChoiceById(@PathParam("id") int id) {
		Choice choice = choiceDao.getById(id, Choice.class);
		return Response.ok(choice).build();
	}
	
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchChoices(@QueryParam("choiceContent") String choiceContent) {
		Choice choice = new Choice();
		choice.setChoiceContent(choiceContent);
		List<Choice> results = choiceDao.search(choice);
		return Response.ok(results).build();
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteChoice(@PathParam("id") int id) {
		Choice choice = new Choice();
		choice = choiceDao.getById(id, Choice.class);
		if (choice == null)
		{
            return Response.status(Status.NO_CONTENT).build();
		}
		choiceDao.delete(choice);
        return Response.status(Status.OK).build();
	}
	
	@PUT
	@Path("/update/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateChoice(@PathParam("id") int id, @QueryParam("choiceContent") String choiceContent) {
		Choice choice = choiceDao.getById(id, Choice.class);
		
		choice.setChoiceContent(choiceContent);
		choiceDao.update(choice);
        return Response.status(Status.OK).build();
	}
}
