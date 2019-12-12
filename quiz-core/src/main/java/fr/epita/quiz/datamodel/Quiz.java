package fr.epita.quiz.datamodel;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Quiz")
public class Quiz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "quizId")
	private Integer quizId;
	
	@Column(name = "title")
	private String title;
	
	@JsonIgnore
	@OneToOne(mappedBy = "quiz", fetch = FetchType.EAGER,
			cascade = CascadeType.REMOVE)
	private Evaluation evaluation;

	@OneToMany(mappedBy = "quiz", fetch = FetchType.EAGER)
	private List<Question> questions;
	
	public Integer getQuizId() {
		return quizId;
	}


	public void setQuizId(Integer quizId) {
		this.quizId = quizId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public List<Question> getQuestions() {
		return questions;
	}


	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}


	public Evaluation getEvaluation() {
		return evaluation;
	}


	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}
	
	
}
