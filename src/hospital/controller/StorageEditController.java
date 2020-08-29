package hospital.controller;

import hospital.model.TableConstructor;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StorageEditController {

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


    /**
     * Выбираем строку, информацию в которой меняем.
     */
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

    //Cancel Button
    @FXML
    private javafx.scene.control.Button closeButton;
    public void closeDialog() {
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }


}
