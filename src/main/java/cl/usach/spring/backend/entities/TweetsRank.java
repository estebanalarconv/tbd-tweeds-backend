package cl.usach.spring.backend.entities;

import java.util.List;

import cl.usach.spring.backend.lucene.Analysis;
import cl.usach.spring.backend.lucene.Search;

public class TweetsRank {
	private static String[][] tweetsLegal;
	private static String[][] tweetsMedical;
	private static String[][] tweetsRecreativo;
	private static Search search = new Search();
	private  static Analysis analysis = new Analysis();
	
	public static void setTweetsLists(){		
		tweetsLegal = analysis.CalcularInfluenciaTweets(search.getLegalTweets());
		tweetsMedical = analysis.CalcularInfluenciaTweets(search.getMedicalTweets());
		tweetsRecreativo = analysis.CalcularInfluenciaTweets(search.getRecreativeTweets());				
	}
	
	public static String[][] getTweetsLegal(){
		return TweetsRank.tweetsLegal;
	}
	
	public String[][] getTweetsMedical(){
		return this.tweetsMedical;
	}
	
	public String[][] getTweetsRecreative(){
		return this.tweetsRecreativo;
	}
}
