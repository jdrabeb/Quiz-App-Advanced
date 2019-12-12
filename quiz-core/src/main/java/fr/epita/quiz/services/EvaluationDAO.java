package fr.epita.quiz.services;

import java.util.Map;

import org.springframework.stereotype.Repository;

import fr.epita.quiz.datamodel.Evaluation;

@Repository
public class EvaluationDAO extends DAO<Evaluation>
{
	@Override
	protected String getQueryString()
	{
		return "from Evaluation e";
	}

	@Override
	protected void fillParametersMap(Map<String,Object> map, Evaluation evaluation)
	{
		map.put("cContent", "%" + evaluation.getStudent().getUsername() + "%");
	}
}
