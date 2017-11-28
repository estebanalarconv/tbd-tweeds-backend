package cl.usach.spring.backend.application;

import cl.usach.spring.backend.apis.GoogleMaps;
import cl.usach.spring.backend.apis.TwitterStreaming;
import cl.usach.spring.backend.database.MongoConection;
import cl.usach.spring.backend.database.Neo4j;
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
		/*
		MongoConection mdb = new MongoConection();
		DBCollection collection = mdb.ConectarMongo2();
		int[] valores = mdb.FindFollowersById("932749707167879169", collection);
		System.out.println("valor0: " + valores[0]);
		System.out.println("valor1: " + valores[1]);
		Analysis analysis = new Analysis();*/
		//TwitterStreaming twitter = new TwitterStreaming();
		//twitter.init();
		//analysis.calcularInfluenciaTweets();
		System.out.println("INDEXANDO TWEEETS");
		Index index = new Index();
		index.IndexarTweets();
		//ScheduledTasks st = new ScheduledTasks();
		//st.IndexTweets();


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