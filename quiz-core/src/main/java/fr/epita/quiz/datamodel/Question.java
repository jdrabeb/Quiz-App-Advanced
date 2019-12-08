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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Questions")
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "question_id")
	private Integer question_id;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "difficulty")
	private int difficulty;
	
//	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
//	private List<Choice> choices;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "topic_id")
//	private Topic topic;
	
	public Question(String content, int difficulty, List<Choice> choices)
	{
		this.content = content;
		this.difficulty = difficulty;
	//	this.topic = topic;
	//	this.choices = choices;
	}
	
	public Integer getQuestionId()
	{
		return question_id;
	}
	
	public void setId(Integer question_id)
	{
		this.question_id = question_id;
	}
	
	public String getContent()
	{
		return this.content;
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
	
	public int getDifficulty()
	{
		return this.difficulty;
	}
	
	public void setDifficulty(int difficulty)
	{
		this.difficulty = difficulty;
	}
	
//	public Topic getTopic()
//	{
//		return this.topic;
//	}
//	
//	public void setTopic(Topic topic)
//	{
//		this.topic = topic;
//	}
	
//	public List<Choice> getChoices()
//	{
//		return this.choices;
//	}
//	
//	public void setChoices(List<Choice> choices)
//	{
//		this.choices = choices;
//	}
}
