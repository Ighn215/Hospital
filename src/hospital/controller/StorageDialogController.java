package hospital.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import hospital.model.TableConstructor;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StorageDialogController {

    private TableConstructor table;

    @FXML
    private TextField numHospitalField;
    @FXML
    private TextField surgeonField;
    @FXML
    private TextField ophthalmologistField;
    @FXML
    private TextField neurologistField;
    @FXML
    private TextField lorField;
    @FXML
    private TextField cardiologistField;
    @FXML
    private TextField endocrinologistField;
    @FXML
    private TextField therapistField;

    public void setHospital(TableConstructor table) {
        this.table = table;
        numHospitalField.setText(Integer.toString(table.getNumHospital()));
        surgeonField.setText(table.getSurgeon());
        ophthalmologistField.setText(table.getOphthalmologist());
        neurologistField.setText(table.getNeurologist());
        lorField.setText(table.getLor());
        cardiologistField.setText(table.getCardiologist());
        endocrinologistField.setText(table.getEndocrinologist());
        therapistField.setText(table.getTherapist());
    }

    private boolean okClicked = false;

    /**
     * true, если пользователь кликнул OK, в другом случае false.
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Добавление данных
     */
    @FXML
    public void addData() {
        if (isInputValid()) {
            table.setNumHospital(Integer.parseInt(numHospitalField.getText()));
            table.setSurgeon(surgeonField.getText());
            table.setOphthalmologist(ophthalmologistField.getText());
            table.setNeurologist(neurologistField.getText());
            table.setLor(lorField.getText());
            table.setCardiologist(cardiologistField.getText());
            table.setEndocrinologist(endocrinologistField.getText());
            table.setTherapist(therapistField.getText());
            okClicked = true;
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * ОТмена
     */
    @FXML
    private javafx.scene.control.Button closeButton;

    public void closeDialog() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Проверяет пользовательский ввод в текстовых полях.
     *
     * @return true, если пользовательский ввод корректен
     */
    private boolean isInputValid() {
        String errorMessage = "";

        String numHospital = numHospitalField.getText();
        if (!isNumberValid(numHospital)) {
            errorMessage += "Некорректный номер поликлиники. Для ввода допускаются только цифры.\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка ввода");
            alert.setHeaderText("Ошибка ввода");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    private boolean isNumberValid(String numHospital) {
        return numHospital != null && numHospital.length() > 0 && !hasLetter(numHospital);
    }

    private boolean hasLetter(String numHospital) {
        String digits = "0123456789";
        for (int i = 0; i < numHospital.length(); i++) {
            if (!digits.contains("" + numHospital.charAt(i))) {
                return true;
            }
        }
        return false;
    }


}
