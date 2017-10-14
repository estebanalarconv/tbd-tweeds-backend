package cl.usach.spring.backend.lucene;

import java.nio.file.Paths;

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

public void SearchTweets(){
		
		try{
			
			//Ubicacion del indice
			Directory directory = FSDirectory.open(Paths.get("/tmp/testindex"));
			
			//crear buscador
			DirectoryReader ireader = DirectoryReader.open(directory);
			IndexSearcher isearcher = new IndexSearcher(ireader);
			
			//palabras a buscar dentro del indice
			String textToSearch = "messi";
			
			//crear consulta
			QueryParser parser = new QueryParser("fieldname", new StandardAnalyzer());
			Query query = parser.parse(textToSearch);
			
			//realizamos busqueda
			TopDocs result=isearcher.search(query,100);
			ScoreDoc[] hits = result.scoreDocs;
			if(hits.length==0){
                System.out.println("No se ha encontrado");
            }else{
            	for(int i=0;i<hits.length;i++){
                    int docId= hits[i].doc;
                    Document doc = isearcher.doc(docId);
                    System.out.println(doc.get("text"));
                 }
    			System.out.println("La palabra"+textToSearch+"esta en"+hits.length+"documentos");

            }
			
			
		}catch (Exception ex){
			System.out.println("Error");
		}
	}
}
