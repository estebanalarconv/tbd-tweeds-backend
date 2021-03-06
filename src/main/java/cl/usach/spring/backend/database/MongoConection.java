package cl.usach.spring.backend.database;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import twitter4j.Status;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MongoConection {
	private MongoClient mClient;
	
	
	public MongoCollection<Document> ConectarMongo(){
	    // Creating a Mongo client 
		mClient = new MongoClient( "localhost" , 27017 );
	 
	        
	    //Accessing the database 
	    MongoDatabase database = mClient.getDatabase("myTwitter");
	    //database.getCollection("sampleCollection"); 
	    //System.out.println("Collection created successfully"); 
	     
	     //Retrieving a collection
	     MongoCollection<Document> collection = database.getCollection("cTwitter");
	     //collection.drop();
	     //System.out.println("Collection sampleCollection selected successfully");
	     return collection;      	    
	}
	
	public DBCollection ConectarMongo2(){
	    // Creating a Mongo client 
		mClient = new MongoClient( "localhost" , 27017 );
        DB db = mClient.getDB("myTwitter");
        DBCollection coll = db.getCollection("cTwitter");
	     return coll;      	    
	}
	
	public void DesconectarMongo(){
		mClient.close();
	}
	
	public void agregarDocumento(MongoCollection<Document> collection, Status status){
		   
		 //Ejemplo para agregar datos
	      Document document = new Document();      
	      if (status.getPlace() != null){
	    	  if (status.getPlace().getName().contains("Chil")){
	    		  document.append("location", status.getPlace().getName())
	    		.append("id", status.getId()) 
	  	      	.append("text", status.getText())
	      		.append("user_id", status.getUser().getId())
	      		.append("user_name", status.getUser().getName())
	      		.append("user_nickname", status.getUser().getScreenName())
	  	  		.append("retweets",status.getRetweetCount())
	  	  		.append("likes",status.getFavoriteCount())
	  	  		.append("followers", status.getUser().getFollowersCount())
	  	  		.append("followees", status.getUser().getFriendsCount())
	  	  		.append("created_at",status.getCreatedAt());
	  	      collection.insertOne(document); 
	    	  }
	    	  
	      }else if (status.getUser().getLocation() != null){
	    	  if (status.getUser().getLocation().contains("Chil")){
	    		  document.append("location", status.getUser().getLocation())
	    	    .append("id", status.getId()) 
	  	      	.append("text", status.getText())
	      		.append("user_id", status.getUser().getId())
	      		.append("user_name", status.getUser().getName())
	      		.append("user_nickname", status.getUser().getScreenName())
	  	  		.append("retweets",status.getRetweetCount())
	  	  		.append("likes",status.getFavoriteCount())
	  	  		.append("followers", status.getUser().getFollowersCount())
	  	  		.append("followees", status.getUser().getFriendsCount())
	  	  		.append("created_at",status.getCreatedAt());
	  	      collection.insertOne(document); 
	    	  }	    	  
	      }else{
	    	  document.append("id", status.getId()) 
	  	      	.append("text", status.getText())
	      		.append("user_id", status.getUser().getId())
	      		.append("user_name", status.getUser().getName())
	      		.append("user_nickname", status.getUser().getScreenName())
	  	  		.append("retweets",status.getRetweetCount())
	  	  		.append("likes",status.getFavoriteCount())
	  	  		.append("followers", status.getUser().getFollowersCount())
	  	  		.append("followees", status.getUser().getFriendsCount())
	  	  		.append("created_at",status.getCreatedAt());
	  	      collection.insertOne(document); 
	      }
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
	    DB database=mongo.getDB("myTwitter");
	    DBCollection collection=database.getCollection("cTwitter");
	    DBObject query = new BasicDBObject("region",idRegion);
	    DBCursor cursor = collection.find(query);
	    while(cursor.hasNext()) {
	    	//System.out.println((String) cursor.next().get("text"));
	    	retorno.add(cursor.next());
	    }
	    //DBObject jo=cursor.one();
	    
		return retorno;
	}
	
	public List<DBObject> findManyTweetData(List<String> ids) {
		
        MongoClient mongo = new MongoClient("localhost" , 27017 );
	    DB database=mongo.getDB("myTwitter");
	    DBCollection collection=database.getCollection("cTwitter");
	    List<DBObject> retorno=new ArrayList<>();
	    for(int i=0;i<ids.size();i++) {
	    	DBObject query = new BasicDBObject("_id",new ObjectId(ids.get(i)));
		    DBCursor cursor = collection.find(query);
		    retorno.add(cursor.one());
	    }
	    //mongo.close();
	    //DBObject jo=cursor.one();
	    //System.out.println((String) cursor.one().get("text"));
	    //return cursor.one();
	    return retorno;
	    
	}
	
	public String FindLocationByIdTweet(String idTweet, DBCollection collection){
		 BasicDBObject whereQuery = new BasicDBObject();
		 long id = Long.parseLong(idTweet);
		 whereQuery.put("id", id);
		 DBCursor cursor = collection.find(whereQuery);
		 while(cursor.hasNext()) {
				DBObject o = (DBObject) cursor.next();
				String location =  (String) o.get("location_user");
				System.out.println((String) o.get("text"));
				return location;
		    }
		 return null;
	}
	
	public String FindUserById(String idTweet, DBCollection collection){
		BasicDBObject whereQuery = new BasicDBObject();
		long id = Long.parseLong(idTweet); 
		whereQuery.put("id", id);
		DBCursor cursor = collection.find(whereQuery);
		while(cursor.hasNext()) {
			DBObject o = (DBObject) cursor.next();
			String user_id =  (String) o.get("user_id").toString();
			return user_id;
		}
		return null;
	}
	
	// [0] -> followers
	// [1] -> followees
	public int[] FindFollowersById(String idTweet, DBCollection collection){
		BasicDBObject whereQuery = new BasicDBObject();
		 long id = Long.parseLong(idTweet);
		 whereQuery.put("id", id);
		 DBCursor cursor = collection.find(whereQuery);
		 int [] valores = new int[2];
		 valores[0] = 0;
		 valores[1] = 0;
		 while(cursor.hasNext()) {
				DBObject o = (DBObject) cursor.next();
				if (o.get("followers") != null && o.get("followees") != null){
					valores[0] =  (int) o.get("followers");
					valores[1] =  (int) o.get("followees");
					return valores;
				}				
		    }
		 return valores;
	}


}
