package hospital.controller;

import com.jfoenix.controls.JFXToggleButton;
import hospital.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import hospital.model.TableConstructor;
import javafx.stage.Stage;


public class StorageAddDialogController {

    private Main main = new Main();

    //Table
    @FXML
    private JFXToggleButton toggle;
    @FXML
    private TableView<TableConstructor> hospitalTable;
    @FXML
    private TableColumn<TableConstructor, String> numHospital;
    @FXML
    private TableColumn<TableConstructor, String> surgeon;
    @FXML
    private TableColumn<TableConstructor, String> ophthalmologist;
    @FXML
    private TableColumn<TableConstructor, String> neurologist;
    @FXML
    private TableColumn<TableConstructor, String> lor;
    @FXML
    private TableColumn<TableConstructor, String> cardiologist;
    @FXML
    private TableColumn<TableConstructor, String> endocrinologist;
    @FXML
    private TableColumn<TableConstructor, String> therapist;


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
