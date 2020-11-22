package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ClackGUI {

    @FXML
    private Button sendButton;

    @FXML
    private TextArea messages;

    @FXML
    private TextField text;

    @FXML
    private ClackClient client;

    @FXML
    private TextField IP;

    @FXML
    private TextField userName;

    @FXML
    private Button logInButton;

    @FXML
    void logIn(ActionEvent event) throws IOException {
//        System.out.println("hello");
        Parent root = FXMLLoader.load(getClass().getResource("Clack.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));

        if (userName.getText().equals("") && IP.getText().equals("")) {
            client = new ClackClient();
            client.startClack();
        } else {
            if (!userName.getText().equals("") && IP.getText().equals("")) { // case (i)
                client = new ClackClient(userName.getText());
                client.startClack();
            } else if (!userName.getText().equals("") && !IP.getText().equals("")) { // case (ii)
                client = new ClackClient(userName.getText(), IP.getText());
                client.startClack();
            } else { // case (iii)
                try {
                    int portNumber = Integer.parseInt(IP.getText());
                    client = new ClackClient(userName.getText(), IP.getText(), portNumber);
                    client.startClack();
                } catch (NumberFormatException nfe) {
                    System.err.println("The value given for the port number could not be parsed as an integer");
                }
            }
        }
        System.out.println(client.toString());
    }

    @FXML
    void send() {
        System.out.println("goodbye");
        client.readClientData("text.getText()");
        client.sendData();
    }
}
