package cl.usach.spring.backend.application;

import cl.usach.spring.backend.database.MongoConection;
import cl.usach.spring.backend.entities.ApprovalTopic;
import cl.usach.spring.backend.entities.HistoryApprovalTopic;
import cl.usach.spring.backend.entities.Topic;
import cl.usach.spring.backend.entities.TweetsTopic;
import cl.usach.spring.backend.lucene.Analysis;
import cl.usach.spring.backend.lucene.Index;
import cl.usach.spring.backend.lucene.Search;
import cl.usach.spring.backend.repository.ApprovalTopicRepository;
import cl.usach.spring.backend.repository.HistoryApprovalTopicRepository;
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
	 public ApprovalTopicRepository approvalTopicRepository;
	 @Autowired
	 public TopicRepository topicRepository;
	 @Autowired
	 public HistoryApprovalTopicRepository historyApprovalTopicRepository;


	 public Iterable<TweetsTopic> tweetsTopics = null;
	 public Search search = new Search();
	 public Index index = new Index();
	 public Analysis analysis = new Analysis();
	 public MongoConection mc = new MongoConection();

	 //@Scheduled(cron="*/10 * * * * *")
	 /*public void TaskTwitterStreaming() {
		 //logger.info("Fixed Rate Task :: Execution Time - {}", dateFormat.format(new Date()) );
		 tStreaming.init();	 
	 }*/

	 //@Scheduled(cron = "0 1 * * * *")
	 public void IndexTweets()
	 {
		 System.out.println(">>>>>>Tarea Programada Inicio: Indexando Tweets<<<<<<");
		 index.IndexarTweets();
		 System.out.println(">>>>>>Tarea Programada Fin: Indexando Tweets<<<<<<");

	 }

	//@Scheduled(cron = "*/10 * * * * *")
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
		tweetsTopicRepository.save(tweetsTopicRecreational);
	}

	//@Scheduled(cron = "*/10 * * * * *")
	public void updateApproval()
	{
		Map<String, Integer> approvalLegal = analysis.AnalisisSentimientosTweets(1);
		System.out.println("Largo Map: " + approvalLegal.size());
		Map<String, Integer> approvalMedical = analysis.AnalisisSentimientosTweets(0);
		System.out.println("Largo Map 2: " + approvalMedical.size());
		int approvalMedicalValues[] = analysis.SepararAprobacionDesaprobacion(approvalMedical);
		int approvalLegalValues[] = analysis.SepararAprobacionDesaprobacion(approvalLegal);
		ApprovalTopic approvalTopicLegal = approvalTopicRepository.findOne(1);
		ApprovalTopic approvalTopicMedical = approvalTopicRepository.findOne(2);
		Topic topicLegal = new Topic();
		Topic topicMedical = new Topic();
		topicLegal.setId(1);
		topicMedical.setId(1);
		
		approvalTopicLegal.setApproval(approvalLegalValues[0]);
		approvalTopicLegal.setDisapproval(approvalLegalValues[1]);
		approvalTopicMedical.setApproval(approvalMedicalValues[0]);
		approvalTopicMedical.setDisapproval(approvalMedicalValues[1]);
		
		approvalTopicRepository.save(approvalTopicMedical);
		approvalTopicRepository.save(approvalTopicLegal);
		
		//INGRESAR DATOS AL HISTORIAL
		HistoryApprovalTopic hATopicLegal = new HistoryApprovalTopic();
		HistoryApprovalTopic hATopicMedicinal = new HistoryApprovalTopic();
		hATopicLegal.setApproval(approvalLegalValues[0]);
		hATopicLegal.setDisapproval(approvalLegalValues[1]);
		hATopicLegal.setTopic(topicLegal);
		hATopicMedicinal.setApproval(approvalMedicalValues[0]);
		hATopicMedicinal.setDisapproval(approvalMedicalValues[1]);
		hATopicMedicinal.setTopic(topicMedical);
		historyApprovalTopicRepository.save(hATopicLegal);
		historyApprovalTopicRepository.save(hATopicMedicinal);

	}
}
