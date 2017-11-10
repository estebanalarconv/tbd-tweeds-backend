package cl.usach.spring.backend.application;

import cl.usach.spring.backend.apis.TwitterStreaming;
import cl.usach.spring.backend.database.MongoConection;
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
		TwitterStreaming twitter = new TwitterStreaming();
		twitter.init();
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
		System.out.println("Desaprobacion Legal"+ approvalLegalValues[1]);*/
		
		/*
		ScheduledTasks st = new ScheduledTasks();
		st.IndexTweets();
  		//st.updateApproval();
        
		Driver driver = GraphDatabase.driver( "bolt://localhost", AuthTokens.basic( "neo4j", "7017" ) );
        Session session = driver.session();
        //borrar grafos
        session.run("match (a)-[r]->(b) delete r");
        session.run("match (n) delete n");
        
        //nodos de las categorias
        session.run( "CREATE (t:Topic {name:'Legalización'})");
        session.run( "CREATE (t:Topic {name:'Medicinal'})");
        session.run( "CREATE (t:Topic {name:'Recreativo'})");
        
        Search luceneSearch = new Search();
        List<String> listIds = luceneSearch.getLegalTweets();
        
        String name = new String();
        String tweet = new String();
        int weight ; 
        
        MongoConection mc = new MongoConection();
        List<DBObject> tweets = mc.findManyTweetData(listIds); 
        
        // Nodos Legalizacion
        for(int i = 0; i < 5; i++)
        {
        	name = tweets.get(i).get("user_name").toString();
        	tweet = tweets.get(i).get("text").toString();
        	weight = i;
        	session.run("create (t:Tweet {name:'"+name+"', tweet:'"+tweet+"', peso:'"+i+"'})");
        	session.run("match (a:Tweet) where a.name='"+name+"'"
                    + "  match (b:Topic) where b.name='Legalización' "
                    + "  create (a)-[r:RLegal]->(b)");
        }
     
        // Nodos Medicinal
        listIds = null;
        listIds = luceneSearch.getMedicalTweets();
        tweets = mc.findManyTweetData(listIds);
        
        for(int i = 0; i < 5; i++)
        {
        	name = tweets.get(i).get("user_name").toString();
        	tweet = tweets.get(i).get("text").toString();
        	session.run("create (t:Tweet {name:'"+name+"', tweet:'"+tweet+"', peso:'"+i+"'})");
        	session.run("match (a:Tweet) where a.name='"+name+"'"
                    + "  match (b:Topic) where b.name='Medicinal' "
                    + "  create (a)-[r:RMedicinal]->(b)");
        }

        //Nodos Recreativos
        listIds = null;
        listIds = luceneSearch.getRecreativeTweets();
        tweets = mc.findManyTweetData(listIds);
        
        for(int i = 0; i < 5; i++)
        {

        	name = tweets.get(i).get("user_name").toString();
        	tweet = tweets.get(i).get("text").toString();
        	session.run("create (t:Tweet {name:'"+name+"', tweet:'"+tweet+"', peso:'"+i+"'})");
        	session.run("match (a:Tweet) where a.name='"+name+"'"
                    + "  match (b:Topic) where b.name='Recreativo' "
                    + "  create (a)-[r:RRecreativo]->(b)");
        }
        session.close();
        driver.close();*/
		
	}
}