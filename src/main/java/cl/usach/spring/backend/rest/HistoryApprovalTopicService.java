package cl.usach.spring.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.usach.spring.backend.repository.HistoryApprovalTopicRepository;
import cl.usach.spring.backend.entities.HistoryApprovalTopic;

@RestController
@RequestMapping("/hApprovals")
public class HistoryApprovalTopicService {
	public HistoryApprovalTopic hApproval;
    @Autowired
    private HistoryApprovalTopicRepository hApprovalRepository;
    
    

    @CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Iterable<HistoryApprovalTopic> getAllUsers() {
		return hApprovalRepository.findAll();
	}
 

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public HistoryApprovalTopic findOne(@PathVariable("id") Integer id){return this.hApprovalRepository.findOne(id);}
	
}
