package cl.usach.spring.backend.lucene;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Analysis {
	
	public int AnalizarTweetLegal(String tweet){
		int value = 0;
		List <String> palabrasPositivas = new ArrayList<>();		
		palabrasPositivas.add("buena");
		palabrasPositivas.add("solucion");
		palabrasPositivas.add("estudi");
		palabrasPositivas.add("gloria");
		palabrasPositivas.add("legal");
		palabrasPositivas.add("favor");
		palabrasPositivas.add("ganas");
		palabrasPositivas.add("alivia");
		
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
		for (int i=0 ; i < size ; i++ ){
			
		}
		
	}
}
