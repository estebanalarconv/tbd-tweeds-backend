package cl.usach.spring.backend.application;

import cl.usach.spring.backend.database.MongoConection;
import cl.usach.spring.backend.database.Neo4j;
import cl.usach.spring.backend.database.RelationalDatabase;
import cl.usach.spring.backend.entities.ApprovalTopic;
import cl.usach.spring.backend.entities.HistoryApprovalTopic;
import cl.usach.spring.backend.entities.Topic;
import cl.usach.spring.backend.entities.TweetsRank;
import cl.usach.spring.backend.entities.TweetsTopic;
import cl.usach.spring.backend.lucene.Analysis;
import cl.usach.spring.backend.lucene.Index;
import cl.usach.spring.backend.lucene.Search;
import cl.usach.spring.backend.repository.ApprovalTopicByRegionRepository;
import cl.usach.spring.backend.repository.ApprovalTopicRepository;
import cl.usach.spring.backend.repository.HistoryApprovalTopicRepository;
import cl.usach.spring.backend.repository.HistoryTweetsTopicRepository;
import cl.usach.spring.backend.repository.TopicRepository;
import cl.usach.spring.backend.repository.TweetsTopicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cl.usach.spring.backend.apis.TwitterStreaming;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTasks {
	 //private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	 private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	 private TwitterStreaming tStreaming = new TwitterStreaming();
	 @Autowired
	 public TweetsTopicRepository tweetsTopicRepository;
	 @Autowired
	 public HistoryTweetsTopicRepository HTweetsTopicRepository;
	 @Autowired
	 public ApprovalTopicRepository approvalTopicRepository;
	 @Autowired
	 public TopicRepository topicRepository;
	 @Autowired
	 public HistoryApprovalTopicRepository historyApprovalTopicRepository;
	 @Autowired
	 public ApprovalTopicByRegionRepository aTopicByRegionRepository;

	 public Iterable<TweetsTopic> tweetsTopics = null;
	 public Search search = new Search();
	 public Index index = new Index();
	 public Analysis analysis = new Analysis();
	 public MongoConection mc = new MongoConection();
	 public RelationalDatabase rdb = new RelationalDatabase();
	 public Neo4j neo = new Neo4j();

	 //@Scheduled(cron="*/10 * * * * *")
	 /*public void TaskTwitterStreaming() {
		 //logger.info("Fixed Rate Task :: Execution Time - {}", dateFormat.format(new Date()) );
		 tStreaming.init();	 
	 }*/

	 //@Scheduled(cron = "*/20 * * * * *")
	 public void ActualizarDatabase()
	 {
		 System.out.println(">>>>>>Tarea Programada Inicio: Indexando Tweets<<<<<<");
		 index.IndexarTweets();
		 System.out.println(">>>>>>Tarea Programada Fin: Indexando Tweets<<<<<<");
		 rdb.ActualizarTweetsPorTopico(tweetsTopicRepository, HTweetsTopicRepository);
		 rdb.ActualizarAprobacionDesaprobacion(approvalTopicRepository, historyApprovalTopicRepository);
		 rdb.ActualizarAprobacionPorRegion(aTopicByRegionRepository);
		 TweetsRank tRank = new TweetsRank();
		 tRank.setTweetsLists();

	 }
	//@Scheduled(cron = "*/10 * * * * *")
	/*
	 public void updateTopics()
	{
		System.out.println("[Scheduled Task: Update Tweets topics]");
		int totalMedicina = search.totalTweetsMedical();
		int totalLegal = search.totalTweetsLegal();
		int totalRecreational = search.totalTweetsRecreativos();
		TweetsTopic tweetsTopicMedical = tweetsTopicRepository.findOne(1);
		TweetsTopic tweetsTopicLegal = tweetsTopicRepository.findOne(2);
		TweetsTopic tweetsTopicRecreational = tweetsTopicRepository.findOne(3);

		Topic topicLegal = new Topic();
		Topic topicMedicina = new Topic();
		//System.out.println(">>>>>>>>ENCONTRADOS<<<<<<<<");
		tweetsTopicMedical.setValue(totalMedicina);
		tweetsTopicLegal.setValue(totalLegal);
		tweetsTopicRecreational.setValue(totalRecreational);
		//System.out.println("LALALALALAALALA");
		tweetsTopicRepository.save(tweetsTopicMedical);
		tweetsTopicRepository.save(tweetsTopicLegal);

	}*/

	//@Scheduled(cron = "0 1 * * * *")
	public void createGraph()
	{
		neo.connect("bolt://localhost","neo4j","secret");
		neo.createRelationNodes();
		neo.disconnect();
	}


}
