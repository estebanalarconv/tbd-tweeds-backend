package cl.usach.spring.backend.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="approval_topic")
public class ApprovalTopic {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private int id;

	@Column(name = "approval",nullable = false)
    private int approval;
    
    @Column(name="disapproval", nullable = false)
    private int disapproval;
    
    @Column(name="create_at", nullable = false)
	private Timestamp create;
    
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

	public Timestamp getCreate() {
		return create;
	}

	public void setCreate(Timestamp create) {
		this.create = create;
	}
}


