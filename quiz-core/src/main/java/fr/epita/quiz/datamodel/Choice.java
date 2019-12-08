package fr.epita.quiz.datamodel;

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
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "isCorrect")
	private boolean isCorrect;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "questionId")
	private Question question;

	public Choice()
	{
	}

	public Choice(String content, boolean isCorrect)
	{
		this.content = content;
		this.isCorrect = isCorrect;
	}

	public Integer getChoiceId() {
		return choiceId;
	}

	public void setId(Integer id) {
		this.choiceId = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
