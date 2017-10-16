package cl.usach.spring.backend.twitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import cl.usach.spring.backend.database.MongoConection;
import cl.usach.spring.backend.lucene.Index;
import cl.usach.spring.backend.lucene.Search;
import twitter4j.FilterQuery;
import twitter4j.GeoLocation;
import twitter4j.Place;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;

public class TwitterStreaming {

	private final TwitterStream twitterStream;
	private Set<String> keywords;
	
	//CREACION APLICACION TWITTER CON SU ACCESS TOKEN
	static Twitter twitter;
	AccessToken at=new AccessToken("580554594-OgfCOpqvpxran6qlAtaCa7CMiFlDIj8Yu11Pm0YW","WxSy9Tck4dtekdnj4QEMHTAibvaFZVqe087WXiwQcvnTF");

	private TwitterStreaming() {
		this.twitterStream = new TwitterStreamFactory().getInstance();
		this.keywords = new HashSet<>();
		loadKeywords();
	}

	private void loadKeywords() {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			keywords.addAll(IOUtils.readLines(classLoader.getResourceAsStream("words.dat"), "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static List<DBObject> getTweets() {
		List<DBObject> documents=new ArrayList<>();
				
		twitter=new TwitterFactory().getInstance();
		//twitter.setOAuthConsumer("EakDz05j85rXdkNoHAniu2K3T", "o8g470uJvqsIitPGAsEI0eCaOtoBhawP7pNI6jdcTcc9XMm4B6");
		//twitter.setOAuthAccessToken(at);
		
		//SE ESTABLECEN PALABRAS A BUSCAR EN LOS TWEETS
		Query q=new Query("marihuana OR cannabis OR weed OR 420 OR mariguana OR canabis OR pito OR skt1 OR cogollo");
		//SE ESTABLECE CANTIDAD TE TWEETS(?)
		q.setCount(100);
		//SE ESTABLECE LENGUAJE DE LOS TWEETS RESCATADOS: "es"
		q.setLang("es");
		//SE ESTABLECEN COORDENAS DE LOCALIZACION, EN ESTE CASO SANTIAGO, CON RADIO DE 200KM
		GeoLocation gl=new GeoLocation(-33.447487, -70.673676);
		q.setGeoCode(gl, 200, Query.KILOMETERS);

		//q.setSince("2017-10-10");
		//q.setUntil("2017-01-03");
		QueryResult result=null;
		int Count=0;
		do {
            try {
				result = twitter.search(q);
				List<Status> tweets = result.getTweets();
				String pais="";
				String lugar="";
	            for (int o=0;o<tweets.size();o++) {
	            	
	            	if(tweets.get(o).getText().contains("RT")!=true) {
		                //NUMERO APROX DE VECES FAVORITOS
		                int favs=tweets.get(o).getFavoriteCount();
		                //LOCALIZACION DEL TWEET
		                pais="";
		                lugar="";
		                Place localizacion= tweets.get(o).getPlace();		                
		                if (localizacion!=null) {
		                	pais=localizacion.getCountry();
			                lugar=localizacion.getName();
		                }
		                //NUMERO DE RETWEETS
		                int rt=tweets.get(o).getRetweetCount();
		                //CONSEGUIR EL USUARIO
		                User usuario=tweets.get(o).getUser();
		                String localizacionUsuario=usuario.getLocation();
		                String nombreusuario=usuario.getName();
		                String nickname=usuario.getScreenName();
		                String data=tweets.get(o).getCreatedAt().toString();
		                
		                //SE CREA UN OBJETO PARA LA BASE DE DATOS DE MONGO Y SE AGREGAN ATRIBUTOS
		                //CLAVE Y VALOR
		                BasicDBObject document = new BasicDBObject();
		                document.put("id", tweets.get(o).getId()); 
		                document.put("text", tweets.get(o).getText());
		                document.put("favs", favs);
		                if (localizacion==null) {
		                	document.put("tweetLocalization", "");
		                }
		                else {
		                	document.put("tweetLocalization", localizacion.getName());
		                }
		                document.put("paisTweet", pais);
		                document.put("rt", rt);
		                document.put("userLocation", localizacionUsuario);
		                document.put("userName", nombreusuario);
		                document.put("userNickname", nickname);
		                document.put("TimeStamp", data);
		                //SE AGREGAN LOS DATOS A LA LISTA
		                documents.add(document);		                
		                
		                //IMPRESIÓN DE DATOS OBTENIDOS 
		                //COMENTAR LUEGO
		                System.out.println("TOTAL TWEETS RECOLECTADOS= "+Count);
		            	Count++;
		                System.out.println("TEXT= "+tweets.get(o).getText());
		                System.out.println("FAVS= "+favs);
		                System.out.println("LOCALIZACION TWEET= "+localizacion);
		                System.out.println("PAIS= "+pais);
		                System.out.println("LUGAR= "+lugar);
		                System.out.println("RT= "+rt);
		                System.out.println("LOCALIZACION USUARIO= "+localizacionUsuario);
		                System.out.println("NOMBRE USUARIO= "+nombreusuario);
		                System.out.println("NICKNAME USUARIO= "+nickname);
		                System.out.println("CREATED AT= "+data);
	            	}
	            }
			} catch (TwitterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
		}while ((q = result.nextQuery()) != null);
		
		return documents;
	}
	
	private int saveTweetsMongo(List<DBObject> documents) {
		int retorno=0;
		try {
			MongoClient mongoClient2;
			mongoClient2 = new MongoClient( "localhost" , 27017 );
			DB db = mongoClient2.getDB( "tbd1" );
			DBCollection collection2=db.getCollection("tbd1ex");			
			for(int d=0;d<documents.size();d++) {
				collection2.insert(documents.get(d));
			}
			mongoClient2.close();	
		}
		catch (Exception e) {
			return 1;
		}
		return retorno;
	}
	
	//FUNCION QUE RETORNA LOS IDS DE LOS TWEETS CON RESPECTO A MEDICINA ENCONTRADOS
	private List<String> findMedicalTweets() {		
		//Asignación de palabras de categoría medicina
				List <String> palabrasMedicina=new ArrayList<>();
				palabrasMedicina.add("medicina*");
				palabrasMedicina.add("medicinal*");
				palabrasMedicina.add("estudio*");
				palabrasMedicina.add("estudios*");
				palabrasMedicina.add("investigación*");
				palabrasMedicina.add("investigacion*");
				palabrasMedicina.add("investigaciones");
				palabrasMedicina.add("universidad*");
				palabrasMedicina.add("universidades");
				palabrasMedicina.add("dolor*");
				palabrasMedicina.add("dolores");
				palabrasMedicina.add("enfermedad");
				palabrasMedicina.add("enfermedades");
				palabrasMedicina.add("curativa");
				palabrasMedicina.add("curativas");
				palabrasMedicina.add("propiedad");
				palabrasMedicina.add("propiedades");
				palabrasMedicina.add("tratamiento");
				palabrasMedicina.add("tratamientos");
				palabrasMedicina.add("epilepsia");
				palabrasMedicina.add("thc");
				palabrasMedicina.add("cancer");
				palabrasMedicina.add("cancerígena");
				palabrasMedicina.add("cancerígenas");
				palabrasMedicina.add("cancerigena");
				palabrasMedicina.add("cancerigenas");
				palabrasMedicina.add("celulas");
				palabrasMedicina.add("células");
				palabrasMedicina.add("esquizofrenia");
				palabrasMedicina.add("beneficio");
				palabrasMedicina.add("beneficios");
				palabrasMedicina.add("beneficiable");
				palabrasMedicina.add("beneficiables");
				palabrasMedicina.add("memoria");
				palabrasMedicina.add("salud");
				palabrasMedicina.add("saludable");
				palabrasMedicina.add("corazon");
				palabrasMedicina.add("corazón");
				palabrasMedicina.add("aceite");
				palabrasMedicina.add("artitris");
				palabrasMedicina.add("hueso");
				palabrasMedicina.add("huesos");
				palabrasMedicina.add("pomada");
				palabrasMedicina.add("farmacia*");
				palabrasMedicina.add("farmacias");
				palabrasMedicina.add("Farmacia");
				palabrasMedicina.add("hojas*");
				palabrasMedicina.add("hojas");
				palabrasMedicina.add("cultivar");				
				
				List<String> idEncontrados=new ArrayList<>();
				List<String> idSearch=new ArrayList<>();
				
				Index index = new Index();
				index.IndexarTweets();	
				for (int t=0;t<palabrasMedicina.size();t++) {
					Search search = new Search();
					idSearch=search.SearchTweets(palabrasMedicina.get(t));
					for (int tt=0;tt<idSearch.size();tt++) {
						idEncontrados.add(idSearch.get(tt));
					}
				}
		
		
		return idEncontrados;
	}
	
	
	

	private void init() {
		MongoConection mc = new MongoConection();
		MongoCollection<Document> collection = mc.ConectarMongo();

		StatusListener listener = new StatusListener() {

			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
				System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
			}

			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
				System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
			}

			public void onScrubGeo(long userId, long upToStatusId) {
				System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
			}

			public void onException(Exception ex) {
				ex.printStackTrace();
			}

			@Override
			public void onStallWarning(StallWarning arg0) {

			}

			@Override
			public void onStatus(Status status)
			{

				try
				{

					if (status.getLang().equals("es") &&
							(status.getUser().getLocation().contains("Chile")
									|| status.getUser().getLocation().contains("CL")
									|| status.getUser().getLocation().contains("CHL"))) {
						mc.agregarDocumento(collection, status);
						System.out.println(status.getId());
						System.out.println(status.getText());
					}
				}catch (NullPointerException e){System.out.println("Excepción NullPointerException");}
			}
		};

		FilterQuery fq = new FilterQuery();

		fq.track(keywords.toArray(new String[0]));

		this.twitterStream.addListener(listener);
		this.twitterStream.filter(fq);

	}
	/*
	public static void main(String[] args) {
		//new TwitterStreaming().init();
		
		//MongoConection mc = new MongoConection();
		//MongoCollection<Document> collection = mc.ConectarMongo();
		//mc.mostrarColeccion(collection);
		//Index index = new Index();
		//index.IndexarTweets();
		List<DBObject> tweets=new ArrayList<>();
		tweets=getTweets();
			
	}*/
}
