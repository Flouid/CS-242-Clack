package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Observable;

public class ClackGUI extends Application {
    private static Stage window;
    private static Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ArrayList<String> participants = new ArrayList<String>();
//        participants.add("Alex");
//        participants.add("Louis");
//        participants.add("Dohan");
//        participants.add("Natasha");
        window = primaryStage;
        GridPane layout = new GridPane();
        TextField textField = new TextField();
        Button send = new Button("Send");
        ObservableList<String> observableList = FXCollections.observableList(participants);
        ListView<String> listView = new ListView<String>();
        listView.setItems(observableList);
        listView.setMaxWidth(100);
        listView.setPrefHeight(140);

        TextArea messages = new TextArea();
        messages.setMaxWidth(150);
        messages.setPrefHeight(140);

        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(10);
        layout.setHgap(10);
        layout.getChildren().addAll(textField, send, listView, messages);
        GridPane.setConstraints(listView, 0, 0);
        GridPane.setConstraints(messages, 1, 0);
        GridPane.setConstraints(send, 0, 1);
        GridPane.setConstraints(textField, 1, 1);

        send.setOnAction(e -> {
            System.out.println(textField.getText());
        });


        scene = new Scene(layout);
        window.setTitle("Clack");
        window.setScene(scene);
        window.show();
    }

    public static void send(GridPane layout) {

    }
}

