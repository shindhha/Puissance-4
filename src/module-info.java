module FXPuissance4 {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires Puissance4;
	requires javafx.base;

	opens application to javafx.graphics, javafx.fxml;
}
