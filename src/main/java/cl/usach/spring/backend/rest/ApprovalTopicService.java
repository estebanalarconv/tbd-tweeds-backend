package cl.usach.spring.backend.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.usach.spring.backend.entities.ApprovalTopic;
import cl.usach.spring.backend.repository.ApprovalTopicRepository;

@RestController
@RequestMapping("/approvals")
public class ApprovalTopicService {
	public ApprovalTopic approval;
    @Autowired
    private ApprovalTopicRepository approvalRepository;
    
    

    @CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Iterable<ApprovalTopic> getAllUsers() {
		return approvalRepository.findAll();
	}
 

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ApprovalTopic findOne(@PathVariable("id") Integer id){return this.approvalRepository.findOne(id);}
    
	
}
