package fr.epita.quiz.datamodel;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Choices")
public class Choice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "choiceId")
	private Integer choiceId;
	
	@Column(name = "choiceContent")
	private String choiceContent;
	
	@Column(name = "isCorrect")
	private boolean isCorrect;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "questionId")
	private Question question;

	public Choice()
	{
	}

	public Choice(String choiceContent, boolean isCorrect)
	{
		this.choiceContent = choiceContent;
		this.isCorrect = isCorrect;
	}

	public Integer getChoiceId() {
		return choiceId;
	}

	public void setId(Integer id) {
		this.choiceId = id;
	}

	public String getChoiceContent() {
		return choiceContent;
	}

	public void setChoiceContent(String choiceContent) {
		this.choiceContent = choiceContent;
	}

	public boolean getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
}
