package Main;
import java.io.FileWriter;
import java.io.IOException;

import Objets.Grille;

import java.io.File;

public class Interface {
	
	
	public static void save(Grille current) throws IOException {
		
		File saveFile = new File("saves/party" + 1 + ".txt");
		
		FileWriter writer = new FileWriter(saveFile,true);
		
		char[] charGrille = current.toString().toCharArray();
		
		for (int character = 0; 
				 character < charGrille.length; character++) {
			
			writer.write(character);
		}
	}

}
