package cl.usach.spring.backend.rest;




import cl.usach.spring.backend.entities.Keyword;
import cl.usach.spring.backend.entities.Topic;
import cl.usach.spring.backend.repository.KeywordRepository;
import cl.usach.spring.backend.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/keywords")
public class KeywordService {

    public Keyword keyword;
    @Autowired
    private KeywordRepository keywordRepository;
    @Autowired
    private TopicRepository topicRepository;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Keyword> getAllKeywords() {
        return keywordRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public  Keyword findOne(@PathVariable("id") Integer id) { return keywordRepository.findOne(id);}

    @RequestMapping(value = "/{id}/topics", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Topic>> getTopics(@PathVariable("id") Integer id){
        if (keywordRepository.exists(id)){
            List<Topic> topics = keywordRepository.findOne(id).getTopics();
            return new ResponseEntity<List<Topic>>(topics, HttpStatus.OK);
        }else{
            return new ResponseEntity<List<Topic>>(HttpStatus.NOT_FOUND);
        }
    }
}
