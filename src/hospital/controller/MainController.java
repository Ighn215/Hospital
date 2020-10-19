package hospital.controller;

import hospital.Main;
import hospital.model.TableConstructor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MainController {

    /**
     * Ссылка на главное приложение.
     * */
    private Main main;

    /**
     * Таблица*/
    @FXML
    private TableView<TableConstructor> table;
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

    /**
     * Вызывается главным приложением, которое даёт на себя ссылку.
     */
    public void setMain(Main main) {
        this.main = main;
        // Добавление в таблицу данных из списка
        table.setItems(getHospitalData());
    }

    /**
     * Инициализация класса-контроллера. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
        // Инициализация таблицы.
        numHospital.setCellValueFactory(cellData -> cellData.getValue().numHospitalProperty().asString());
        surgeon.setCellValueFactory(cellData -> cellData.getValue().surgeonProperty());
        ophthalmologist.setCellValueFactory(cellData -> cellData.getValue().ophthalmologistProperty());
        neurologist.setCellValueFactory(cellData -> cellData.getValue().neurologistProperty());
        lor.setCellValueFactory(cellData -> cellData.getValue().lorProperty());
        cardiologist.setCellValueFactory(cellData -> cellData.getValue().cardiologistProperty());
        endocrinologist.setCellValueFactory(cellData -> cellData.getValue().endocrinologistProperty());
        therapist.setCellValueFactory(cellData -> cellData.getValue().therapistProperty());
    }

    private final ObservableList<TableConstructor> hospitalData = FXCollections.observableArrayList();
    public ObservableList<TableConstructor> getHospitalData() {
        return hospitalData;
    }

    @FXML
    private void newStorage() {
        TableConstructor tempHospital = new TableConstructor();
        boolean okClicked = main.openDialog(tempHospital);
        if (okClicked) {
            getHospitalData().add(tempHospital);
        }
    }

    @FXML
    public void editStorage() {
        TableConstructor selectedHospital = table.getSelectionModel().getSelectedItem();
        if (selectedHospital != null) {
            main.openDialog(selectedHospital);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка.");
            alert.setHeaderText("Строка не выбрана.");
            alert.setContentText("Выберите строку для редактирования.");
            alert.showAndWait();
        }
    }

    @FXML
    private void deleteStorage() {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            table.getItems().remove(selectedIndex);
        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка.");
            alert.setHeaderText("Строка не выбрана.");
            alert.setContentText("Выберите строку для удаления.");
            alert.showAndWait();
        }
    }

    /**
    * Проверка на укомплектованность
    * */
    private boolean isStaffed(TableConstructor table) {
        return isNotEmpty(table.getCardiologist())
                && isNotEmpty(table.getSurgeon())
                && isNotEmpty(table.getOphthalmologist())
                && isNotEmpty(table.getNeurologist())
                && isNotEmpty(table.getLor())
                && isNotEmpty(table.getCardiologist())
                && isNotEmpty(table.getEndocrinologist())
                && isNotEmpty(table.getTherapist());
    }
    private boolean isNotEmpty(String str) {
        return str != null && !str.isBlank();
    }
}
