package cl.usach.spring.backend.entities;



import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "topics")
//@NamedQuery(name = "Topic.findAll", query = "SELECT t FROM topics to")
public class Topic {
	
    @Id
    @Column(name = "id",unique = true,nullable = false)
    private int id;

    @Column(name = "name",nullable = false)
    private String name;
    

    
    @OneToMany(mappedBy = "topic", targetEntity = HistoryTweetsTopic.class)
    @JsonIgnore
    private List<HistoryTweetsTopic> historyTweetsTopicList;


	@OneToMany(mappedBy = "topic", targetEntity = TweetsTopic.class)
    @JsonIgnore
    private List<TweetsTopic> tweetsTopicList;
	
	@OneToMany(mappedBy = "topic", targetEntity = TweetsTopic.class)
    @JsonIgnore
    private List<ApprovalTopic> approvalTopicList;
	
	@OneToMany(mappedBy = "topic", targetEntity = TweetsTopic.class)
    @JsonIgnore
    private List<HistoryApprovalTopic> historyApprovalTopicList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<HistoryTweetsTopic> getHistoryTweetsTopicList() {
		return historyTweetsTopicList;
	}

	public void setHistoryTweetsTopicList(List<HistoryTweetsTopic> historyTweetsTopicList) {
		this.historyTweetsTopicList = historyTweetsTopicList;
	}

	public List<TweetsTopic> getTweetsTopicList() {
		return tweetsTopicList;
	}

	public void setTweetsTopicList(List<TweetsTopic> tweetsTopicList) {
		this.tweetsTopicList = tweetsTopicList;
	}

	public List<ApprovalTopic> getApprovalTopicList() {
		return approvalTopicList;
	}

	public void setApprovalTopicList(List<ApprovalTopic> approvalTopicList) {
		this.approvalTopicList = approvalTopicList;
	}

	public List<HistoryApprovalTopic> getHistoryApprovalTopicList() {
		return historyApprovalTopicList;
	}

	public void setHistoryApprovalTopicList(List<HistoryApprovalTopic> historyApprovalTopicList) {
		this.historyApprovalTopicList = historyApprovalTopicList;
	}




	
}
