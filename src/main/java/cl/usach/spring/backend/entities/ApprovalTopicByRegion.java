package cl.usach.spring.backend.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="approval_topic_by_region")
public class ApprovalTopicByRegion {

	    @Id
	    @Column(name = "id", unique = true, nullable = false)
	    private int id;

		@Column(name = "approval",nullable = false)
	    private double approval;
	    
	    @Column(name="disapproval", nullable = false)
	    private double disapproval;


	    @Column(name="create_at", nullable = false)
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date create;
	    
	    @ManyToOne
	    @JoinColumn(name="topic_id", referencedColumnName = "id")
	    private Topic topic;
		
	    @ManyToOne
	    @JoinColumn(name="region_id", referencedColumnName = "id")
	    private Region region;
	    
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

		public double getApproval() {
			return approval;
		}

		public void setApproval(double approval) {
			this.approval = approval;
		}

		public double getDisapproval() {
			return disapproval;
		}

		public void setDisapproval(double disapproval) {
			this.disapproval = disapproval;
		}

		public Date getCreate() {
			return create;
		}

		public void setCreate(Date create) {
			this.create = create;
		}
}
