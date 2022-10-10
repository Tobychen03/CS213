package project3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * main class
 * Run code
 * Start the application
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */

public class Main extends Application {

    /**
     * Setting the Start stage
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("BankTellerview.fxml"));
        primaryStage.setTitle("Bank System");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();
    }

    /**
     * launch the application
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
