package Main;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.Arrays;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.CharBuffer;

import Objets.Grille;

import java.io.File;
import java.io.FileNotFoundException;

public class Interface {

	
	private static final String PARENT_DIRECTORY = "src/saves/";
	private static File[] listParties = new File(PARENT_DIRECTORY).listFiles();
	private static int index = listParties.length ;
	private static final int size = 95;
	
	
	public static void save(Grille current, String nom) throws IOException {
		
		File saveFile = new File(PARENT_DIRECTORY,nom + ".txt");
		FileWriter writer = new FileWriter(saveFile,false);
		
		writer.write(current.toString() + "\n");
		
		writer.close();
		
	}
	public static File getPartie(String name) {
		File found = null;
		for (int i = 0; i < listParties.length; i++) {
			if (listParties[i].getName().equals(name)) {
				found = listParties[i];
			}
		}
		return found;
	}
	public static Grille charger(String name) throws IOException {
		FileReader reader = new FileReader(getPartie(name));
		char[] fileContent = new char[size];
		reader.read(fileContent, 0, size);
		Grille aRenvoyer = new Grille();
		int compteurCase = 0;
		for (int i = fileContent.length - 1; i >= 0 ; i--) {
			if (fileContent[i] == '_') {
				compteurCase++;
			} else if (fileContent[i] == 'J' || fileContent[i] == 'B') {
				aRenvoyer.ajouter(6 - (compteurCase % 7), fileContent[i] == 'J');
				compteurCase++;
			}
				
		}
		
		return aRenvoyer;
	}
	public static void delete(String nom) throws IOException {
		getPartie(nom).delete();
		
	}

}
