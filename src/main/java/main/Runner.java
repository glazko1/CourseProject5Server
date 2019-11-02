package main;

import util.config.AppConfig;
import controller.ServerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Runner extends Application implements Runnable {

    private static ServerController serverController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/server.fxml"));
        Parent root = loader.load();
        serverController = loader.getController();
        primaryStage.setTitle("Server application");
        primaryStage.setScene(new Scene(root, 600, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        AppConfig.getInstance().init();
        new Thread(new Runner()).start();
        launch(args);
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(5000, 10);
            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(socket);
                new Thread(handler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void incrementConnectionsNumber() {
        serverController.incrementConnectionsNumber();
    }

    public static void incrementUsersOnline() {
        serverController.incrementUsersOnline();
    }

    public static void decrementUsersOnline() {
        serverController.decrementUsersOnline();
    }
}