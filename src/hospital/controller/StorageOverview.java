package hospital.controller;

import com.jfoenix.controls.JFXToggleButton;
import hospital.model.MainTableConstructor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StorageOverview {

    private ObservableList<MainTableConstructor> hospital = FXCollections.observableArrayList();

    //Table
    @FXML
    private JFXToggleButton toggle;
    @FXML
    private TableView<MainTableConstructor> table;
    @FXML
    private TableColumn<MainTableConstructor, String> numHospital;
    @FXML
    private TableColumn<MainTableConstructor, String> surgeon;
    @FXML
    private TableColumn<MainTableConstructor, String> ophthalmologist;
    @FXML
    private TableColumn<MainTableConstructor, String> neurologist;
    @FXML
    private TableColumn<MainTableConstructor, String> lor;
    @FXML
    private TableColumn<MainTableConstructor, String> cardiologist;
    @FXML
    private TableColumn<MainTableConstructor, String> endocrinologist;
    @FXML
    private TableColumn<MainTableConstructor, String> therapist;

    // инициализируем форму данными
    @FXML
    private void initialize() {
        initData();

        // устанавливаем тип и значение которое должно хранится в колонке
        numHospital.setCellValueFactory(new PropertyValueFactory<MainTableConstructor, String>("numHospital"));
        surgeon.setCellValueFactory(new PropertyValueFactory<MainTableConstructor, String>("surgeon"));
        ophthalmologist.setCellValueFactory(new PropertyValueFactory<MainTableConstructor, String>("ophthalmologist"));
        neurologist.setCellValueFactory(new PropertyValueFactory<MainTableConstructor, String>("neurologist"));
        lor.setCellValueFactory(new PropertyValueFactory<MainTableConstructor, String>("lor"));
        cardiologist.setCellValueFactory(new PropertyValueFactory<MainTableConstructor, String>("cardiologist"));
        endocrinologist.setCellValueFactory(new PropertyValueFactory<MainTableConstructor, String>("endocrinologist"));
        therapist.setCellValueFactory(new PropertyValueFactory<MainTableConstructor, String>("therapist"));

        // заполняем таблицу данными
        table.setItems(hospital);
    }

    // подготавливаем данные для таблицы
    // вы можете получать их с базы данных
    private void initData() {
        hospital.add(new MainTableConstructor(1,"qweasd", "qweasd", "qweasd", "qweasd", "qweasd", "qweasd", "qweasd"));
        hospital.add(new MainTableConstructor(2,"qweasd", "qweasd", "qweasd", "qweasd", "qweasd", "qweasd", "qweasd"));
        hospital.add(new MainTableConstructor(3,"qweasd", "qweasd", "qweasd", "qweasd", "qweasd", "qweasd", "qweasd"));
        hospital.add(new MainTableConstructor(4,"qweasd", "qweasd", "qweasd", "qweasd", "qweasd", "qweasd", "qweasd"));
        hospital.add(new MainTableConstructor(5,"qweasd", "qweasd", "qweasd", "qweasd", "qweasd", "qweasd", "qweasd"));
    }

}
