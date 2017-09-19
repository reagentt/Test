package net.wdc.testinglist.model;



import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name="question")
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
