/**
 * 
 */
package net.wdc.testinglist.dao;

import java.util.List;
import java.util.Set;

import net.wdc.testinglist.model.Answer;
import net.wdc.testinglist.model.Question;



public interface QuestionDAO {

	void addQuestion(Question question) throws Exception;

	List<Question> getQuestions();

	Question getQuestionById(Integer id);

	void editQuestion(Question question);

	Set<Question> getQuestionsWithAnswers();

	Answer getAnswerById(Integer id);

	void deleteQuestion(Integer id);

}
