package hospital.controller;

import com.jfoenix.controls.JFXToggleButton;
import hospital.Main;
import hospital.model.StorageListWrapper;
import hospital.model.TableConstructor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;

public class MainController {

    /**
     * Ссылка на главное приложение.
     */
    private Main main;

    private StorageListWrapper storage;

    /**
     * Таблица
     */
    @FXML
    private JFXToggleButton toggle;
    @FXML
    private Label fileName;
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
     * Инициализация таблицы. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
        numHospital.setCellValueFactory(cellData -> cellData.getValue().numHospitalProperty().asString());
        surgeon.setCellValueFactory(cellData -> cellData.getValue().surgeonProperty());
        ophthalmologist.setCellValueFactory(cellData -> cellData.getValue().ophthalmologistProperty());
        neurologist.setCellValueFactory(cellData -> cellData.getValue().neurologistProperty());
        lor.setCellValueFactory(cellData -> cellData.getValue().lorProperty());
        cardiologist.setCellValueFactory(cellData -> cellData.getValue().cardiologistProperty());
        endocrinologist.setCellValueFactory(cellData -> cellData.getValue().endocrinologistProperty());
        therapist.setCellValueFactory(cellData -> cellData.getValue().therapistProperty());
    }

    public ObservableList<TableConstructor> hospitalData = FXCollections.observableArrayList();

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
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка.");
            alert.setHeaderText("Строка не выбрана.");
            alert.setContentText("Выберите строку для удаления.");
            alert.showAndWait();
        }
    }

    @FXML
    public void openDoctorsDialog() throws IOException {
        if (storage == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка.");
            alert.setHeaderText("Файл не загружен");
            alert.setContentText("Загрузите файл");
            alert.showAndWait();
            return;
        }

        FXMLLoader Loader = new FXMLLoader(Main.class.getResource("view/Doctors.fxml"));
        Parent root = Loader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setResizable(false);
        stage.setTitle("Доктора");

        DoctorsController controller = Loader.getController(); //получаем контроллер для второй формы
        controller.setStorage(storage);

        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    /**
     * Проверка на укомплектованность
     */
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


    /**
     * Закрывает приложение.
     */
    @FXML
    private void exit() {
        setFilePath(null);
        System.exit(0);
    }


    public void loadIsStaffData(File file) {
        try {
            List<TableConstructor> staffedHospitals = storage.getHospitals().stream()
                    .filter(this::isStaffed)
                    .collect(Collectors.toList());

            hospitalData.clear();
            hospitalData.addAll(staffedHospitals);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setContentText("Не удалось загрузить данные из файла:\n" + file.getPath());
            alert.showAndWait();
        }
    }

    @FXML
    private void loadFilterData() {
        File file = getFilePath();
        if (file != null) {
            if (toggle.isSelected()) {
                toggle.setText("Нажмите чтобы показать весь список");
                loadIsStaffData(file);
            } else {
                toggle.setText("Нажмите чтобы показать только укомплектовынные больницы");
                loadDataFromFile(file);
            }
        }
    }


    @FXML
    private void openPrevFile() {
        File file = getFilePath();
        loadDataFromFile(file);
    }

    /**
     * Загружает информацию об адресатах из указанного файла.
     * Текущая информация будет заменена.
     */
    public void loadDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(StorageListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Чтение XML из файла
            storage = (StorageListWrapper) um.unmarshal(file);

            hospitalData.clear();
            hospitalData.addAll(storage.getHospitals());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Не удалось загрузить данные из файла.");
            alert.setContentText("Не удалось загрузить данные из файла. Возможно файл пустой или повежден.:\n" + file.getPath());
            alert.showAndWait();
        }
    }

    /**
     * Сохраняет текущую информацию об адресатах в указанном файле.
     */
    public void saveDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(StorageListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Обёртываем данные
            StorageListWrapper wrapper = new StorageListWrapper();
            wrapper.setHospitals(hospitalData);

            //сохраняем XML в файл.
            m.marshal(wrapper, file);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Не удалось сохранить данные");
            alert.setContentText("Не удалось сохранить данные в файл:\n" + file.getPath());
            alert.showAndWait();
        }
    }

    /**
     * Создаёт пустую таблицу.
     */
    @FXML
    private void newFile() {
        getHospitalData().clear();
        setFilePath(null);
    }

    /**
     * Открывает файл
     */
    @FXML
    private void openFile() {
        FileChooser fileChooser = new FileChooser();
        // Задаём фильтр расширений
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        // Диалог загрузки файла
        File file = fileChooser.showOpenDialog(main.getPrimaryStage());
        if (file != null) {
            setFilePath(file);
            loadDataFromFile(file);
        }
    }


    /**
     * Сохраняет файл, который открыт.
     * Если файл не открыт, то отображается диалог "Сохранить как".
     */
    @FXML
    private void saveFile() {
        File file = getFilePath();
        if (file != null) {
            saveDataToFile(file);
        } else {
            saveFileAs();
        }
    }

    /**
     * Получает путь до файла
     */
    public File getFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    public void setFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());
            String name = file.getPath();
            fileName.setText(name);
        } else {
            prefs.remove("filePath");

        }
    }

    /**
     * Сохранить как
     */
    @FXML
    private void saveFileAs() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(main.getPrimaryStage());
        if (file != null) {
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            saveDataToFile(file);
        }
    }


}
