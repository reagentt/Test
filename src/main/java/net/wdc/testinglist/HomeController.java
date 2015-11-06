package net.wdc.testinglist;


import net.wdc.testinglist.dao.QuestionDAO;
import net.wdc.testinglist.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 *
 */

@Controller
public class HomeController {
	

	@Autowired
	private QuestionDAO questionDAO;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		return "home";
	}
	
	@RequestMapping(value = "/questionList", method = RequestMethod.GET)
	public ModelAndView questionList(Locale locale, Model model){
		ModelAndView modelAndView = new ModelAndView("questionList");
		List<Question> questions = questionDAO.getQuestions();
		modelAndView.addObject("questions", questions);
		return modelAndView;
	}
	
	@RequestMapping(value ="/createQuestion.html", method = RequestMethod.GET)
	public String addNewQuestion(@ModelAttribute("questionForm")QuestionForm questionform, 
			Map<String, Object> model){
		
		return "createQuestion";
	}

	@RequestMapping(value ="/TestQuestionForm.html", method = RequestMethod.POST)
	public String showNewQuestion(@ModelAttribute("questionForm")QuestionForm questionform, 
			Map<String, Object> model){
		
		Question question = questionform.getQuestion();
		try {
			questionDAO.addQuestion(question);
		} catch (Exception e) {
			question.setContent(e.getMessage());
		} finally {
			model.put("question", question);
		}
		
		return "TestQuestionForm";
	}
	
	@RequestMapping(value ="/deleteQuestion.html", method = RequestMethod.GET)
	public ModelAndView deleteQuestion(HttpServletRequest request) {
		String id = request.getParameter("id");
		questionDAO.deleteQuestion(new Integer(id));
		return new ModelAndView("redirect:questionList");
	}
	
	@RequestMapping(value ="/results.html", method = RequestMethod.GET)
	public String showResults(@ModelAttribute("poll")Poll poll, 
			Map<String, Object> model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer correctAnswers = new Integer((Integer) session.getAttribute("correct_answers"));
		Integer incorrectAnswers = new Integer((Integer) session.getAttribute("incorrect_answers"));
		Integer unansweredQuestions = new Integer((Integer) session.getAttribute("unanswered_questions"));
		Long milliseconds = session.getLastAccessedTime() - session.getCreationTime();
		long second = (milliseconds/1000)%60;
		long minute = (milliseconds/(1000*60))%60;
		long hour = (milliseconds/(1000*60*60))%24;
		String time = String.format("%02d:%02d:%02d", hour, minute, second);
		poll.setCorrectAnswers(correctAnswers);
		poll.setIncorrectAnswers(incorrectAnswers);
		poll.setUnansweredQuestions(unansweredQuestions);
		poll.setTestingTime(time);
		
		return "results";
	}
	
}
