package cl.usach.spring.backend.database;

import java.util.Map;

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

import cl.usach.spring.backend.entities.ApprovalTopic;
import cl.usach.spring.backend.entities.ApprovalTopicByRegion;
import cl.usach.spring.backend.entities.HistoryApprovalTopic;
import cl.usach.spring.backend.entities.HistoryTweetsTopic;
import cl.usach.spring.backend.entities.Topic;
import cl.usach.spring.backend.entities.TweetsTopic;
import cl.usach.spring.backend.lucene.Analysis;
import cl.usach.spring.backend.lucene.Search;
import cl.usach.spring.backend.repository.ApprovalTopicByRegionRepository;
import cl.usach.spring.backend.repository.ApprovalTopicRepository;
import cl.usach.spring.backend.repository.HistoryApprovalTopicRepository;
import cl.usach.spring.backend.repository.HistoryTweetsTopicRepository;
import cl.usach.spring.backend.repository.TopicRepository;
import cl.usach.spring.backend.repository.TweetsTopicRepository;

@Component
public class RelationalDatabase implements ApplicationRunner{
	private Search search = new Search();
	private Analysis analysis = new Analysis();

//	@Autowired
	//public static TweetsTopicRepository tweetsTopicRepository;
	
	public void ActualizarTweetsPorTopico(TweetsTopicRepository tweetsTopicRepository, HistoryTweetsTopicRepository HTweetsTopicRepository){
		 TweetsTopic tweetsTopicLegal;
		 TweetsTopic tweetsTopicMedical;
			System.out.println("actualizando mysql");
			int medicinal = search.totalTweetsMedical();
			int legal = search.totalTweetsLegal();
			System.out.println("valor legal "+legal);
			System.out.println("valor medical "+medicinal);
			//legal
			tweetsTopicLegal = tweetsTopicRepository.findOne(1);
			tweetsTopicLegal.setValue(legal);
			tweetsTopicLegal.setCreateAt(null);
			tweetsTopicRepository.save(tweetsTopicLegal);	
			
			//medicical
			tweetsTopicMedical= tweetsTopicRepository.findOne(2);
			tweetsTopicMedical.setValue(medicinal);
			tweetsTopicMedical.setCreateAt(null);
			tweetsTopicRepository.save(tweetsTopicMedical);
			System.out.println("actualizada mysql");
			
			//HISTORIAL		
			Topic topicLegal = new Topic();
			Topic topicMedical = new Topic();
			topicLegal.setId(1);
			topicMedical.setId(2);
			
			HistoryTweetsTopic hTweetsTopicLegal = new HistoryTweetsTopic();
			HistoryTweetsTopic hTweetsTopicMedicinal = new HistoryTweetsTopic();
			hTweetsTopicLegal.setValue(legal);
			hTweetsTopicLegal.setTopic(topicLegal);
			hTweetsTopicMedicinal.setValue(medicinal);
			hTweetsTopicMedicinal.setTopic(topicMedical);
			HTweetsTopicRepository.save(hTweetsTopicMedicinal);
			HTweetsTopicRepository.save(hTweetsTopicLegal);

		
	}

	public void ActualizarAprobacionDesaprobacion(ApprovalTopicRepository approvalTopicRepository, HistoryApprovalTopicRepository historyApprovalTopicRepository)
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
		topicMedical.setId(2);
		
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
	
	public void ActualizarAprobacionPorRegion(ApprovalTopicByRegionRepository aTopicByRegionRepository){
		Map<String, Integer> approvalLegal = analysis.AnalisisSentimientosTweets(1);
		Map<String, Integer> approvalMedical = analysis.AnalisisSentimientosTweets(0);
		int [][] vLegal = analysis.SepararAprobacionDesaprobacionPorRegion(approvalLegal);
		int [][] vMedical = analysis.SepararAprobacionDesaprobacionPorRegion(approvalMedical);
		ApprovalTopicByRegion approvalTopicLegal;
		ApprovalTopicByRegion approvalTopicMedical;
		
		Topic topicLegal = new Topic();
		Topic topicMedical = new Topic();
		topicLegal.setId(1);
		topicMedical.setId(2);
		long resultApproval, resultDesapproval;
		
		//LEGAL
		for (int i=1 ; i<17; i++){
			approvalTopicLegal = aTopicByRegionRepository.findOne(i);
			resultApproval = ((long)vLegal[i][0])/((long)(vLegal[i][0]+vLegal[i][1]));
			resultDesapproval = ((long)vLegal[i][1])/((long)(vLegal[i][0]+vLegal[i][1]));
			approvalTopicLegal.setApproval(resultApproval);
			approvalTopicLegal.setDisapproval(resultDesapproval);
			aTopicByRegionRepository.save(approvalTopicLegal);
			
		}
		
		//MEDICAL
		for (int i=17 ; i<33 ; i++){
			approvalTopicLegal = aTopicByRegionRepository.findOne(i);
			resultApproval = ((long)vMedical[i][0])/((long)(vMedical[i][0]+vMedical[i][1]));
			resultDesapproval = ((long)vMedical[i][1])/((long)(vMedical[i][0]+vMedical[i][1]));
			approvalTopicLegal.setApproval(resultApproval);
			approvalTopicLegal.setDisapproval(resultDesapproval);
			aTopicByRegionRepository.save(approvalTopicLegal);
		}
		
		
		
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
		ActualizarTweetsPorTopico();
	}
}
