/**
 * 
 */
package net.wdc.testinglist;



import java.util.Set;

import net.wdc.testinglist.model.Question;


public class Poll {

	private Set<Question> questions;
	
	private Integer correctAnswers;
	
	private Integer incorrectAnswers;
	
	private Integer unansweredQuestions;
	
	private String testingTime;
	

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setCorrectAnswers(Integer correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	public Integer getCorrectAnswers() {
		return correctAnswers;
	}

	public void setIncorrectAnswers(Integer incorrectAnswers) {
		this.incorrectAnswers = incorrectAnswers;
	}

	public Integer getIncorrectAnswers() {
		return incorrectAnswers;
	}

	public void setUnansweredQuestions(Integer unansweredQuestions) {
		this.unansweredQuestions = unansweredQuestions;
	}

	public Integer getUnansweredQuestions() {
		return unansweredQuestions;
	}

	public void setTestingTime(String testingTime) {
		this.testingTime = testingTime;
	}

	public String getTestingTime() {
		return testingTime;
	}
}
