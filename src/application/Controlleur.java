package application;

import java.io.File;
import java.io.IOException;

import Main.Interface;
import Objets.Grille;
import Objets.Pion;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Controlleur {
	
	@FXML
	public GridPane grilleVisuelle;
	@FXML
	public TextField nomSauvegarde;
	
	
	@FXML
	public VBox listpartie;
	
	private String nomPartie;
	private Grille grilleDeJeux;
	private boolean isPartieTerminer;
	private Stage primaryStage;
	private int mode;
	private int nbPoint;
	private int nbCoupAutoriser = 4;
	
	
	public void initGrilleExemple() {
		Grille exemple = new Grille();
		
	}
	
	public void init (Stage primaryStage, Grille partie , GridPane grilleVisuelle) {
		this.grilleDeJeux = partie;
		this.primaryStage = primaryStage;
		this.grilleVisuelle = grilleVisuelle;
	}
	
	public void init (Stage primaryStage , int mode) {
		this.primaryStage = primaryStage;
		this.mode = mode;
		if (grilleVisuelle != null) creerPartie();
	}
	private void trouverListPartie() {
		for (File file : new File("src/saves").listFiles()) {
			Button partie = new Button(file.getName());
			partie.setOnMouseClicked(e->this.nomPartie = partie.getText());
			partie.setAlignment(Pos.CENTER);
			this.listpartie.getChildren().add(partie);
		}
	}

	private void creerPartie () {
		grilleDeJeux = new Grille();
		grilleVisuelle.setOnMouseClicked(e->initPartie(e));
		if (mode == 2) {
			grilleDeJeux.randomGrille(8);
			actualiser(grilleDeJeux);
		}
	}
	
	public void actualiser(Grille grilleDeJeux) {
		for (int colonne = 0; colonne < grilleDeJeux.getTableau().length; colonne++) {
			for (int ligne = 0; ligne < grilleDeJeux.getTableau()[0].length; ligne++) {

				Pion pion = grilleDeJeux.getPionFrom(colonne, ligne);
				ImageView c = (ImageView) grilleVisuelle.getChildren().get(ligne * 7 + colonne);
				if (pion != null) {
					
					changerCase(c, pion.getTeam() ? 1 : 2);

				} else {
					changerCase(c, 0);
				}
				
				
			}
		}
	}

	private void initPartie (Event click) {
		if (!isPartieTerminer) {
			ImageView caseCliquer = (ImageView) click.getTarget();
			final int indiceCase = GridPane.getColumnIndex(caseCliquer) == null ? 0 : GridPane.getColumnIndex(caseCliquer);
			isPartieTerminer = jouer(indiceCase);
			if (isPartieTerminer) nbPoint++;
			
			if (mode == 2) {
				nbCoupAutoriser--;
				isPartieTerminer = nbCoupAutoriser == 0; 
			}
		} 
		
		if (isPartieTerminer) {
			
		}

	}

	boolean jouer(int b) {
		boolean tour = grilleDeJeux.isTourJoueur();
		Pion jouer = null;
		try {
			if (mode != 1 || (mode == 1 && tour)) {
				jouer = grilleDeJeux.creer(b, tour);
				grilleDeJeux.ajouter(jouer);
			} else {
				jouer = grilleDeJeux.ordinateur(tour);
			}
			ImageView c = (ImageView) grilleVisuelle.getChildren().get(jouer.getCoord()[1] * 7 + jouer.getCoord()[0]);
			changerCase(c,jouer.getTeam() ?  1 : 2 );
		} catch (Exception e) {
		}
		return jouer != null && grilleDeJeux.IdentVictory(3,jouer);
		
	}
	
	
	void changerCase(ImageView aChanger , int parQuoi) {
		if(parQuoi == 0) {
			aChanger.setImage(new Image(Main.class.getResource("ressources/placeParking.png").toExternalForm()));
		} else if (parQuoi == 1){
			aChanger.setImage(new Image(Main.class.getResource("ressources/placeRouge.png").toExternalForm()));
		} else if (parQuoi == 2) {
			aChanger.setImage(new Image(Main.class.getResource("ressources/placeBleu.png").toExternalForm()));
		}
	}
	

	private void loadPage(String page) throws IOException {
		loadPage(page, -1);
	}
	private void loadPage(String page,int mode) throws IOException {
		
        FXMLLoader fxloader = new FXMLLoader(Main.class.getResource("view/" + page + ".fxml"));
        Parent root = fxloader.load();
        Scene scene = new Scene(root);
        Controlleur c = fxloader.getController();
        c.init(primaryStage,mode);
        scene.getStylesheets().setAll(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	private Controlleur loadPage(String page,Stage newStage) throws IOException {
		
		FXMLLoader fxloader = new FXMLLoader(Main.class.getResource("view/" + page + ".fxml"));
        Parent root = fxloader.load();
        Scene scene = new Scene(root);
        Controlleur c = fxloader.getController();
        c.init(newStage,grilleDeJeux,grilleVisuelle);
        scene.getStylesheets().setAll(getClass().getResource("application.css").toExternalForm());
        newStage.setScene(scene);
        newStage.show();
        return c;
	}
	
	@FXML
	public void quitter () {
		primaryStage.close();
	}
	
	
	@FXML
	public void ouvrirMenu () throws IOException {
		loadPage("Home");
	}
	
	
	@FXML
	public void ouvrirPartie2Joueur() throws IOException {
		loadPage("Partie",0);
		
	}
	
	
	@FXML
	public void ouvrirPartieIa() throws IOException {
		loadPage("Partie",1);
	}
	
	
	@FXML
	public void ouvrirPartiePuzzle() throws IOException {
		loadPage("Partie",2);
	}
	
	
	@FXML
	public void ouvrirOptions() throws IOException {
		loadPage("options");
	}
	
	
	@FXML
	public void ouvrirModeJeu() throws IOException {
		loadPage("ChoiceMod");
	}
	@FXML
	public void ouvrirAdversaire() throws IOException {
		loadPage("OponnentChoice");
	}
	
	@FXML
	public void ouvrirSauvegarde() throws IOException {
		loadPage("Sauvegarder", new Stage());
	}
	@FXML
	public void ouvrirCharger() throws IOException {
		Controlleur c = loadPage("Charger", new Stage());
		c.trouverListPartie();
	}
	
	@FXML 
	public void exitGrille() throws IOException {
		if (mode == 2) {
			loadPage("ChoiceMod");
		} else {
			loadPage("OponnentChoice");
		}
	}
	@FXML
	public void sauvegarder() throws IOException {
		String nomSv = nomSauvegarde.getText();
		System.out.println(this.grilleDeJeux);
		Interface.save(this.grilleDeJeux, nomSv);
	}
	@FXML
	public void charger() throws IOException {
		if (nomPartie != null) {
			Grille grille = Interface.charger(nomPartie);
			actualiser(grille);
		}
	}
	@FXML
	public void afficherRegles() throws IOException {
		Grille grille = Interface.charger("regles.txt");
		actualiser(grille);
	}
}
