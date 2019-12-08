package fr.epita.quiz.tests.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.quiz.datamodel.Choice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.ChoiceDAO;
import fr.epita.quiz.services.QuestionDAO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class TestQuestion {
	
	@Inject
	QuestionDAO questionDAO;
	
	@Inject
	ChoiceDAO choiceDAO;

	@PersistenceContext
	EntityManager em;
	
	@Test
	@Transactional
	public void testCreateChoice()
	{
		Choice choice = new Choice("choice1", false);
		choiceDAO.create(choice);
		
		Assert.assertNotNull(em.find(Choice.class, choice.getChoiceId()));
	}
	
	@Test
	@Transactional
	public void testCreateQuestion() {
		List<Choice> choices = new ArrayList<Choice>();
		String firstChoiceContent = "choice1";
		Choice firstChoice = new Choice(firstChoiceContent, false);
		choiceDAO.create(firstChoice);
		choices.add(firstChoice);
		String secondChoiceContent = "choice2";
		Choice secondChoice = new Choice(secondChoiceContent, false);
		choiceDAO.create(secondChoice);
		choices.add(secondChoice);
		String questionContent = "What is Dependency Injection ?";
		Question question = new Question(questionContent, 2, choices);

		questionDAO.create(question);
		
		Assert.assertNotNull(em.find(Question.class, question.getQuestionId()));
	}

	@Test
	@Transactional
	public void testSearchChoice()
	{
		Choice choice1 = new Choice("choice1", false);
		choiceDAO.create(choice1);
		Choice choice2 = new Choice("choice2", false);
		choiceDAO.create(choice2);
		List <Choice> searchedChoices = choiceDAO.search(choice1);
		Assert.assertNotNull(searchedChoices);
	}
	
	
	
}