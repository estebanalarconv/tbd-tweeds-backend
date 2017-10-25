package cl.usach.spring.backend.lucene;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Analysis {
	
	public int AnalizarTweetLegal(String tweet){
		int value = 0;
		System.out.println(tweet);
		List <String> palabrasPositivas = new ArrayList<>();		
		palabrasPositivas.add("buena");
		palabrasPositivas.add("solucion");
		palabrasPositivas.add("estudi");
		palabrasPositivas.add("gloria");
		palabrasPositivas.add("legal");
		palabrasPositivas.add("favor");
		palabrasPositivas.add("ganas");
		palabrasPositivas.add("alivia");
		palabrasPositivas.add("despenaliza");
		palabrasPositivas.add("no es droga");
		palabrasPositivas.add("no es una droga");
		
		List <String> palabrasNegativas = new ArrayList<>();
		palabrasNegativas.add("narco");
		palabrasNegativas.add("incaut");
		palabrasNegativas.add("contra");
		palabrasNegativas.add("causa problema");
		palabrasNegativas.add("sin narco");
		palabrasNegativas.add("contra");
		palabrasNegativas.add("detuv");
		palabrasNegativas.add("detener");
		palabrasNegativas.add("criminal");
		palabrasNegativas.add("deten");
		palabrasNegativas.add("trafic");
		palabrasNegativas.add("tráfic");
		palabrasNegativas.add("arrest");
		palabrasNegativas.add("amenaza");
		palabrasNegativas.add("mala");
		palabrasNegativas.add("no a la legal");
		palabrasNegativas.add("no legal");
		palabrasNegativas.add("daña");
		palabrasNegativas.add("daño");
		palabrasNegativas.add("droga");
		
		StringTokenizer token = new StringTokenizer(tweet);
		String tmp;
		while (token.hasMoreTokens())
		{
			tmp = token.nextToken();
			for (int i=0 ;i < palabrasNegativas.size() ; i++){
								
				if ( tmp.indexOf(palabrasNegativas.get(i)) != -1) {
					System.out.println(tmp);
					value--;
				}else if (i < palabrasPositivas.size()){
					if(tmp.indexOf(palabrasPositivas.get(i)) != -1){
						System.out.println(tmp);
						value++;
					}
				}
			}
		}
		return value;	
	}
	
	public int AnalizarTweetMedicinal(String tweet){
		int value = 0;
		System.out.println(tweet);
		List <String> palabrasPositivas = new ArrayList<>();		
		palabrasPositivas.add("buena");
		palabrasPositivas.add("solucion");
		palabrasPositivas.add("calma");
		palabrasPositivas.add("sirv");
		palabrasPositivas.add("mejor");
		palabrasPositivas.add("medicina");
		palabrasPositivas.add("alivia");
		palabrasPositivas.add("cur");
		palabrasPositivas.add("trata");
		
		List <String> palabrasNegativas = new ArrayList<>();
		palabrasNegativas.add("causa");
		palabrasNegativas.add("provoc");
		palabrasNegativas.add("contra");
		palabrasNegativas.add("causa problema");
		palabrasNegativas.add("daña");
		palabrasNegativas.add("daño");
		palabrasNegativas.add("arruin");
		palabrasNegativas.add("droga");
		
		StringTokenizer token = new StringTokenizer(tweet);
		String tmp;
		while (token.hasMoreTokens())
		{
			tmp = token.nextToken();
			for (int i=0 ;i < palabrasNegativas.size() ; i++){
								
				if ( tmp.indexOf(palabrasNegativas.get(i)) != -1) {
					System.out.println(tmp);
					value--;
				}else if (i < palabrasPositivas.size()){
					if(tmp.indexOf(palabrasPositivas.get(i)) != -1){
						System.out.println(tmp);
						value++;
					}
				}
			}
		}
		return value;	
	}
	
	public int[] getAprobacionLegal(List<String> idsLegal){
		//0-> aprobacion
		//1 -> desaprobacion
		int numeroTweets[] = {0,0};
		int size = idsLegal.size();
		Search search = new Search();
		String tmp;
		int value;
		for (int i=0 ; i < size ; i++ ){
			tmp = search.SearchTweetsById(idsLegal.get(i));
			if (AnalizarTweetLegal(tmp) > 0){
				numeroTweets[0]++;
			}else if (AnalizarTweetLegal(tmp) < 0){
				numeroTweets[1]++;
			}
		}
		return numeroTweets; 	
	}
	
	public int[] getAprobacionMedicinal(List<String> idsMedicinal){
		//0-> aprobacion
		//1 -> desaprobacion
		int numeroTweets[] = {0,0};
		int size = idsMedicinal.size();
		Search search = new Search();
		String tmp;
		int value;
		for (int i=0 ; i < size ; i++ ){
			tmp = search.SearchTweetsById(idsMedicinal.get(i));
			if (AnalizarTweetMedicinal(tmp) > 0){
				numeroTweets[0]++;
			}else if (AnalizarTweetMedicinal(tmp) < 0){
				numeroTweets[1]++;
			}
		}
		return numeroTweets; 	
	}
}
