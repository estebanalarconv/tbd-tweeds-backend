package cl.usach.spring.backend.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "regions")
public class Region {
	
    @Id
    @Column(name = "id",unique = true,nullable = false)
    private int id;

    @Column(name = "name",nullable = false)
    private String name;
      
    @OneToMany(mappedBy = "region", targetEntity = ApprovalTopicByRegion.class)
    @JsonIgnore
    private List<ApprovalTopicByRegion> approvalTopicByRegionList;

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
	
	public List<ApprovalTopicByRegion> getApprovalTopicByRegionList() {
		return approvalTopicByRegionList;
	}

	public void setApprovalTopicByRegionList(List<ApprovalTopicByRegion> approvalTopicByRegionList) {
		this.approvalTopicByRegionList = approvalTopicByRegionList;
	}
}
