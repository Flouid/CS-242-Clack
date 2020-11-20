package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.ClackClient;

import java.io.IOException;

public class logInWindow {
    @FXML
    private TextField IP;

    @FXML
    private TextField userName;

    @FXML
    private Button logInButton;

    @FXML
    void logIn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Clack.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();

        if (userName.getText().equals("") && IP.getText().equals("")) {
            ClackClient clackClient = new ClackClient();
            clackClient.start();
        } else {
            // Take the first argument and split it into an array of string by the defined delimiters @ and :
            if (!userName.getText().equals("") && IP.getText().equals("")) { // case (i)
                ClackClient clackClient = new ClackClient(userName.getText());
                clackClient.start();
            } else if (!userName.getText().equals("") && !IP.getText().equals("")) { // case (ii)
                ClackClient clackClient = new ClackClient(userName.getText(), IP.getText());
                clackClient.start();
            } else { // case (iii)
                try {
                    int portNumber = Integer.parseInt(IP.getText());
                    ClackClient clackClient = new ClackClient(userName.getText(), IP.getText(), portNumber);
                    clackClient.start();
                } catch (NumberFormatException nfe) {
                    System.err.println("The value given for the port number could not be parsed as an integer");
                }
            }
        }


    }
}
