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

	@Column(name="topic_id", nullable=false)
	private int topicId;

	@Column(name="DATE", nullable=false)
	private Timestamp date;
	
	@Column(name="VALUE", nullable=false)
	private int value;
	

	public int getTweetsTopicId() {
		return this.tweetsTopicId;
	}

	public void setTweetsTopicIdd(int tweetsTopicId) {
		this.tweetsTopicId = tweetsTopicId;
	}

	public int getTopicId() {
		return this.topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
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
