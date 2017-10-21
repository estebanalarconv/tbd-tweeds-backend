package cl.usach.spring.backend.database;

import com.mongodb.client.MongoDatabase;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

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
	
	public DBObject findTweetData(String id) {
			
			MongoClient mongo = new MongoClient( "localhost" , 27017 );
		    DB database=mongo.getDB("tbd1");
		    DBCollection collection=database.getCollection("tbd1ex");
		    DBObject query = new BasicDBObject("_id",new ObjectId(id));
		    DBCursor cursor = collection.find(query);
		    //DBObject jo=cursor.one();
		    //System.out.println((String) cursor.one().get("text"));
		    return cursor.one();
		    
		}
	
	public List<DBObject> findTweetByRegion (int idRegion){
		List<DBObject> retorno=new ArrayList();
		MongoClient mongo = new MongoClient( "localhost" , 27017 );
	    DB database=mongo.getDB("tbd1");
	    DBCollection collection=database.getCollection("tbd1ex");
	    DBObject query = new BasicDBObject("region",idRegion);
	    DBCursor cursor = collection.find(query);
	    while(cursor.hasNext()) {
	    	//System.out.println((String) cursor.next().get("text"));
	    	retorno.add(cursor.next());
	    }
	    //DBObject jo=cursor.one();
	    
		return retorno;
	}

}
