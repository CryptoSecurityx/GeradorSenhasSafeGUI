package com.example.geradorsenhassafegui;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HelloApplication extends Application {

    private TextField lengthField;
    private TextField passwordField;
    private ComboBox<String> complexityComboBox;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gerador de Senhas Seguras");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setStyle("-fx-background-color: #333333;");

        Label lengthLabel = new Label("Tamanho:");
        lengthLabel.setTextFill(Color.WHITE);
        GridPane.setConstraints(lengthLabel, 0, 0);

        lengthField = new TextField();
        lengthField.setPromptText("Digite aqui..");
        lengthField.setText("12");
        GridPane.setConstraints(lengthField, 1, 0);

        complexityComboBox = new ComboBox<>();
        complexityComboBox.setItems(FXCollections.observableArrayList(
                "Apenas letras maiúsculas e minúsculas",
                "Letras maiúsculas, minúsculas e números",
                "Letras maiúsculas, minúsculas, números e caracteres especiais",
                "Senhas seguras da internet"
        ));
        complexityComboBox.setValue("Apenas letras maiúsculas e minúsculas");
        GridPane.setConstraints(complexityComboBox, 0, 1, 2, 1);

        Button generateButton = new Button("Gerar Senha");
        generateButton.setStyle("-fx-background-color: #666666; -fx-text-fill: white;");
        generateButton.setOnAction(e -> generatePassword());
        GridPane.setConstraints(generateButton, 1, 2);

        Label passwordLabel = new Label("Senha Gerada:");
        passwordLabel.setTextFill(Color.WHITE);
        GridPane.setConstraints(passwordLabel, 0, 3);

        passwordField = new TextField();
        passwordField.setEditable(false);
        passwordField.setStyle("-fx-control-inner-background: #666666; -fx-text-fill: white;");
        GridPane.setConstraints(passwordField, 1, 3);

        grid.getChildren().addAll(lengthLabel, lengthField, complexityComboBox, generateButton, passwordLabel, passwordField);

        VBox root = new VBox(grid);
        root.setStyle("-fx-background-color: #222222; -fx-padding: 10;");
        Scene scene = new Scene(root, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void generatePassword() {
        try {
            int length = Integer.parseInt(lengthField.getText());
            String complexity = complexityComboBox.getValue();
            String password;

            if (complexity.equals("Senhas seguras da internet")) {
                password = generateInternetSafePassword();
            } else {
                password = generateSecurePassword(length, complexity);
            }

            passwordField.setText(password);
        } catch (NumberFormatException e) {
            passwordField.setText("Falha.. tamanho nao especificado.");
        }
    }

    private String generateSecurePassword(int length, String complexity) {
        StringBuilder allChars = new StringBuilder();
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        if (complexity.equals("Apenas letras maiúsculas e minúsculas")) {
            allChars.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
        } else if (complexity.equals("Letras maiúsculas, minúsculas e números")) {
            allChars.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789");
        } else if (complexity.equals("Letras maiúsculas, minúsculas, números e caracteres especiais")) {
            allChars.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+<>?");
        }

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allChars.length());
            password.append(allChars.charAt(randomIndex));
        }

        return password.toString();
    }

    private String generateInternetSafePassword() {
        List<String> internetSafePasswords = new ArrayList<>();
        internetSafePasswords.add("Tr0ub4dor&3");
        internetSafePasswords.add("br0cc0li7");
        internetSafePasswords.add("Qwer123$!");
        internetSafePasswords.add("Pr0c3ssR4nd0m!");

        Random random = new Random();
        int index = random.nextInt(internetSafePasswords.size());

        return internetSafePasswords.get(index);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

