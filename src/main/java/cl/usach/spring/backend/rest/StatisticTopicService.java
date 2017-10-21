package cl.usach.spring.backend.rest;


import cl.usach.spring.backend.entities.Statistic;
import cl.usach.spring.backend.entities.StatisticTopic;
import cl.usach.spring.backend.repository.StatisticRepository;
import cl.usach.spring.backend.repository.StatisticTopicRepository;
import cl.usach.spring.backend.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/st")
public class StatisticTopicService {

    public StatisticTopic statisticTopic;

    @Autowired
    private StatisticTopicRepository statisticTopicRepository;

    @Autowired
    private StatisticRepository statisticRepository;

    @Autowired
    private TopicRepository topicRepository;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<StatisticTopic> getAllStatisticTopic(){
        return statisticTopicRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public StatisticTopic findOne(@PathVariable("id") Integer id){return this.statisticTopicRepository.findOne(id);}

    //Tweets: statistic_id = 1.
    //Aprob/Desaprob: statistic_id = 2.
    @RequestMapping(value = "tweets/topics/{topic_id}/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<StatisticTopic> findValue(@PathVariable("topic_id") Integer id)
    {
        
    }
}
