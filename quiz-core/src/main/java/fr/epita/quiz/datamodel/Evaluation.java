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

@Entity
@Table(name="Evaluation")
public class Evaluation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "evaluationId")
	private Integer evaluationId;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "userId")
	private User student;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "quizId")
	private Quiz quiz;
	
	@OneToMany(mappedBy = "evaluation", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<Choice> answers;
	
	@Column(name = "grade")
	private int grade;

	public Integer getEvaluationId() {
		return evaluationId;
	}

	public void setEvaluationId(Integer evaluationId) {
		this.evaluationId = evaluationId;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public List<Choice> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Choice> answers) {
		this.answers = answers;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
	
}
