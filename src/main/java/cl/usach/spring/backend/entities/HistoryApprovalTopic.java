package cl.usach.spring.backend.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="history_approval_topic")
public class HistoryApprovalTopic {
	
	@Id
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "approval",nullable = false)
    private int approval;
    
    @Column(name="disapproval", nullable = false)
    private int disapproval;
    
    @Column(name="create_at", nullable = false)
	private Timestamp createAt;
   
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

	public int getApproval() {
		return approval;
	}

	public void setApproval(int approval) {
		this.approval = approval;
	}

	public int getDisapproval() {
		return disapproval;
	}

	public void setDisapproval(int disapproval) {
		this.disapproval = disapproval;
	}

	public Timestamp getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}


}
