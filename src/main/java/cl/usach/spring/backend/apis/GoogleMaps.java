package cl.usach.spring.backend.apis;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

public class GoogleMaps {
	private String key = "AIzaSyBNX1xinU-iZ9LHCw-Bpx_Z6mTsdsCJDJI";
	private GeoApiContext context = new GeoApiContext.Builder()
		    .apiKey(key)
		    .build();
	
	private String[] regiones = inicializarRegiones();
	
	private String[] inicializarRegiones(){
		String regiones[] = new String[15];
		regiones[0] = "Tarapacá";
		regiones[1] = "Antofagasta";
		regiones[2] = "Atacama";
		regiones[3] = "Coquimbo";
		regiones[4] = "Valparaíso";
		regiones[5] = "Higgins";
		regiones[6] = "Maule";
		regiones[7] = "Bío Bío";
		regiones[8] = "Araucanía";
		regiones[9] = "Lagos";
		regiones[10] = "Aysén";
		regiones[11] = "Magallanes";
		regiones[12] = "Santiago";
		regiones[13] = "Ríos";
		regiones[14] = "Araucanía";	
		return regiones;
	}
	
	private String identificarRegion(String region){
		for (int i=0 ; i < 15 ; i++){
			if(region.indexOf(regiones[i]) != -1){
				return regiones[i];
			}
		}
		return null;
	}
	
	/*
	public void Ejemplo(){
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey(key)
			    .build();
		GeocodingResult[] results;
		try {
			results = GeocodingApi.geocode(context,
				    "Cañete, Chile").await();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			if (results.length == 0){
				System.out.println("niuuun BRRRROOOOOOLLLI");
			}else{
				System.out.println(gson.toJson(results[0].addressComponents[3].longName));
			}
			
		} catch (ApiException e) {
			System.out.println("no encontrado");
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public void ObtenerRegion(String localidad){
		GeocodingResult[] results;
		try {
			results = GeocodingApi.geocode(context,
				    localidad).await();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			if (results.length != 0){
				String region = identificarRegion(results[0].addressComponents[3].longName);
				System.out.println(region);
				
			}else{
				System.out.println("NO ENCONTRADA");
			}
			
		} catch (ApiException e) {
			System.out.println("no encontrado");
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
