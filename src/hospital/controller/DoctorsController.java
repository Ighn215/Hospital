package hospital.controller;

import hospital.model.Profession;
import hospital.model.StorageListWrapper;
import hospital.model.TableConstructor;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DoctorsController {

    private StorageListWrapper storage;

    @FXML
    private ComboBox<Profession> specialist;
    @FXML
    private TableView<Specialist> doctorsTable;
    @FXML
    private TableColumn<Specialist, String> doctor;
    @FXML
    private TableColumn<Specialist, String> hospitalNum;

    public void setStorage(StorageListWrapper storage) {
        this.storage = storage;
    }

    @FXML
    public void initialize() {
        specialist.setItems(FXCollections.observableArrayList());
        specialist.getItems().addAll(Profession.values());

        doctor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        hospitalNum.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHospitalNum().toString()));

    }

    public void showSpecialists(ActionEvent actionEvent) {
        Profession value = specialist.getValue();
        List<Specialist> collect = storage.getHospitals().stream()
                .map(hospital -> getSpecialist(hospital, value))
                .filter(specialist-> Objects.nonNull(specialist.getLastName()))
                .filter(specialist-> !specialist.getLastName().isEmpty())
                .collect(Collectors.toList());
        doctorsTable.setItems(FXCollections.observableArrayList());
        doctorsTable.getItems().addAll(collect);
    }

    private Specialist getSpecialist(TableConstructor hospital, Profession value) {
        return switch (value) {
            case LOR -> new Specialist(hospital.getLor(), hospital.getNumHospital());
            case SURGEON -> new Specialist(hospital.getSurgeon(), hospital.getNumHospital());
            case THERAPIST -> new Specialist(hospital.getTherapist(), hospital.getNumHospital());
            case NEUROLOGIST -> new Specialist(hospital.getNeurologist(), hospital.getNumHospital());
            case CARDIOLOGIST -> new Specialist(hospital.getCardiologist(), hospital.getNumHospital());
            case ENDOCRINOLOGIST -> new Specialist(hospital.getEndocrinologist(), hospital.getNumHospital());
            case OPHTHALMOLOGIST -> new Specialist(hospital.getOphthalmologist(), hospital.getNumHospital());
        };
    }

    private static final class Specialist {
        private String lastName;
        private Integer hospitalNum;

        public Specialist(String lastName, Integer hospitalNum) {
            this.lastName = lastName;
            this.hospitalNum = hospitalNum;
        }

        public String getLastName() {
            return lastName;
        }

        public Integer getHospitalNum() {
            return hospitalNum;
        }
    }
}
