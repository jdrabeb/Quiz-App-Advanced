package fr.epita.quiz.datamodel;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Topic")
public class Topic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "topic_id")
	private Integer topic_id;
	
	@Column(name = "Topic")
	private String topicName;
	
	public Topic(String topicName)
	{
		this.topicName = topicName;
	}
	
	public Integer getTopicId()
	{
		return topic_id;
	}
	
	public void setTopicId(Integer topic_id)
	{
		this.topic_id = topic_id;
	}
	
	public String getTopicName()
	{
		return this.topicName;
	}
	
	public void setTopicName(String topicName)
	{
		this.topicName = topicName;
	}
}
