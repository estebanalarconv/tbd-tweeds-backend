package cl.usach.spring.backend.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cl.usach.spring.backend.twitter.TwitterStreaming;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTasks {
	 private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	 private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	 private TwitterStreaming tStreaming = new TwitterStreaming();
	 
	 
	 @Scheduled(fixedRate = 25000)
	 public void TaskTwitterStreaming() {
		 logger.info("Fixed Rate Task :: Execution Time - {}", dateFormat.format(new Date()) );
		 tStreaming.init();	 
	 }
}
