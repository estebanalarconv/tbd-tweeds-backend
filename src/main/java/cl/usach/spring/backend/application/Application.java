package cl.usach.spring.backend.application;

import cl.usach.spring.backend.lucene.Analysis;
import cl.usach.spring.backend.lucene.Index;
import cl.usach.spring.backend.repository.TweetsTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Map;

@SpringBootApplication
@EnableScheduling
@ComponentScan({"cl.usach.spring.backend.application", "cl.usach.spring.backend.rest"})
@EntityScan("cl.usach.spring.backend.entities")
@EnableJpaRepositories("cl.usach.spring.backend.repository")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		/*ScheduledTasks st = new ScheduledTasks();
		st.IndexTweets();
		st.updateTopics();*/
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
		
	}
}