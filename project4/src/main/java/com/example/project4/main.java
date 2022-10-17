package com.example.project4;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

/**
 * main class
 * run the application
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */

public class main extends Application {
    /**
     * open the main page
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Rutgers Coffee");
        stage.setScene(scene);
        stage.show();


    }


    /**
     * start
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}