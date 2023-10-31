/*Author: Tianle Chen Chenyan Fan*/
package demo.ide;


import demo.data.Song;
import javafx.event.EventHandler;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Collator;
import java.util.List;

public class Controller {
    Stage mainStage;


    static ObservableList<Song> items = FXCollections.observableArrayList(input.getSongList());
//    ObservableList<String`> showArray =FXCollections.observableArrayList(input.getShowList());

    @FXML
    private TabPane tabpane;
    @FXML
    private Tab tabe;
    @FXML
    private ListView<Song> list;

    @FXML
    private Button add;

    @FXML
    private Button edit;

    @FXML
    private Button delete;

    @FXML
    private TextField add1;
    @FXML
    private TextField add2;
    @FXML
    private TextField add3;
    @FXML
    private TextField add4;
    @FXML
    private TextField edit1;
    @FXML
    private TextField edit2;
    @FXML
    private TextField edit3;
    @FXML
    private TextField edit4;

    @FXML
    public void initialize(){
        list.setItems(items);
        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Song>(){
            public void changed(ObservableValue observable,Song oldValue, Song newValue) {
                if(newValue != null){
                    edit1.setText(newValue.getName());
                    edit2.setText(newValue.getArtist());
                    edit3.setText(newValue.getAlbum());
                    edit4.setText(newValue.getYear());
                }
            }
        });
        list.getSelectionModel().select(0);
    }

    @FXML
    public void clickAdd() throws IOException {
        if(add1.getText().equals("")  || add2.getText().equals("")){
            error();
            return;
        }
        if(overlap(add1.getText(),add2.getText(), null)){
            repeatWarning();
            return;
        }
        if(!isNum(add4.getText())){
            numWarning();
            return;
        }
        if(add1.getText().contains("|") || add2.getText().contains("|") || add3.getText().contains("|")){
            charWarning();
            return;
        }
        if(!confirmAdd()){
            return;
        }
        Song temp = new Song(add1.getText().trim(),add2.getText().trim(),add3.getText().trim(),add4.getText().trim());
        add1.setText("");
        add2.setText("");
        add3.setText("");
        add4.setText("");
        items.add(temp);
        tabpane.getSelectionModel().select(tabe);
        sortItems();
        list.getSelectionModel().select(temp);
        saveFile();
    }

    @FXML
    public void clickDelete() throws IOException {
        if(!confirmDelete()){
            return;
        }
        int i = list.getSelectionModel().getSelectedIndex();
        items.remove(list.getSelectionModel().getSelectedItem());
        if(items.size() > i){
            list.getSelectionModel().select(i);
        }
        if(items.size() == 0){
            edit1.setText("");
            edit2.setText("");
            edit3.setText("");
            edit4.setText("");
        }
        saveFile();
    }

    @FXML
    public void clickEdit() throws IOException {
        if(list.getSelectionModel().getSelectedItem() == null){
            return;
        }
        if(edit1.getText().equals("")  || edit2.getText().equals("")){
            error();
            return;
        }
        Song st = list.getSelectionModel().getSelectedItem();
        if(overlap(edit1.getText(),edit2.getText(), st)){
            repeatWarning();
            return;
        }
        if(!isNum(edit4.getText())){
            numWarning();
            return;
        }
        if(edit1.getText().contains("|") || edit2.getText().contains("|") || edit3.getText().contains("|")){
            charWarning();
            return;
        }
        if(!confirmEdit()){
            return;
        }

        st.setName(edit1.getText().trim());
        st.setArtist(edit2.getText().trim());
        st.setAlbum(edit3.getText().trim());
        st.setYear(edit4.getText().trim());
        sortItems();
        saveFile();
    }

    public static void sortItems() {
        items.sort((o1, o2) -> {
            if (o1.getName().equals(o2.getName())) {
                return Collator.getInstance().compare(o1.getArtist(), o2.getArtist());
            }
            return Collator.getInstance().compare(o1.getName(), o2.getName());
        });
    }

    public void error(){
        Alert alert = new Alert(AlertType.ERROR);
        alert.initOwner(mainStage);
        alert.setTitle("Type Song's Name and artist");
        alert.setHeaderText("Missing Song's name or artist");
        alert.setContentText("Please Type Song's Name and artist");
        alert.showAndWait();
    }

    public boolean confirmAdd(){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.initOwner(mainStage);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure to add Song");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK){
            return true;
        }
        return false;
    }

    public boolean confirmEdit(){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.initOwner(mainStage);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure to edit Song");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK){
            return true;
        }
        return false;
    }

    public boolean confirmDelete(){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.initOwner(mainStage);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure to delete Song");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK){
            return true;
        }
        return false;
    }


    public void repeatWarning(){
        Alert alert = new Alert(AlertType.ERROR);
        alert.initOwner(mainStage);
        alert.setTitle("Warning");
        alert.setHeaderText("Song Repeat");
        alert.setContentText("Please enter different Songs");
        alert.showAndWait();
    }

    public void numWarning(){
        Alert alert = new Alert(AlertType.ERROR);
        alert.initOwner(mainStage);
        alert.setTitle("Warning");
        alert.setHeaderText("Year is not Integer");
        alert.setContentText("Please enter correct year");
        alert.showAndWait();
    }

    public void charWarning(){
        Alert alert = new Alert(AlertType.ERROR);
        alert.initOwner(mainStage);
        alert.setTitle("Warning");
        alert.setHeaderText("Any printable character except '|' (vertical bar)");
        alert.setContentText("Please enter correct format");
        alert.showAndWait();
    }

    public boolean overlap(String name, String artist, Song o){
        for(Song s: items){
            if(s == o){
                continue;
            }
            if(s.getName().equalsIgnoreCase(name) && s.getArtist().equalsIgnoreCase(artist)){
                return true;
            }
        }
        return false;
    }

    public void saveFile() throws IOException {
//        String root = System.getProperty("user.dir");
//        String FileName="save.txt";
//        String filePath = root+File.separator+"src/demo/ide"+File.separator+FileName;
        String filePath = "src/demo/ide/save.txt";
        FileWriter fw = new FileWriter(filePath);
        BufferedWriter bw = new BufferedWriter(fw);
        for(Song i: items){
            String temp = i.getName()+ ";" + i.getArtist() + ";" + i.getAlbum() + ";" + i.getYear();
            bw.write(temp+"\n");
        }
        bw.close();
        fw.close();
    }

    public boolean isNum(String text){
        for(int i = 0; i < text.length(); i++){
            if(text.charAt(i) < '0' || text.charAt(i) > '9'){
                return false;
            }
        }
        return true;
    }

//    public boolean notContain(String text){
//        for(int i = 0; i < text.length(); i++){
//            if(text.charAt(i) == '|'){
//                return false;
//            }
//        }
//        return true;
//    }

    public void setMainStage(Stage stage) {
        mainStage = stage;
    }

}

