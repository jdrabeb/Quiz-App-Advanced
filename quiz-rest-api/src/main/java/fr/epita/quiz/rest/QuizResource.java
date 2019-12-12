package fr.epita.quiz.rest;

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
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.Quiz;
import fr.epita.quiz.datamodel.Role;
import fr.epita.quiz.filter.JWTTokenNeeded;
import fr.epita.quiz.services.QuestionDAO;
import fr.epita.quiz.services.QuizDAO;

@Path("/quiz/")
public class QuizResource {

	@Inject
	QuizDAO quizDao;
	
	@Inject
	QuestionDAO questionDao;
	
	private static final Logger LOGGER = LogManager.getLogger(QuestionResource.class);

	@POST
	@Path("/")
	@JWTTokenNeeded(Permissions = Role.ADMIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createQuiz(@RequestBody Quiz quiz) throws URISyntaxException {
		quizDao.create(quiz);
		for (Question question : quiz.getQuestions()) {
			question.setQuiz(quiz);
			questionDao.update(question);
		}
		LOGGER.info("received creation order for quiz : {}",  quiz);
		return Response.created(new URI("quiz/"  + String.valueOf(quiz.getQuizId()))).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getQuestionById(@PathParam("id") int id) {		
		Quiz quiz = quizDao.getById(id, Quiz.class);
		return Response.ok(quiz).build();
	}

	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchQuestions(@QueryParam("title") String title) {
		Quiz quiz= new Quiz();
		quiz.setTitle(title);
		List<Quiz> results = quizDao.search(quiz);
		return Response.ok(results).build();
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteQuiz(@PathParam("id") int id) {
		Quiz quiz = new Quiz();
		quiz = quizDao.getById(id, Quiz.class);
		if (quiz == null)
		{
            return Response.status(Status.NO_CONTENT).build();
		}
		quizDao.delete(quiz);
        return Response.status(Status.OK).build();
	}
	
	@PUT
	@Path("/update/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateQuiz(@PathParam("id") int id, @QueryParam("title") String title) {
		Quiz quiz = quizDao.getById(id, Quiz.class);
		quiz.setTitle(title);
		quizDao.update(quiz);
        return Response.status(Status.OK).build();
	}

}

