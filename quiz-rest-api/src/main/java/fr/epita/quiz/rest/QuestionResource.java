package fr.epita.quiz.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
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
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.Role;
import fr.epita.quiz.filter.JWTTokenNeeded;
import fr.epita.quiz.services.ChoiceDAO;
import fr.epita.quiz.services.QuestionDAO;

@Path("/questions/")

public class QuestionResource {
	
	
	@Inject
	QuestionDAO questionDao;
	
	@Inject
	ChoiceDAO choiceDao;
	
	private static final Logger LOGGER = LogManager.getLogger(QuestionResource.class);
	
	@POST
	@Path("/")
	//@JWTTokenNeeded(Permissions = Role.ADMIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createQuestion(@RequestBody Question question) throws URISyntaxException {
		questionDao.create(question);
		for (Choice choice : question.getChoices()) {
			choice.setQuestion(question);
			choiceDao.update(choice);
		}
		LOGGER.info("received creation order for question : {}",  question);
		return Response.created(new URI("questions/"  + String.valueOf(question.getQuestionId()))).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getQuestionById(@PathParam("id") int id) {		
		Question question = questionDao.getById(id, Question.class);
		return Response.ok(question).build();
	}

	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchQuestions(@QueryParam("questionContent") String questionContent) {
		Question question = new Question();
		question.setQuestionContent(questionContent);
		List<Question> results = questionDao.search(question);
		return Response.ok(results).build();
	}
	
	@DELETE
	@Path("/delete/{id}")
	@JWTTokenNeeded(Permissions = Role.ADMIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteQuestion(@PathParam("id") int id) {
		Question question = questionDao.getById(id, Question.class);
		if (question == null)
		{
            return Response.status(Status.NO_CONTENT).build();
		}
		questionDao.delete(question);
        return Response.status(Status.OK).build();
	}
	
	@PUT
	@Path("/update/{id}")
	@JWTTokenNeeded(Permissions = Role.ADMIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateQuestion(@PathParam("id") int id, @QueryParam("questionContent") String questionContent) {
		Question question = questionDao.getById(id, Question.class);
		question.setQuestionContent(questionContent);
		questionDao.update(question);
        return Response.status(Status.OK).build();
	}
}
