package cl.usach.spring.backend.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import cl.usach.spring.backend.entities.TweetsTopic;
import cl.usach.spring.backend.repository.TweetsTopicRepository;;

@RestController
@RequestMapping("/tweets")
public class TweetsTopicService {
	public TweetsTopic tweetsTopic;
    @Autowired
    private TweetsTopicRepository tweetsRepository ;
    
    

    @CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Iterable<TweetsTopic> getAllUsers() {
		return tweetsRepository.findAll();
	}
 
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public TweetsTopic findOne(@PathVariable("id") Integer id){return this.tweetsRepository.findOne(id);}
    
    
}
