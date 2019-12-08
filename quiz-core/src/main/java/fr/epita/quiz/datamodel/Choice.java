package fr.epita.quiz.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Choices")
public class Choice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "choice_id")
	private Integer choice_id;
	
	@Column(name = "ChoiceContent")
	private String choiceContent;
	
	@Column(name = "isCorrect")
	private boolean isCorrect;
	
//	@ManyToOne
//	@JoinColumn(name = "question_id")
//	private Question question;

	public Choice()
	{
	}

	public Choice(String choiceContent, boolean isCorrect)
	{
		this.choiceContent = choiceContent;
		this.isCorrect = isCorrect;
	}
	
	public Integer getChoiceId()
	{
		return choice_id;
	}
	
	public void setChoiceId(Integer choice_id)
	{
		this.choice_id = choice_id;
	}

	public String getChoiceContent()
	{
		return this.choiceContent;
	}
	
	public void setChoiceContent(String choiceContent)
	{
		this.choiceContent = choiceContent;
	}
	
	public boolean isCorrect()
	{
		return this.isCorrect;
	}
	
	public void setIsCorrect(boolean isCorrect)
	{
		this.isCorrect = isCorrect;
	}
	
//	public Question getQuestion()
//	{
//		return this.question;
//	}
//	
//	public void setQuestion(Question question)
//	{
//		this.question = question;
//	}
}
