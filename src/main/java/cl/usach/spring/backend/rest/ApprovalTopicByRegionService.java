package cl.usach.spring.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.usach.spring.backend.entities.ApprovalTopic;
import cl.usach.spring.backend.entities.ApprovalTopicByRegion;
import cl.usach.spring.backend.repository.ApprovalTopicByRegionRepository;
import cl.usach.spring.backend.repository.ApprovalTopicRepository;


@RestController
@RequestMapping("/approvals_by_region")
public class ApprovalTopicByRegionService {
	
	public ApprovalTopicByRegion approvalByRegion;
    @Autowired
    private ApprovalTopicByRegionRepository approvalByRegionRepository;
	
    /*id: 1 -> legal
    id: 2 -> medicinal
    Se obtiene valores de aprobacion y desaprobación por categoria
   */
 
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public Iterable<ApprovalTopicByRegion> findByRegion(@PathVariable("id") Integer id){
	  return this.approvalByRegionRepository.findByRegion(id);
	  }

}
