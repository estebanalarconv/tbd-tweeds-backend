package cl.usach.spring.backend.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.usach.spring.backend.entities.TweetsTopic;
import cl.usach.spring.backend.lucene.Search;
import cl.usach.spring.backend.repository.TweetsTopicRepository;

@Component
public class RelationalDatabase implements ApplicationRunner{
	private Search search = new Search();
	private TweetsTopic tweetsTopic;

	@Autowired
	public static TweetsTopicRepository tweetsTopicRepository;
	
	public void ActualizarTweetsPorTopico(){
		int medicinal = search.totalTweetsMedical();
		int legal = search.totalTweetsLegal();
		int recreativo = search.totalTweetsRecreativos();
		
		//medicical
		tweetsTopic= tweetsTopicRepository.findOne(1);
		tweetsTopic.setValue(medicinal);
		tweetsTopicRepository.save(tweetsTopic);
		
		//recreo
		tweetsTopic = tweetsTopicRepository.findOne(1);
		tweetsTopic.setValue(recreativo);
		tweetsTopicRepository.save(tweetsTopic);

		//legal
		tweetsTopic = tweetsTopicRepository.findOne(1);
		tweetsTopic.setValue(legal);
		tweetsTopicRepository.save(tweetsTopic);	
		
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
		ActualizarTweetsPorTopico();
	}
}
