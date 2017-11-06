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
    
    
    /*Se obtienen los valores de todas las categorias
     * */
    @CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Iterable<ApprovalTopic> getAllUsers() {
		return approvalRepository.findAll();
	}
 
    
    /*id: 1 -> legal
      id: 2 -> medicinal
      Se obtiene valores de aprobacion y desaprobación por categoria
     */
   
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ApprovalTopic findOne(@PathVariable("id") Integer id){return this.approvalRepository.findOne(id);}
    
	
}
