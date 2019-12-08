package fr.epita.quiz.services;


import java.util.Map;

import org.springframework.stereotype.Repository;

import fr.epita.quiz.datamodel.Choice;

@Repository
public class ChoiceDAO extends DAO<Choice>
{
	@Override
	protected String getQueryString()
	{
		return "from Choice c where c.choiceContent like :Ccontent";
	}

	@Override
	protected void fillParametersMap(Map<String,Object> map, Choice choice)
	{
		map.put("Ccontent", "%" + choice.getChoiceContent() + "%");
	}
}