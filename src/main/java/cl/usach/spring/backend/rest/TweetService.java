package cl.usach.spring.backend.rest;


import cl.usach.spring.backend.entities.Tweet;
import cl.usach.spring.backend.entities.User;
import cl.usach.spring.backend.repository.TweetRepository;
import cl.usach.spring.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweets")
public class TweetService {

    public Tweet tweet;
    @Autowired
    private TweetRepository tweetRepository;
    @Autowired
    private UserRepository userRepository;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Tweet> getAllTweets(){return this.tweetRepository.findAll();}

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Tweet findOne(@PathVariable("id") Integer id){return this.tweetRepository.findOne(id);}

    @RequestMapping(value = "/{id}/user", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> getUser(@PathVariable("id") Integer id){
        if (tweetRepository.exists(id)){
            User user = tweetRepository.findOne(id).getUser();
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }else{
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }




}
