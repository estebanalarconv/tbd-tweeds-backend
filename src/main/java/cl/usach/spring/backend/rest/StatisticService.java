package cl.usach.spring.backend.rest;


import cl.usach.spring.backend.entities.Statistic;
import cl.usach.spring.backend.entities.Topic;
import cl.usach.spring.backend.entities.User;
import cl.usach.spring.backend.repository.StatisticRepository;
import cl.usach.spring.backend.repository.TopicRepository;
import cl.usach.spring.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticService {

    public Topic topic;
    @Autowired
    private StatisticRepository statisticRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private UserRepository userRepository;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Statistic> getAllStatistics() { return statisticRepository.findAll(); }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public  Statistic findOne(@PathVariable("id") Integer id) { return statisticRepository.findOne(id);}

    @RequestMapping(value = "/{id}/topics", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Topic>> getTopics(@PathVariable("id") Integer id){
        if (statisticRepository.exists(id)){
            List<Topic> topics = statisticRepository.findOne(id).getTopics();
            return new ResponseEntity<List<Topic>>(topics, HttpStatus.OK);
        }else{
            return new ResponseEntity<List<Topic>>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}/users", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<User>> getUser(@PathVariable("id") Integer id){
        if (statisticRepository.exists(id)){
            List<User> users = statisticRepository.findOne(id).getUsers();
            return new ResponseEntity<List<User>>(users, HttpStatus.OK);
        }else{
            return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
        }
    }
}
