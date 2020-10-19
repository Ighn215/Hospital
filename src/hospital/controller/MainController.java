package hospital.controller;

import com.jfoenix.controls.JFXToggleButton;
import hospital.Main;
import hospital.model.StorageListWrapper;
import hospital.model.TableConstructor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class MainController {

    /**
     * Ссылка на главное приложение.
     */
    private Main main;

    /**
     * Таблица
     */
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


    public void clearData() {
        List<TableConstructor> staffedHospitals = getHospitalData().stream()
                .filter(this::isStaffed)
                .collect(Collectors.toList());
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
    private void filterData() {
        toggle.setOnAction(event -> {
            if (toggle.isSelected()) {
                getHospitalData().clear();
            } else {
                System.out.println("WEWEW");
            }
        });
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
        System.exit(0);
    }

    /**
     * Загружает информацию об адресатах из указанного файла.
     * Текущая информация об адресатах будет заменена.
     */
    public void loadPersonDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(StorageListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Чтение XML из файла
            StorageListWrapper wrapper = (StorageListWrapper) um.unmarshal(file);

            hospitalData.clear();
            hospitalData.addAll(wrapper.getHospitals());
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
    public void savePersonDataToFile(File file) {
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
            loadPersonDataFromFile(file);
        }
    }

    /**
     * Сохраняет файл, который открыт.
     * Если файл не открыт, то отображается диалог "Сохранить как".
     */
    @FXML
    private void saveFile() {
        File personFile = main.getFilePath();
        if (personFile != null) {
            savePersonDataToFile(personFile);
        } else {
            saveFileAs();
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
            savePersonDataToFile(file);
        }
    }



}
