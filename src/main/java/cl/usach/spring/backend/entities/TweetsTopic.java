package cl.usach.spring.backend.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;
import java.sql.Timestamp;

@Entity
@Table(name="tweets_topic")
public class TweetsTopic {

	/*@Autowired
	public TweetsTopicRepository tweetsTopicRepository;*/

	@Id
	@Column(name="id", unique=true, nullable=false)
	private int tweetsTopicId;

	@Column(name="value", nullable=false)
	private int value;
	
	@Column(name="create_at", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;

	//@Column(name="topic_name",nullable = false)
	//private String topicName;
	@ManyToOne
    @JoinColumn(name="topic_id", referencedColumnName = "id")
    private Topic topic;

	public int getTweetsTopicId() {
		return tweetsTopicId;
	}

	public void setTweetsTopicId(int tweetsTopicId) {
		this.tweetsTopicId = tweetsTopicId;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	

	

	/*public void updateTweets()
	{
		//id = 1 MEDICINAL
		//id = 2 RECREACIONAL
		//id = 3 LEGALIZACION
		Search search = new Search();
		int totalMedicina = search.totalTweetsMedical();
		int totalLegal = search.totalTweetsLegal();
		int totalRecreacion = search.totalTweetsRecreativos();
		TweetsTopic tweetsTopicMedical = tweetsTopicRepository.findOne(1);
		TweetsTopic tweetsTopicRecreacional = tweetsTopicRepository.findOne(2);
		TweetsTopic tweetsTopicLegal = tweetsTopicRepository.findOne(3);
		tweetsTopicMedical.setValue(totalMedicina);
		tweetsTopicRecreacional.setValue(totalRecreacion);
		tweetsTopicLegal.setValue(totalLegal);
		tweetsTopicRepository.save(tweetsTopicMedical);
		tweetsTopicRepository.save(tweetsTopicLegal);
		tweetsTopicRepository.save(tweetsTopicRecreacional);
	}*/
}
