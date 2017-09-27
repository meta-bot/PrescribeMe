/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.API;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 *
 * @author anando
 */
public class MainPanel extends Application {

    static String[] args;
    private ArrayList<String> logInMenu(Stage stage) throws FileNotFoundException{
        ArrayList<String> ret = new ArrayList<String>();
        /* Login Menu */
        Text welcomeText = new Text("PRESCRIBEME");
        welcomeText.setFont(Font.font("verdana",FontWeight.BOLD,FontPosture.REGULAR,20));
        
        Text email_lable = new Text("Email: "); 
        email_lable.setFont(Font.font("verdana",FontWeight.LIGHT,FontPosture.REGULAR,14));
        TextField email = new TextField();
        
        Text password_lable = new Text("Password: "); 
        password_lable.setFont(Font.font("verdana",FontWeight.LIGHT,FontPosture.REGULAR,14));
        PasswordField password = new PasswordField();
        
        Button login = new Button("Log In");
        
        login.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        
        Button cancel = new Button("Cancel");
        
        cancel.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        Image image = new Image(new FileInputStream("doctor_checkup_logo.png"));
        ImageView window = new ImageView(image);
        window.setFitWidth(145);window.setFitHeight(150); window.setPreserveRatio(true);
        
        GridPane grid = new GridPane();
        grid.setMinSize(400, 200);
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(5); grid.setHgap(5);
        grid.setAlignment(Pos.CENTER);
       
        grid.add(email_lable, 0, 0);
        grid.add(password_lable, 0, 1);
        grid.add(password, 1, 1);
        grid.add(email, 1, 0);
        grid.add(cancel, 1, 5);
        grid.add(login, 3, 5);
        
        GridPane grid2 = new GridPane();
        grid.setMinSize(200, 100);
        grid2.setPadding(new Insets(10,10,10,10));
        grid2.setVgap(5); grid2.setHgap(5);
        grid2.setAlignment(Pos.CENTER);
        grid2.add(welcomeText, 0, 0);
        grid2.add(window, 0, 1);
        
        VBox vbox = new VBox();
        vbox.getChildren().addAll(grid2,grid);
        
        Scene scene = new Scene(vbox);
        stage.setTitle("Login Window");
        stage.setScene(scene);
        stage.show();
        stage.setMinHeight(300);
        stage.setMinWidth(400);
        
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if(email.getText().length() ==0 || password.getText().length()==0 || API.getInstance().doctorLogin(email.getText(),password.getText())==false){
                    Alert dialog = new Alert(Alert.AlertType.ERROR);
                    dialog.setTitle("Invalid Input");
                    dialog.setContentText("Check all credentials. Your Input is invalid");
                    dialog.showAndWait();
                }else{
                    ret.add(email.getText());
                    ret.add(password.getText());//System.out.println(ret.size());
                    stage.close();
                    prescription();
                }
            }
        });
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates
                System.exit(0);
            }
        });
        return ret;
    }
    private void prescription(){
        Stage stage = new Stage();
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10, 10, 10, 10));
        HBox top = new HBox();
        top.setSpacing(5);
        borderPane.setTop(top);
        
        
        Button openButton = new Button("Open"); 
        Button save = new Button("SAVE");
        top.getChildren().addAll(openButton,save);
        
        //
        
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setTitle("Prescribe Me!");
        stage.setMinHeight(200);
        stage.setMinWidth(300);
        stage.show();
        openButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                FileChooser fchoos = new FileChooser();
                fchoos.setTitle("Open FIle (Prescribe me)");
                File f = fchoos.showOpenDialog(borderPane.getScene().getWindow());
                
            }
        });
    }
    @Override
    public void start(Stage stage) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ArrayList<String> logIninfo = logInMenu(stage);
        
        
    }
    public void doTask(String[] args){
        launch(args);
    }
    public static void main(String[] args) {
        MainPanel.args = args;
        launch(args);
    }
}
