package cl.usach.spring.backend.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan({"cl.usach.spring.backend.application", "cl.usach.spring.backend.rest"})
@EntityScan("cl.usach.spring.backend.entities")
@EnableJpaRepositories("cl.usach.spring.backend.repository")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		ScheduledTasks st = new ScheduledTasks();
		st.IndexTweets();
		st.updateApproval();
		
	}
}