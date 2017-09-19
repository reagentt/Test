/**
 *
 */
package net.wdc.testinglist.dao;

import net.wdc.testinglist.model.Answer;
import net.wdc.testinglist.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Repository
public class QuestionDAOImpl implements QuestionDAO {

	@Autowired
	private HibernateTemplate template;



	@Override
	public void addQuestion(Question question) throws Exception {
		template.saveOrUpdate(question);
	}

	@Override
	public List<Question> getQuestions() {
		return template.loadAll(Question.class);
	}

	@Override
	public Set<Question> getQuestionsWithAnswers() {
		Set<Question> tr = new TreeSet<Question>(Question.getComparator());
		tr.addAll(template.find("from Question as q left join fetch q.answers"));
		return tr;
	}

	@Override
	public void deleteQuestion(Integer id) {
		template.delete(template.get(Question.class, id));
	}

	@Override
	public Question getQuestionById(Integer id) {
		return (Question) template.find("from Question as q left join fetch q.answers where q.id = ?", id).get(0);
	}

	@Override
	public void editQuestion(Question question) {
		template.update(question);
	}

	@Override
	public Answer getAnswerById(Integer id) {
		return template.get(Answer.class, id);
	}



}