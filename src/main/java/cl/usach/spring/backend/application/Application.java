package cl.usach.spring.backend.application;

import cl.usach.spring.backend.apis.GoogleMaps;
import cl.usach.spring.backend.apis.TwitterStreaming;
import cl.usach.spring.backend.database.MongoConection;
import cl.usach.spring.backend.database.RelationalDatabase;
import cl.usach.spring.backend.lucene.*;

import cl.usach.spring.backend.repository.TweetsTopicRepository;
/*
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableScheduling
@ComponentScan({"cl.usach.spring.backend.application", "cl.usach.spring.backend.rest"})
@EntityScan("cl.usach.spring.backend.entities")
@EnableJpaRepositories("cl.usach.spring.backend.repository")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
<<<<<<< HEAD
		/*
		Index index = new Index();
		index.IndexarTweets();
		Analysis analysis = new Analysis();
		Map<String, Integer> approvalLegal = analysis.AnalisisSentimientosTweets(1);
		Map<String, Integer> approvalMedical = analysis.AnalisisSentimientosTweets(0);
		int approvalMedicalValues[][] = analysis.SepararAprobacionDesaprobacionPorRegion(approvalMedical);
		int approvalLegalValues[][] = analysis.SepararAprobacionDesaprobacionPorRegion(approvalLegal);*/
		
		/*
		MongoConection mg = new MongoConection();
		GoogleMaps gmaps = new GoogleMaps();
		DBCollection coll = mg.ConectarMongo2();
		String id = "932749707167879169";
		String location = mg.FindLocationByIdTweet(id, coll);
		System.out.println("result: "+ location);
		if (location != null){
			int result = gmaps.ObtenerRegion(location);
			System.out.println("region: "+ result);*/
		
		
=======
		Index index = new Index();
		index.IndexarTweets();
		//ScheduledTasks st = new ScheduledTasks();
		//st.IndexTweets();

>>>>>>> a05bd26ab0faf2975e50ff220ee7ee702c79701a
		//TwitterStreaming twitter = new TwitterStreaming();
		//twitter.init();
		/*ScheduledTasks st = new ScheduledTasks();
		st.IndexTweets();
		st.updateTopics();
		Analysis analysis = new Analysis();
		Index index = new Index();
		index.IndexarTweets();
		Map<String, Integer> approvalLegal = analysis.AnalisisSentimientosTweets(1);
		System.out.println("Largo Map: " + approvalLegal.size());
		Map<String, Integer> approvalMedical = analysis.AnalisisSentimientosTweets(0);
		System.out.println("Largo Map 2: " + approvalMedical.size());
		int approvalMedicalValues[] = analysis.SepararAprobacionDesaprobacion(approvalMedical);
		int approvalLegalValues[] = analysis.SepararAprobacionDesaprobacion(approvalLegal);
		System.out.println("Aprobacion Medicinal"+ approvalMedicalValues[0]);
		System.out.println("Aprobacion Medicinal"+ approvalMedicalValues[1]);
		System.out.println("Aprobacion Legal"+ approvalLegalValues[0]);
		System.out.println("Desaprobacion Legal"+ approvalLegalValues[1]);
		

		ScheduledTasks st = new ScheduledTasks();
		st.IndexTweets();*/

		
	}
}