package cl.usach.spring.backend.lucene;

import com.mongodb.*;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.nio.file.Paths;



public class Index {
	
	public void IndexarTweets(){
		
		try{
			Analyzer analyzer = new StandardAnalyzer();
			IndexWriterConfig config = new IndexWriterConfig(analyzer).setOpenMode(OpenMode.CREATE);
			Directory directory = FSDirectory.open(Paths.get("/tmp/testindex"));
			//Directory directory = new RAMDirectory();
			
			IndexWriter iwriter = new IndexWriter(directory, config);
			
			MongoClient mongoClient = new MongoClient("localhost", 27017);
            DB db = mongoClient.getDB("myTwitter");
            DBCollection coll = db.getCollection("cTwitter");
			//MongoConection mc = new MongoConection();
			//MongoCollection<Document> collection = mc.ConectarMongo();
			
			//Getting the iterable object 
		     DBCursor cursor = coll.find(); 

		      //Getting the iterator 
		      //Iterator it = iterDoc.iterator(); 
		      String text;
		      String id;
		      String _id;
			while (cursor.hasNext()){
				org.apache.lucene.document.Document lDocument = new org.apache.lucene.document.Document();
				DBObject o = (DBObject) cursor.next();
				text = (String) o.get("text");
				id= o.get("id").toString();
				_id = o.get("_id").toString();
				//System.out.println(text);
				lDocument.add(new Field("text",text, TextField.TYPE_STORED));
				lDocument.add(new StringField("id", id, Field.Store.YES));
				lDocument.add(new StringField("_id", _id, Field.Store.YES));
				//lDocument.add(new StringField("text", text, Field.Store.YES));

		    	 
				iwriter.addDocument(lDocument);
			}

		
		iwriter.close();
		}catch (Exception ex){
			System.out.println(ex);
		}

	}

}