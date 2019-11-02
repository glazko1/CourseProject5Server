package controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ServerController {

    @FXML
    private Text connectionsNumber;

    @FXML
    private Text usersOnline;

    @FXML
    private void initialize() {}

    public void incrementConnectionsNumber() {
        connectionsNumber.setText(String.valueOf(Integer.parseInt(connectionsNumber.getText()) + 1));
    }

    public void incrementUsersOnline() {
        usersOnline.setText(String.valueOf(Integer.parseInt(usersOnline.getText()) + 1));
    }

    public void decrementUsersOnline() {
        usersOnline.setText(String.valueOf(Integer.parseInt(usersOnline.getText()) - 1));
    }
}