package fr.epita.quiz.services;


import java.util.Map;

import org.springframework.stereotype.Repository;

import fr.epita.quiz.datamodel.User;

@Repository
public class UserDAO extends DAO<User>
{
	@Override
	protected String getQueryString()
	{
		return "from User u where u.username like :username";
	}

	@Override
	protected void fillParametersMap(Map<String,Object> map, User user)
	{
		map.put("username", "%" + user.getUsername() + "%");
	}
}