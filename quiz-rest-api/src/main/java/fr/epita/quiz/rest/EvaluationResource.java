package fr.epita.quiz.rest;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestBody;

import fr.epita.quiz.datamodel.Choice;
import fr.epita.quiz.datamodel.Evaluation;
import fr.epita.quiz.datamodel.Quiz;
import fr.epita.quiz.datamodel.User;
import fr.epita.quiz.services.ChoiceDAO;
import fr.epita.quiz.services.EvaluationDAO;
import fr.epita.quiz.services.UserDAO;

@Path("/eval/")
public class EvaluationResource {

	@Inject
	EvaluationDAO evaluationDao;
	
	@Inject
	ChoiceDAO choiceDao;
	
	@Inject
	UserDAO userDao;


	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createEvaluation(@RequestBody Evaluation evaluation) throws URISyntaxException {
		evaluationDao.create(evaluation);
		for (Choice answer : evaluation.getAnswers()) {
			answer.setEvaluation(evaluation);
			choiceDao.update(answer);
		}
		User student =  evaluation.getStudent();
		student.setEvaluation(evaluation);
		userDao.update(student); 
		Quiz quiz = evaluation.getQuiz();
		quiz.setEvaluation(evaluation);
		return Response.created(new URI("choices/"  + String.valueOf(evaluation.getEvaluationId()))).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEvaluationById(@PathParam("id") int id) {
		Evaluation evaluation = evaluationDao.getById(id, Evaluation.class);
		return Response.ok(evaluation).build();
	}
	
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchEvaluations(@QueryParam("content") String content) {
		Evaluation evaluation = new Evaluation();
		List<Evaluation> results = evaluationDao.search(evaluation);
		return Response.ok(results).build();
	}

}
