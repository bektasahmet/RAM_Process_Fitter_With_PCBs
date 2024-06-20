package _21010310003_Ahmet_Bektas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class _21010310003_Reader {
	
	String[] lines;
	File dosya;
	
	public _21010310003_Reader() {
		this.dosya = new File("girdi.txt");
		
	}
	
	public void readlines(File dosya) {
		try {
	        this.dosya = dosya;
	        BufferedReader br = new BufferedReader(new FileReader(dosya));
	        
	        int lineCount = 0;
	        while (br.readLine() != null) {
	            lineCount++;
	        }
	        
	        br.close();
	        
	        this.lines = new String[lineCount]; 

	        br = new BufferedReader(new FileReader(dosya)); 
	        String line;
	        int i = 0;

	        while ((line = br.readLine()) != null) {
	            this.lines[i] = line;
	            i++;
	        }

	        br.close();
	    } catch (IOException e) {
	        System.out.println("Dosya okunamadı. " + e.getMessage());
	    }

	}
	
	
}
