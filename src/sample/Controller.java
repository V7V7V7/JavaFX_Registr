package sample;

import anime.Animation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField pass_field;

    @FXML
    private Button authsignbutton;

    @FXML
    private Button loginSignupButton;

    @FXML
    void initialize() {
        authsignbutton.setOnAction(actionEvent -> {
            String loginText = login_field.getText().trim();
            String loginPass = pass_field.getText().trim();
            if (!loginText.equals("") && !loginPass.equals("")) {
                loginUser(loginText, loginPass);
            } else
                System.out.println("Login or password is empty");

        });


        loginSignupButton.setOnAction(actionEvent -> {
            newScene("/sample/signUp.fxml");
        });
    }

    private void loginUser(String loginText, String loginPass) {
        DateBaseHandler dbHandler = new DateBaseHandler();
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(loginPass);
        ResultSet result = dbHandler.getUser(user);

        int counter = 0;
        try {
            while (result.next()) {
                counter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (counter >= 1) {
            newScene("/sample/app.fxml");
        } else {
            Animation userLoginAnim = new Animation(login_field);
            Animation userPassAnim = new Animation(pass_field);
            userLoginAnim.play();
            userPassAnim.play();
        }
    }
    public void newScene(String window) {
        loginSignupButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();



    }
}
