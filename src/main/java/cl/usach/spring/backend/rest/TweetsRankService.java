package cl.usach.spring.backend.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.usach.spring.backend.entities.TweetsRank;
import cl.usach.spring.backend.lucene.Analysis;
import cl.usach.spring.backend.lucene.Search;

@RestController
@RequestMapping("/list_tweets")
public class TweetsRankService {
	private Analysis analysis = new Analysis();
	private Search search = new Search();
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	  @ResponseBody
	  public String[][] findTweetsByTopic(@PathVariable("id") Integer id){
		if (id == 1){
			return  analysis.CalcularInfluenciaTweets(search.getLegalTweets());
		}else if(id == 2){
			return analysis.CalcularInfluenciaTweets(search.getMedicalTweets());
		}else if (id ==3){
			return analysis.CalcularInfluenciaTweets(search.getRecreativeTweets());
		}else{
			return null;
		}
		
	}

}
