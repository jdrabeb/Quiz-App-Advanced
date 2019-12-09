package fr.epita.quiz.datamodel;

import java.util.List;

import javax.persistence.OneToMany;

public class Quiz {
	
	@OneToMany
	private List<Question> questions;
}
