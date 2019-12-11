package fr.epita.quiz.services;

import java.util.Map;

import org.springframework.stereotype.Repository;

import fr.epita.quiz.datamodel.Quiz;

@Repository
public class QuizDAO extends DAO<Quiz>
{
	@Override
	protected String getQueryString()
	{
		return "from Quiz q where q.title like :qTitle";
	}

	@Override
	protected void fillParametersMap(Map<String,Object> map, Quiz quiz)
	{
		map.put("qTitle", "%" + quiz.getTitle() + "%");
	}
}
