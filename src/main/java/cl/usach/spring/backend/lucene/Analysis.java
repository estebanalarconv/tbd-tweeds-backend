package cl.usach.spring.backend.lucene;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


public class Analysis {
	
	private Map<String, Integer> crearMapaPalabrasLegales(){
		Map<String, Integer> valorDePalabras = new HashMap<String,Integer>();
		valorDePalabras.put("buena*", 1);
		valorDePalabras.put("solucion*", 1);
		valorDePalabras.put("estudi*",1);
		valorDePalabras.put("gloria",2);
		valorDePalabras.put("legal*",1);
		valorDePalabras.put("\"a favor\"",2);
		valorDePalabras.put("ganas",3);
		valorDePalabras.put("alivia*",1);
		valorDePalabras.put("despenaliz*",2);
		valorDePalabras.put("\"no es droga\"",2);
		valorDePalabras.put("\"no es una droga\"",2);
		
		valorDePalabras.put("narco*",-2);
		valorDePalabras.put("incaut*",-1);
		valorDePalabras.put("contra",-1);
		valorDePalabras.put("\"causa problema\"",-1);
		valorDePalabras.put("detuv*",-1);
		valorDePalabras.put("detener*",-1);
		valorDePalabras.put("criminal*",-1);
		valorDePalabras.put("deten*",-1);
		valorDePalabras.put("trafic*",-1);
		valorDePalabras.put("tráfic*",-1);
		valorDePalabras.put("arrest*",-1);
		valorDePalabras.put("amenaza*",-1);
		valorDePalabras.put("mala",-1);
		valorDePalabras.put("\"no a la legal*\"",-2);
		valorDePalabras.put("\"no legal*\"",-2);
		valorDePalabras.put("daña",-1);
		valorDePalabras.put("daño",-1);
		valorDePalabras.put("droga NOT no",-2);
		valorDePalabras.put("delincuente*",-1);
		
		return valorDePalabras;
	}
	
	private List<String> crearListaPalabrasLegales(){
		List<String> palabras = new ArrayList<String>();
		palabras.add("buena*");
		palabras.add("solucion*");
		palabras.add("estudi*");
		palabras.add("gloria");
		palabras.add("legal*");
		palabras.add("\"a favor\"");
		palabras.add("ganas");
		palabras.add("alivia*");
		palabras.add("despenaliz*");
		palabras.add("\"no es droga\"");
		palabras.add("\"no es una droga\"");
		
		palabras.add("narco*");
		palabras.add("incaut*");
		palabras.add("contra");
		palabras.add("\"causa problema\"");
		palabras.add("detuv*");
		palabras.add("detener*");
		palabras.add("criminal*");
		palabras.add("deten*");
		palabras.add("trafic*");
		palabras.add("tráfic*");
		palabras.add("arrest*");
		palabras.add("amenaza*");
		palabras.add("mala");
		palabras.add("\"no a la legal*\"");
		palabras.add("\"no legal*\"");
		palabras.add("daña");
		palabras.add("daño");
		palabras.add("droga NOT no");
		palabras.add("delincuente*");
		return palabras;	
	}
	
	private Map<String, Integer> crearMapaPalabrasMedicina(){
		Map<String, Integer> palabras = new HashMap<String,Integer>();
		palabras.put("solucion*",1);
		palabras.put("calma*",1);
		palabras.put("sirv*",1);
		palabras.put("mejor*",2);
		palabras.put("medicina*",2);
		palabras.put("alivia",1);
		palabras.put("cur*",1);
		palabras.put("trata*",1);
		
		palabras.put("causa*",-1);
		palabras.put("provoc*",-1);
		palabras.put("contra",-1);
		palabras.put("\"causa problema\"",-2);
		palabras.put("daña",-2);
		palabras.put("daño",-2);
		palabras.put("arruin*",-2);
		
		return palabras;
	}
	
