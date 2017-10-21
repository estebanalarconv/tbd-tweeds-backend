package cl.usach.spring.backend.rest;


import cl.usach.spring.backend.entities.Statistic;
import cl.usach.spring.backend.entities.Topic;
import cl.usach.spring.backend.repository.StatisticRepository;
import cl.usach.spring.backend.repository.TopicRepository;
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
    @Autowired
    private StatisticRepository statisticRepository;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Topic> getAllTopics() {return topicRepository.findAll();}

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Topic findOne(@PathVariable("id") Integer id){return this.topicRepository.findOne(id);}
    
    @RequestMapping(value = "/{id}/statistics", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Statistic>> getStatistics(@PathVariable("id") Integer id){
        if (topicRepository.exists(id)){
            List<Statistic> statistics = topicRepository.findOne(id).getStatistics();
            return new ResponseEntity<List<Statistic>>(statistics, HttpStatus.OK);
        }else{
            return new ResponseEntity<List<Statistic>>(HttpStatus.NOT_FOUND);
        }
    }


}
