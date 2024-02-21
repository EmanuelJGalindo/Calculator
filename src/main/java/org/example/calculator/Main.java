package org.example.calculator;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{
    private Button binariButton, decimalButton, octalButton, hexadecimalButton;
    private TextField inputField;
    private Button convertButton, backButton;
    private Label labelResult;

    @Override
    public void start(Stage primaryStage) {
        binariButton = new Button("Binario");
        binariButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        decimalButton = new Button("Decimal");
        decimalButton.setStyle("-fx-background-color: #008CBA; -fx-text-fill: white; -fx-font-weight: bold;");
        octalButton = new Button("Octal");
        octalButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;");
        hexadecimalButton = new Button("Hexadecimal");
        hexadecimalButton.setStyle("-fx-background-color: #ff9800; -fx-text-fill: white; -fx-font-weight: bold;");

        inputField = new TextField();
        convertButton = new Button("Convertir");
        backButton = new Button("Volver");
        labelResult = new Label();

        inputField.setStyle("-fx-font-size: 14px;");
        convertButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        backButton.setStyle("-fx-background-color: #008CBA; -fx-text-fill: white; -fx-font-weight: bold;");
        labelResult.setStyle("-fx-font-size: 14px;");

        binariButton.setOnAction(e -> showTextField("Binario"));
        decimalButton.setOnAction(e -> showTextField("Decimal"));
        octalButton.setOnAction(e -> showTextField("Octal"));
        hexadecimalButton.setOnAction(e -> showTextField("Hexadecimal"));
        convertButton.setOnAction(e -> convertNumber());
        backButton.setOnAction(e -> back());

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        HBox buttonBox = new HBox(10, binariButton, decimalButton, octalButton, hexadecimalButton);
        buttonBox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(10, inputField, convertButton, labelResult, backButton);
        vBox.setAlignment(Pos.CENTER);

        grid.add(buttonBox, 0, 0);
        grid.add(vBox, 0, 1);

        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Conversor Numérico");
        primaryStage.show();
    }

    private void showTextField(String sistemaSeleccionado) {
        binariButton.setVisible(false);
        decimalButton.setVisible(false);
        octalButton.setVisible(false);
        hexadecimalButton.setVisible(false);

        inputField.setVisible(true);
        inputField.clear();

        convertButton.setVisible(true);
        convertButton.setText("Convertir a " + sistemaSeleccionado);
        backButton.setVisible(true);
    }




    private void convertNumber() {
        try {
            int number = Integer.parseInt(inputField.getText(), obtainSelection());

            labelResult.setText("Binario: " + Integer.toBinaryString(number) +
                    "\nDecimal: " + Integer.toString(number) +
                    "\nOctal: " + Integer.toOctalString(number) +
                    "\nHexadecimal: " + Integer.toHexString(number));
        } catch (NumberFormatException e) {
            labelResult.setText("Error: Ingrese un número válido.");
        }
    }

    private void back() {
        binariButton.setVisible(true);
        decimalButton.setVisible(true);
        octalButton.setVisible(true);
        hexadecimalButton.setVisible(true);

        inputField.setVisible(false);
        convertButton.setVisible(false);
        labelResult.setText("");
        backButton.setVisible(false);
    }

    private int obtainSelection() {
        if (convertButton.getText().contains("Binario")) {
            return 2;
        } else if (convertButton.getText().contains("Decimal")) {
            return 10;
        } else if (convertButton.getText().contains("Octal")) {
            return 8;
        } else if (convertButton.getText().contains("Hexadecimal")) {
            return 16;
        }
        return 10;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
