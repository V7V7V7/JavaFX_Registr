package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField pass_field;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField signUpName;

    @FXML
    private TextField signUpLastName;

    @FXML
    private CheckBox signUpChekMale;

    @FXML
    private CheckBox signUpChekFemale;

    @FXML
    private TextField signUpCountry;

    @FXML
    void initialize() {



            signUpButton.setOnAction(actionEvent -> {
                signUpNewUser();
                signUpButton.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/sample.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();


            });
    }

    private void signUpNewUser() {
        DateBaseHandler dbHandler = new DateBaseHandler();
        String firstName = signUpName.getText();
        String lastName = signUpLastName.getText();
        String userName = login_field.getText();
        String password = pass_field.getText();
        String location = signUpCountry.getText();
        String gender = "";
        if (signUpChekMale.isSelected()) {
            gender = "Мужской";
        } else
            gender = "женский";
        User user = new User(firstName, lastName, userName, password, location, gender);

        dbHandler.signUpUser(user);
    }
}
