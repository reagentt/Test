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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



@Controller
@RequestMapping("/editQuestion.html")
public class EditQuestionController {


	@Autowired
	private QuestionDAO questionDAO;
	
	@RequestMapping(method=RequestMethod.GET)
	protected ModelAndView showForm(@ModelAttribute("question") Question question, HttpServletRequest request) {
		String id = request.getParameter("id");
		ModelAndView mav = new ModelAndView("editQuestion");
		Question q = questionDAO.getQuestionById(new Integer(id));
		List<Answer> answers = q.getAnswers();
		question.setId(q.getId());
		question.setAnswers(answers);
		question.setContent(q.getContent());

		return mav;
	}

    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView onSubmit(@ModelAttribute("question") Question question, HttpServletRequest request) {
    	String answerId = request.getParameter("answer_id");
    	String[] answersToDelete = request.getParameterValues("delete_answer");
    	Question questAns = questionDAO.getQuestionById(question.getId());
    	boolean correctAnsDeleted = false;
		for (Answer ans : new ArrayList<Answer>(questAns.getAnswers())) {
			if (ans.getId().equals(new Integer(answerId))) {
				ans.setIsRight(1);				
			} else if (ans.getIsRight() == 1) {
				ans.setIsRight(0);
			}
			if (answersToDelete != null && Arrays.asList(answersToDelete).contains(ans.getId().toString())) {
				if (ans.getIsRight() == 1) {
					correctAnsDeleted = true;
				}
				questAns.getAnswers().remove(ans);
			}
		}
		if (correctAnsDeleted) {
			questAns.getAnswers().get(0).setIsRight(1);
		}
		question.setAnswers(questAns.getAnswers());
    	try {
			questionDAO.editQuestion(question);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
    	return new ModelAndView("redirect:questionList.html");
    }
}
