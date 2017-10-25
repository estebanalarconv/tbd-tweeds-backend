package cl.usach.spring.backend.rest;


import cl.usach.spring.backend.entities.*;
import cl.usach.spring.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicService {

    public Topic topic;
    @Autowired
    private TopicRepository topicRepository;
    
    

    @CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Topic> getAllUsers() {
		return topicRepository.findAll();
	}
 

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Topic findOne(@PathVariable("id") Integer id){return this.topicRepository.findOne(id);}
    
    @RequestMapping(value = "/{id}/tweetsTopic",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<TweetsTopic>> getTweetsTopic(@PathVariable("id") Integer id){
        if(topicRepository.exists(id)){
            List<TweetsTopic> tweets = topicRepository.findOne(id).getTweetsTopicList();
            return new ResponseEntity<List<TweetsTopic>>(tweets, HttpStatus.OK);
        }else{return new ResponseEntity<List<TweetsTopic>>(HttpStatus.NOT_FOUND);}
    }
    
    @RequestMapping(value = "/{id}/htweetsTopic",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<HistoryTweetsTopic>> getHistoryTweetsTopic(@PathVariable("id") Integer id){
        if(topicRepository.exists(id)){
            List<HistoryTweetsTopic> htweets = topicRepository.findOne(id).getHistoryTweetsTopicList();
            return new ResponseEntity<List<HistoryTweetsTopic>>(htweets, HttpStatus.OK);
        }else{return new ResponseEntity<List<HistoryTweetsTopic>>(HttpStatus.NOT_FOUND);}
    }
    
    @RequestMapping(value = "/{id}/approvalTopic",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<ApprovalTopic>> getApprovalTopic(@PathVariable("id") Integer id){
        if(topicRepository.exists(id)){
            List<ApprovalTopic> approvals = topicRepository.findOne(id).getApprovalTopicList();
            return new ResponseEntity<List<ApprovalTopic>>(approvals, HttpStatus.OK);
        }else{return new ResponseEntity<List<ApprovalTopic>>(HttpStatus.NOT_FOUND);}
    }
    
    @RequestMapping(value = "/{id}/happrovalTopic",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<HistoryApprovalTopic>> getHistoryApprovalTopic(@PathVariable("id") Integer id){
        if(topicRepository.exists(id)){
            List<HistoryApprovalTopic> happrovals = topicRepository.findOne(id).getHistoryApprovalTopicList();
            return new ResponseEntity<List<HistoryApprovalTopic>>(happrovals, HttpStatus.OK);
        }else{return new ResponseEntity<List<HistoryApprovalTopic>>(HttpStatus.NOT_FOUND);}
    }
}
