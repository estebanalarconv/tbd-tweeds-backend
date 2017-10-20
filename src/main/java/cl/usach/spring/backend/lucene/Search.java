package cl.usach.spring.backend.lucene;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


public class Search {

	//LA FUNCION BUSCA LOS IDS (ID DADO POR MONGO) DENTRO DE LOS TWEETS DE LA BDMONGO SEGUN LA
		//PALABRA word
		//RETORNA UNA LISTA CON ID'S
	public List<String> SearchTweets(String word){
		List<String> retorno=new ArrayList();
			try{
				
				//Ubicacion del indice
				Directory directory = FSDirectory.open(Paths.get("/tmp/testindex"));
				
				//crear buscador
				DirectoryReader ireader = DirectoryReader.open(directory);
				IndexSearcher isearcher = new IndexSearcher(ireader);
				
				//palabras a buscar dentro del indice
				String textToSearch = word;
				
				//crear consulta
				QueryParser parser = new QueryParser("fieldname", new StandardAnalyzer());
				Query query = parser.parse(textToSearch);
				
				//realizamos busqueda
				TopDocs result=isearcher.search(query,100);
				ScoreDoc[] hits = result.scoreDocs;
				if(hits.length==0){
	                System.out.println("No se han encontradon tweets con la palabra "+word);
	            }else{
	            	for(int i=0;i<hits.length;i++){
	            		int docId= hits[i].doc;
	                    Document doc = isearcher.doc(docId);
	                    retorno.add(doc.get("id"));
	                 }
	    			System.out.println("La palabra"+textToSearch+"esta en"+hits.length+"documentos");
	
	            }
				
				
			}catch (Exception ex){
				System.out.println("Error");
			}
			return retorno;
		}
	
	
	
	//FUNCION QUE RETORNA LOS IDS DE LOS TWEETS CON RESPECTO A MEDICINA ENCONTRADOS
		public List<String> getMedicalTweets() {		
				//Asignación de palabras de categoría medicina
						List <String> palabrasMedicina=new ArrayList<>();
						palabrasMedicina.add("medicina*");
						palabrasMedicina.add("medicinal*");
						palabrasMedicina.add("estudio*");
						palabrasMedicina.add("estudios*");
						palabrasMedicina.add("investigación*");
						palabrasMedicina.add("investigacion*");
						palabrasMedicina.add("universidad*");
						palabrasMedicina.add("dolor*");
						palabrasMedicina.add("enfermedad*");
						palabrasMedicina.add("curativa*");
						palabrasMedicina.add("propiedad*");
						palabrasMedicina.add("tratamiento*");
						palabrasMedicina.add("epilepsia*");
						palabrasMedicina.add("thc");
						palabrasMedicina.add("cancer");
						palabrasMedicina.add("cancerígena*");
						palabrasMedicina.add("cancerigena*");
						palabrasMedicina.add("celula*");
						palabrasMedicina.add("célula*");
						palabrasMedicina.add("esquizofrenia");
						palabrasMedicina.add("beneficio*");
						palabrasMedicina.add("beneficiable*");
						palabrasMedicina.add("memoria*");
						palabrasMedicina.add("salud*");
						palabrasMedicina.add("saludable");
						palabrasMedicina.add("corazon*");
						palabrasMedicina.add("corazón*");
						palabrasMedicina.add("aceite*");
						palabrasMedicina.add("artitris*");
						palabrasMedicina.add("hueso*");
						palabrasMedicina.add("pomada*");
						palabrasMedicina.add("farmacia*");
						palabrasMedicina.add("Farmacia");
						palabrasMedicina.add("hoja*");
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
		
		
		public List<String> getRecreativeTweets() {		
			//Asignación de palabras de categoría medicina
					List <String> palabrasMedicina=new ArrayList<>();
					palabrasMedicina.add("bongazo*");
					palabrasMedicina.add("matacola*");
					palabrasMedicina.add("pito*");
					palabrasMedicina.add("skt1*");
					palabrasMedicina.add("sacate1*");
					palabrasMedicina.add("felicidad*");
					palabrasMedicina.add("relajo*");
					palabrasMedicina.add("relax*");
					palabrasMedicina.add("creatividad*");
					palabrasMedicina.add("recreativo*");
					palabrasMedicina.add("recreativa*");
					palabrasMedicina.add("hachis*");
					palabrasMedicina.add("compartir*");
					palabrasMedicina.add("socializar*");
					palabrasMedicina.add("cultivar*");
									
					
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
		
		
		public List<String> getLegalTweets() {		
			//Asignación de palabras de categoría medicina
					List <String> palabrasMedicina=new ArrayList<>();
					palabrasMedicina.add("legal*");
					palabrasMedicina.add("legalización*");
					palabrasMedicina.add("legalincenlas**");
					palabrasMedicina.add("ley**");
					palabrasMedicina.add("20000");
					palabrasMedicina.add("prohibicion*");
					palabrasMedicina.add("prohibición*");
					palabrasMedicina.add("penalizado*");
					palabrasMedicina.add("juicio*");
					palabrasMedicina.add("carcél*");
					palabrasMedicina.add("carcel*");
					palabrasMedicina.add("prision*");
					palabrasMedicina.add("prisión*");
					palabrasMedicina.add("libertad");
					palabrasMedicina.add("condicional");
					palabrasMedicina.add("policia*");
					palabrasMedicina.add("policía*");
					palabrasMedicina.add("paco*");
					palabrasMedicina.add("juicio*");
					palabrasMedicina.add("politico*");
					palabrasMedicina.add("politíco*");
					palabrasMedicina.add("politica*");
					palabrasMedicina.add("politíca*");
					palabrasMedicina.add("senador*");
					palabrasMedicina.add("senadres");
					palabrasMedicina.add("diputado*");
					palabrasMedicina.add("diputados*");
					palabrasMedicina.add("senadora*");
					palabrasMedicina.add("congreso*");
					palabrasMedicina.add("Bachelet*");
					palabrasMedicina.add("Presidenta*");
					palabrasMedicina.add("Presidente*");
					palabrasMedicina.add("narcotrafico*");
					palabrasMedicina.add("Narcotrafico*");
					palabrasMedicina.add("traficar*");
					palabrasMedicina.add("traficante*");
					palabrasMedicina.add("PDI*");
					palabrasMedicina.add("autoridad*");
					palabrasMedicina.add("cocaína*");
					palabrasMedicina.add("cocaina*");
					palabrasMedicina.add("droga*");
					palabrasMedicina.add("despenalizar*");
					palabrasMedicina.add("despenalizacion*");
					palabrasMedicina.add("despenalización*");
					palabrasMedicina.add("ratis*");
					
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
}
