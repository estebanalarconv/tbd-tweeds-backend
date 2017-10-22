package cl.usach.spring.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="tweets_topic")
public class TweetsTopic {
	private static final long serialVersionUID = 1L;

	/*@Autowired
	private TweetsTopicRepository tweetsTopicRepository;*/

	@Id
	@Column(name="id", unique=true, nullable=false)
	private int tweetsTopicId;

	@Column(name="topic_name", nullable=false)
	private String topicName;

	@Column(name="date", nullable=false)
	private Timestamp date;
	
	@Column(name="value", nullable=false)
	private int value;

	@Column(name="description",nullable = false)
	private String description;

	public int getTweetsTopicId() {
		return this.tweetsTopicId;
	}

	public void setTweetsTopicIdd(int tweetsTopicId) {
		this.tweetsTopicId = tweetsTopicId;
	}

	public String getTopicName() {
		return this.topicName;
	}

	public void setTopicName(int topicName) {
		this.topicName = topicName;
	}
	
	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
