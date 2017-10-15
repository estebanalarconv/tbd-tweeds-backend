package cl.usach.spring.backend.rest;


import cl.usach.spring.backend.entities.Statistic;
import cl.usach.spring.backend.entities.Tweet;
import cl.usach.spring.backend.entities.User;
import cl.usach.spring.backend.repository.StatisticRepository;
import cl.usach.spring.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserService {

    public User user;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StatisticRepository statisticRepository;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public Iterable<User> getAllUsers(){return this.userRepository.findAll();}

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User findOne(@PathVariable("id") Integer id){return this.userRepository.findOne(id);}

    @RequestMapping(value = "/{id}/statistic", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Statistic> getStatistic(@PathVariable("id") Integer id)
    {
        if(userRepository.exists(id))
        {
            Statistic statistic = userRepository.findOne(id).getStatistic();
            return new ResponseEntity<Statistic>(statistic, HttpStatus.OK);
        }else{return new ResponseEntity<Statistic>(HttpStatus.NOT_FOUND);}
    }

    @RequestMapping(value = "/{id}/tweets",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Tweet>> getTweets(@PathVariable("id") Integer id){
        if(userRepository.exists(id)){
            List<Tweet> tweets = userRepository.findOne(id).getTweets();
            return new ResponseEntity<List<Tweet>>(tweets, HttpStatus.OK);
        }else{return new ResponseEntity<List<Tweet>>(HttpStatus.NOT_FOUND);}
    }
}
