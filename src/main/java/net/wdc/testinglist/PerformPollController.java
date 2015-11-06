/**
 * 
 */
package net.wdc.testinglist;


import net.wdc.testinglist.dao.QuestionDAO;
import net.wdc.testinglist.model.Answer;
import net.wdc.testinglist.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/performPoll.html")
public class PerformPollController {
	

	@Autowired
	private QuestionDAO questionDAO;

	@RequestMapping(method = RequestMethod.GET)
	public String showQuestion(@ModelAttribute("pollForm")PollForm pollform, Map<String, Object> model,
			HttpServletRequest request){
		String questionId = request.getParameter("id");
		if (questionId == null) {
			request.getSession().invalidate();
		}
		HttpSession session = request.getSession();
		synchronized(session) {
			if (questionId == null) {
				session.removeAttribute("questions");
				session.removeAttribute("current_question");
				session.removeAttribute("correct_answers");
				session.removeAttribute("incorrect_answers");
				session.removeAttribute("unanswered_questions");
				Set<Question> questions = questionDAO.getQuestionsWithAnswers();
				Question question = questions.iterator().next();
				pollform.setQuestion(question);
				session.setAttribute("questions", questions);
				session.setAttribute("current_question", question);
				session.setAttribute("correct_answers", new Integer(0));
				session.setAttribute("incorrect_answers", new Integer(0));
				session.setAttribute("unanswered_questions", new Integer(0));
			} else {
		    	@SuppressWarnings("unchecked")
				Set<Question> questions = (Set<Question>) session.getAttribute("questions");
		    	Set<Question> tr = new TreeSet<Question>(Question.getComparator());
		    	tr.addAll(questions);
		    	for (Question q : tr) {
		    		if (q.getId().equals(new Integer(questionId))) {
		    			pollform.setQuestion(q);
		    			session.setAttribute("current_question", q);
		    		}
		    	}
			}
		}

		return "performPoll";
	}
	
	
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView onSubmit(@ModelAttribute("answerForm")PollForm pollform, Map<String, Object> model, 
    		HttpServletRequest request) {
    	Integer nextQuestionId = null;
    	HttpSession session = request.getSession();
    	synchronized(session) {
	    	String answerId = request.getParameter("answer_id");
	    	if (answerId == null) {
	    		Integer unansweredQuestions = new Integer((Integer) session.getAttribute("unanswered_questions"));
	    		session.setAttribute("unanswered_questions", ++unansweredQuestions);
	    	}
	    	Question currentQuestion = (Question) session.getAttribute("current_question");
	    	for (Answer answer : currentQuestion.getAnswers()) {
	    		if (answerId != null && answer.getId().equals(new Integer(answerId))) {
	    			if (answer.getIsRight() == 1) {
	    				Integer correctAnswers = new Integer((Integer) session.getAttribute("correct_answers"));
	    				session.setAttribute("correct_answers", ++correctAnswers);
	    			} else {
	    				Integer incorrectAnswers = new Integer((Integer) session.getAttribute("incorrect_answers"));
	    				session.setAttribute("incorrect_answers", ++incorrectAnswers);
	    			}
	    		}
	    	}
	    	@SuppressWarnings("unchecked")
			Set<Question> questions = (Set<Question>) session.getAttribute("questions");
	    	Set<Question> tr = new TreeSet<Question>(Question.getComparator());
	    	tr.addAll(questions);
	    	List<Question> qList = new ArrayList<Question>();
	    	qList.addAll(tr);
	    	for (int i = 0; i < qList.size(); i++) {
	    		if (qList.get(i).getId().equals(currentQuestion.getId())) {
	    			try {
	    				nextQuestionId = qList.get(++i).getId();
	    			} catch (Exception e) {
	    				return new ModelAndView("redirect:results.html");
	    			}
	    		}
	    	}
    	}

    	return new ModelAndView("redirect:performPoll.html?id=" + nextQuestionId);
    }
}
