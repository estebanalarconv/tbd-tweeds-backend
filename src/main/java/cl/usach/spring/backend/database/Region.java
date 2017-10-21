package cl.usach.spring.backend.database;

import java.util.ArrayList;
import java.util.List;


public class Region {
	public int id;
	public String nombre;
	public double latitud;
	public double longitud;
	public double radio;
	
	public Region() {
		this.id=0;
		this.nombre="";
		this.latitud=0;
		this.longitud=0;
		this.radio=0;
	}
	
	public List<Region> getRegions(){
		List<Region> retorno=new ArrayList<>();
		Region region=new Region();
		region.id=1;
		region.nombre="Tarapacá";
		region.latitud=-20.283333;
		region.longitud=-69.333333;
		region.radio=200;
		retorno.add(region);
		
		Region region2=new Region();
		region2.id=2;
		region2.nombre="Antofagasta";
		region2.latitud=-23.644167;
		region2.longitud= -70.410833;
		region2.radio=150;
		retorno.add(region2);
		
		Region region3=new Region();
		region3.id=3;
		region3.nombre="Atacama";
		region3.latitud=-27.366667;
		region3.longitud=-70.332222;
		region3.radio=150;
		retorno.add(region3);
		
		Region region4=new Region();
		region4.id=4;
		region4.nombre="Coquimbo";
		region4.latitud=-29.907778;
		region4.longitud=-71.254167;
		region4.radio=150;
		retorno.add(region4);
		
		Region region5=new Region();
		region5.id=5;
		region5.nombre="Valparaíso";
		region5.latitud=-33.063056;
		region5.longitud=-71.639444;
		region5.radio=75;
		retorno.add(region5);
		
		Region region6=new Region();
		region6.id=6;
		region6.nombre="O'higgins";
		region6.latitud=-34.16889;
		region6.longitud=-70.73889;
		region6.radio=200;
		retorno.add(region6);
		
		Region region7=new Region();
		region7.id=7;
		region7.nombre="Maule";
		region7.latitud=-34.98667;
		region7.longitud=-71.24167;
		region7.radio=200;
		retorno.add(region7);
		
		Region region8=new Region();
		region8.id=8;
		region8.nombre="Bío Bío";
		region8.latitud=-36.772778;
		region8.longitud=-73.063056;
		region8.radio=200;
		retorno.add(region8);
		
		Region region9=new Region();
		region9.id=9;
		region9.nombre="Araucanía";
		region9.latitud=-38.9;
		region9.longitud=-72.666667;
		region9.radio=200;
		retorno.add(region9);
		
		Region region10=new Region();
		region10.id=10;
		region10.nombre="Los Lagos";
		region10.latitud=-41.471667;
		region10.longitud= -72.936667;
		region10.radio=200;
		retorno.add(region10);
		
		Region region11=new Region();
		region11.id=11;
		region11.nombre="Aysen";
		region11.latitud=-45.57;
		region11.longitud=-72.066111;
		region11.radio=200;
		retorno.add(region11);
		
		Region region12=new Region();
		region12.id=12;
		region12.nombre="Magallanes";
		region12.latitud=-53.1625;
		region12.longitud=-70.9225;
		region12.radio=150;
		retorno.add(region12);
		
		Region region13=new Region();
		region13.id=13;
		region13.nombre="Metropolitana";
		region13.latitud=-33.437778;
		region13.longitud=-70.650278;
		region13.radio=175;
		retorno.add(region13);
		
		Region region14=new Region();
		region14.id=14;
		region14.nombre="Los Ríos";
		region14.latitud=-39.808333;
		region14.longitud=-73.241667;
		region14.radio=150;
		retorno.add(region14);
		
		Region region15=new Region();
		region15.id=15;
		region15.nombre="Arica y Parinacota";
		region15.latitud=-18.475;
		region15.longitud= -70.314444;
		region15.radio=100;
		retorno.add(region15);
		
		
		return retorno;
	}
}
