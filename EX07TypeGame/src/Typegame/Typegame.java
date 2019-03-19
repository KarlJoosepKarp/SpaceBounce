package Typegame;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class Typegame extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        stage.setTitle("Registration form example");
        Scene scene = new Scene(root);

        // You can replace these components with the ones in other examples to test them
        TextField textFieldEmail = new TextField();
        PasswordField passwordField1 = new PasswordField();
        PasswordField passwordField2 = new PasswordField();
        passwordField2.setPromptText("Please retype your password");
        RadioButton radioButtonMale = new RadioButton("M");
        RadioButton radioButtonFemale = new RadioButton("F");
        ToggleGroup genderToggleGroup = new ToggleGroup();
        radioButtonFemale.setToggleGroup(genderToggleGroup);
        radioButtonMale.setToggleGroup(genderToggleGroup);
        radioButtonMale.setSelected(true);
        ChoiceBox choiceBoxUniversity = new ChoiceBox();
        choiceBoxUniversity.getItems().addAll("TTÜ", "TLÜ", "TÜ");
        Button registerButton = new Button("Register");

        CheckBox checkBoxEmailUpdates = new CheckBox("I would like to receive email updates");
        checkBoxEmailUpdates.setWrapText(true);

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(4);

        // If you replaced any components before, you must also replace the following lines (see JavaFX: Layouts)
        grid.add(new Label("Email: "), 0, 0);
        grid.add(textFieldEmail, 1, 0, 2, 1);
        grid.add(new Label("Password: "), 0, 1);
        grid.add(passwordField1, 1, 1, 2, 1);
        grid.add(passwordField2, 1, 2, 2, 1);
        grid.add(new Label("Gender: "), 0, 3);
        grid.add(radioButtonMale, 1, 3);
        grid.add(radioButtonFemale, 2, 3);
        grid.add(new Label("University: "), 0, 4);
        grid.add(choiceBoxUniversity, 1, 4, 2, 1);
        grid.add(checkBoxEmailUpdates, 0, 5, 3, 1);
        grid.add(registerButton, 1, 6, 2, 1);

        registerButton.setOnAction((ActionEvent e) -> {
            String userPassword = passwordField1.getText();
            if (userPassword.equals(passwordField2.getText())) {
                String userEmail = textFieldEmail.getText();
                String userUniversity = choiceBoxUniversity.valueProperty().getValue().toString();
                String userGender;
                String emailsAllowed;
                if (radioButtonMale.isSelected()) {
                    userGender = "Male";
                } else {
                    userGender = "Female";
                }
                if (checkBoxEmailUpdates.isSelected()) {
                    emailsAllowed = "emails allowed";
                } else {
                    emailsAllowed = "emails not allowed";
                }
                System.out.println("User " + userEmail + " registered with password "
                        + userPassword + " (" + userGender + ", " + userUniversity + ", " + emailsAllowed + ")");
            } else {
                grid.add(new Label("Passwords do not match!"), 0, 7, 3, 1);
                System.out.println("Registration failed: passwords not equal");
            }
        });

        for (Node element: grid.getChildren()) {
            if (element instanceof TextField) {
                ((Region) element).setMinWidth(300.0);
            }
        }

        root.getChildren().add(grid);
        stage.setScene(scene);
        stage.show();
    }
}
