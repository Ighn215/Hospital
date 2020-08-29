package hospital.controller;

import com.jfoenix.controls.JFXToggleButton;
import hospital.Main;
import hospital.model.TableConstructor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MainController {

    //Ссылка на главный класс
    private Main main = new Main();
    private Stage primaryStage;

    private final ObservableList<TableConstructor> hospital = FXCollections.observableArrayList();

    //Table
    @FXML
    private JFXToggleButton toggle;
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

    // инициализируем форму данными
    @FXML
    private void initialize() {
        initData();

        // устанавливаем тип и значение которое должно хранится в колонке
        numHospital.setCellValueFactory(new PropertyValueFactory<TableConstructor, String>("numHospital"));
        surgeon.setCellValueFactory(new PropertyValueFactory<TableConstructor, String>("surgeon"));
        ophthalmologist.setCellValueFactory(new PropertyValueFactory<TableConstructor, String>("ophthalmologist"));
        neurologist.setCellValueFactory(new PropertyValueFactory<TableConstructor, String>("neurologist"));
        lor.setCellValueFactory(new PropertyValueFactory<TableConstructor, String>("lor"));
        cardiologist.setCellValueFactory(new PropertyValueFactory<TableConstructor, String>("cardiologist"));
        endocrinologist.setCellValueFactory(new PropertyValueFactory<TableConstructor, String>("endocrinologist"));
        therapist.setCellValueFactory(new PropertyValueFactory<TableConstructor, String>("therapist"));

        // заполняем таблицу данными
        table.setItems(hospital);
    }

    // подготавливаем данные для таблицы
    // вы можете получать их с базы данных
    private void initData() {
        hospital.add(new TableConstructor(1,"qweasd", "qweasd", "qweasd", "qweasd", "qweasd", "qweasd", "qweasd"));
        hospital.add(new TableConstructor(2,"qweasd", "qweasd", "qweasd", "qweasd", "qweasd", "qweasd", "qweasd"));
        hospital.add(new TableConstructor(3,"qweasd", "qweasd", "qweasd", "qweasd", "qweasd", "qweasd", "qweasd"));
        hospital.add(new TableConstructor(4,"qweasd", "qweasd", "qweasd", "qweasd", "qweasd", "qweasd", "qweasd"));
        hospital.add(new TableConstructor(5,"qweasd", "qweasd", "qweasd", "qweasd", "qweasd", "qweasd", "qweasd"));
    }

    @FXML
    public void newStorage(ActionEvent event) {
        main.openAddDialog();
    }

    @FXML
    public void editStorage(ActionEvent event) {
        main.openEditDialog();
    }



}
