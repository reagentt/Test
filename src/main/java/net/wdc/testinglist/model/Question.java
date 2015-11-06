package net.wdc.testinglist.model;



import java.util.Comparator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import org.hibernate.annotations.*;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="QUESTION")
public class Question {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="CONTENT")
	private String content;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="question")
	@Cascade({ CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	private List<Answer> answers;
	
	public Integer getId(){
		return id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public List<Answer> getAnswers() {
		return answers;
	}
	
	public static Comparator<Question> getComparator() {
		return new Comparator<Question>() {
			@Override
			public int compare(Question q1, Question q2) {
				if (q1.id > q2.id)
					return 1;
				if (q1.id < q2.id)
					return -1;
				
				return 0;
			}
		};
	}

}