	private List<String> crearListaPalabrasMedicina(){
		List<String> palabras = new ArrayList<String>();
		palabras.add("solucion*");
		palabras.add("calma*");
		palabras.add("sirv*");
		palabras.add("mejor*");
		palabras.add("medicina*");
		palabras.add("alivia");
		palabras.add("cur*");
		palabras.add("trata*");
		
		palabras.add("causa*");
		palabras.add("provoc*");
		palabras.add("contra");
		palabras.add("\"causa problema\"");
		palabras.add("daña");
		palabras.add("daño");
		palabras.add("arruin*");
		return palabras;	
	}
	
	
	public Map<String,Integer> AnalisisSentimientosTweets(int categoria){
		int valorTemp;
		Map<String, Integer> valorDePalabras;
		List<String> palabras;
		if (categoria == 0){//ES TIPO LEGAL
			valorDePalabras = this.crearMapaPalabrasLegales();
			palabras = this.crearListaPalabrasLegales();
		}else{ //TIPO MEDICINAL
			valorDePalabras = this.crearMapaPalabrasMedicina();
			palabras = this.crearListaPalabrasMedicina();
		}
		
		
		Map<String, Integer> valorDeTweets = new HashMap<String,Integer>();
		try{
			
			//Ubicacion del indice
			Directory directory = FSDirectory.open(Paths.get("/tmp/testindex"));
			
			//crear buscador
			DirectoryReader ireader = DirectoryReader.open(directory);
			IndexSearcher isearcher = new IndexSearcher(ireader);
			
			
			//crear consulta
			QueryParser parser = new QueryParser("fieldname", new StandardAnalyzer());
			
			for (int i=0 ; i<palabras.size() ; i++){
				org.apache.lucene.search.Query query = parser.parse(palabras.get(i));
				
				//realizamos busqueda
				//EN VEZ DE 2000 AGREGAR NUMERO DE TWEETS DE MONGO()
				TopDocs result=isearcher.search(query,2000);
				ScoreDoc[] hits = result.scoreDocs;
				if(hits.length==0){
	                System.out.println("No se han encontradon tweets con la palabra "+palabras.get(i));
	            }else{
	            	for(int j=0;j<hits.length;j++){
	            		int docId= hits[j].doc;
	                    org.apache.lucene.document.Document doc = isearcher.doc(docId);
	                    System.out.println("Tweet: "+ doc.get("text"));
	                    //SI no se ha ingresado el ID
	                    if (valorDeTweets.get(doc.get("id")) == null){
	                    	valorDeTweets.put(doc.get("id"), valorDePalabras.get(palabras.get(i)));
	                    }else{ //EN CASO DE QUE EXISTA, SUMAR CON EL VALOR ANTERIOR
	                    	valorTemp = valorDeTweets.get(doc.get("id")) +  valorDePalabras.get(palabras.get(i));
	                    	valorDeTweets.put(doc.get("id"), valorTemp);
	                    }
	                    System.out.println("Llave: " + doc.get("id")+ "valor: " +valorDeTweets.get(doc.get("id")).toString());
	                    
	                 }
	    			//System.out.println("La palabra "+word+" esta en"+hits.length+"documentos");
	            }

            }
			return valorDeTweets;
		}catch (Exception ex){
			System.out.println("Error");
			return null;
		}
		
	}
	
	public int[] SepararAprobacionDesaprobacion(Map<String, Integer> valorTweets){
		//0-> aprobacion
		//1 -> desaprobacion
		int[] numeroTweets = new int[2];
		numeroTweets[0]=0;
		numeroTweets[1]=0;
		Iterator it = valorTweets.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry e = (Map.Entry)it.next();
			System.out.println(e.getKey() + "value= " + e.getValue());
			if ((int)e.getValue() > 0){
				numeroTweets[0]++;
			}else if((int)e.getValue() < 0){
				numeroTweets[1]++;
			}
		}
		return numeroTweets; 	
	}
}
