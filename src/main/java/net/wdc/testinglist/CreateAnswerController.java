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
import java.util.Map;

@Controller
@RequestMapping("/createAnswer.html")
public class CreateAnswerController {


	@Autowired
	private QuestionDAO questionDAO;
	
	@RequestMapping(method = RequestMethod.GET)
	public String addNewAnswer(@ModelAttribute("answerForm")AnswerForm answerorm, 
			Map<String, Object> model, HttpServletRequest request){
		String questionId = request.getParameter("questionId");
		answerorm.setQuestionId(new Integer(questionId));
		return "createAnswer";
	}

    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView onSubmit(@ModelAttribute("answerForm")AnswerForm answeform, 
			Map<String, Object> model) {
    	Answer answer = answeform.getAnswer();
    	Question question = questionDAO.getQuestionById(answeform.getQuestionId());
    	answer.setQuestion(question);
    	if (question.getAnswers().size() == 0) {
    		answer.setIsRight(1);
    	} else if (answeform.getCorrect()) {
    		for (Answer ans : question.getAnswers()) {
    			if (ans.getIsRight() == 1) {
    				ans.setIsRight(0);
    			}
    		}
    		answer.setIsRight(1);
    	} else {
    		answer.setIsRight(0);
    	}
    	question.getAnswers().add(answer);
    	questionDAO.editQuestion(question);

    	return new ModelAndView("redirect:editQuestion.html?id=" + answeform.getQuestionId());
    }
}
