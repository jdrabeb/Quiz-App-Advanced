package fr.epita.quiz.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Questions")
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "questionId")
	private Integer questionId;
	
	@Column(name = "questionContent")
	private String questionContent;
	
	@Column(name = "difficulty")
	private int difficulty;
	
	@OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
	private List<Choice> choices;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "quizId")
	private Quiz quiz;

	
	public Question()
	{
	}
	
	public Question(String questionContent, int difficulty, List<Choice> choices)
	{
		this.questionContent = questionContent;
		this.difficulty = difficulty;
		this.choices = choices;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setId(Integer id) {
		this.questionId = id;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

}
