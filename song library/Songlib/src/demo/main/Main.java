/*Author: Tianle Chen Chenyan Fan*/
package demo.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import demo.ide.Controller;
public class Main extends Application{

    Stage mainStage;

    public void start(Stage stage)
            throws Exception {
        mainStage = stage;
        mainStage.setTitle("Songlib");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/demo/ide/view.fxml"));
        AnchorPane pane = (AnchorPane)loader.load();

        Controller controller = loader.getController();
        controller.setMainStage(mainStage);

        Scene scene = new Scene(pane, 668, 473);
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}