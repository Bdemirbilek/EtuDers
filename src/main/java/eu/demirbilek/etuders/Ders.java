package eu.demirbilek.etuders;
import org.jsoup.select.Elements;

import java.io.Serializable;

public class Ders implements Serializable{
	String dersAdi;
	Sube[] sube;
	public Ders(String d,  int subeSayisi){
		sube= new Sube[subeSayisi];
		dersAdi=d;
		}
	public void subeEkle(Elements row,int subeNo){

		sube[subeNo]=new Sube(row, dersAdi, subeNo);
	}
	public void subeyeYaz(int subeNo, Sube tmpSube){
		sube[subeNo]=tmpSube;
	}



	
}
