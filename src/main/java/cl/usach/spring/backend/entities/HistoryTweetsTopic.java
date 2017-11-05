package cl.usach.spring.backend.entities;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="history_tweets_topic")
public class HistoryTweetsTopic {
	@Id
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "value",nullable = false)
    private int value;
    
    @Column(name="create_at", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;
    
    @ManyToOne
    @JoinColumn(name="topic_id", referencedColumnName = "id")
    private Topic topic;
	
	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

}
