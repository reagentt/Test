/**
 * 
 */
package net.wdc.testinglist;

import net.wdc.testinglist.model.Answer;


public class AnswerForm {

	private Answer answer;
	
	private Boolean correct;
	
	private Integer questionId;


	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public Answer getAnswer() {
		return answer;
	}


	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}


	public Boolean getCorrect() {
		return correct;
	}


	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}


	public Integer getQuestionId() {
		return questionId;
	}
}
