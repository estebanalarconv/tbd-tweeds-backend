package cl.usach.spring.backend.database;

import com.mongodb.client.MongoDatabase; 
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import java.util.Iterator;

import org.bson.Document;
import twitter4j.Status;


public class MongoConection {
	
	
	public MongoCollection<Document> ConectarMongo(){
	    // Creating a Mongo client 
	    MongoClient mongo = new MongoClient( "localhost" , 27017 );
	 
	        
	    //Accessing the database 
	    MongoDatabase database = mongo.getDatabase("myTwitter");
	    //database.getCollection("sampleCollection"); 
	    System.out.println("Collection created successfully"); 
	     
	     //Retrieving a collection
	     MongoCollection<Document> collection = database.getCollection("cTwitter");
	     //collection.drop();
	     System.out.println("Collection sampleCollection selected successfully");
	     return collection;
	      	    
	}
	
	public void agregarDocumento(MongoCollection<Document> collection, Status status){
		   
		 //Ejemplo para agregar datos
	      Document document = new Document("id", status.getId()) 
	      .append("text", status.getText())
	      .append("user_id", status.getUser().getId())
	      .append("user_name", status.getUser().getName())
	      .append("user_nickname", status.getUser().getScreenName())
		  .append("retweets",status.getRetweetCount())
		  .append("likes",status.getFavoriteCount())
		  .append("created_at",status.getCreatedAt());
	      
	      if (status.getPlace() != null){
	    	  document.append("pais", status.getPlace().getCountry())
	    	  .append("lugar", status.getPlace().getName());
	      }
	      
	      collection.insertOne(document); 
	}
	
	public void mostrarColeccion(MongoCollection<Document> collection){
		 //Getting the iterable object 
	      FindIterable<Document> iterDoc = collection.find(); 

	      //Getting the iterator 
	      Iterator it = iterDoc.iterator(); 
	    
	      while (it.hasNext()) {  
	         System.out.println(it.next());  
	      }
	}

}
