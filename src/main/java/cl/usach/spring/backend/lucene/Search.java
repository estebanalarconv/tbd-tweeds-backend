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
}
