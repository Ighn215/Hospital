package hospital.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class TableConstructor {

    private final IntegerProperty numHospital;
    private final StringProperty surgeon;
    private final StringProperty ophthalmologist;
    private final StringProperty neurologist;
    private final StringProperty lor;
    private final StringProperty cardiologist;
    private final StringProperty endocrinologist;
    private final StringProperty therapist;


    /**
     * Конструктор по умолчанию.
     */
    public TableConstructor() {
        this(0, null, null, null, null, null, null, null);
    }

    /**
     * Конструктор.
     */
    public TableConstructor(Integer numHospital, String surgeon, String ophthalmologist, String neurologist, String lor, String cardiologist, String endocrinologist, String therapist) {
        this.numHospital = new SimpleIntegerProperty(numHospital);
        this.surgeon = new SimpleStringProperty(surgeon);
        this.ophthalmologist = new SimpleStringProperty(ophthalmologist);
        this.neurologist = new SimpleStringProperty(neurologist);
        this.lor = new SimpleStringProperty(lor);
        this.cardiologist = new SimpleStringProperty(cardiologist);
        this.endocrinologist = new SimpleStringProperty(endocrinologist);
        this.therapist = new SimpleStringProperty(therapist);

    }

    public Integer getNumHospital() {
        return numHospital.get();
    }

    public void setNumHospital(Integer numHospital) {
        this.numHospital.set(numHospital);
    }

    public IntegerProperty numHospitalProperty() {
        return numHospital;
    }

    public String getSurgeon() {
        return surgeon.get();
    }

    public void setSurgeon(String lastName) {
        this.surgeon.set(lastName);
    }

    public StringProperty surgeonProperty() {
        return surgeon;
    }

    public String getOphthalmologist() {
        return ophthalmologist.get();
    }

    public void setOphthalmologist(String ophthalmologist) {
        this.ophthalmologist.set(ophthalmologist);
    }

    public StringProperty ophthalmologistProperty() {
        return ophthalmologist;
    }

    public String getNeurologist() {
        return neurologist.get();
    }

    public StringProperty neurologistProperty() {
        return neurologist;
    }

    public void setNeurologist(String neurologist) {
        this.neurologist.set(neurologist);
    }

    public String getLor() {
        return lor.get();
    }

    public StringProperty lorProperty() {
        return lor;
    }

    public void setLor(String lor) {
        this.lor.set(lor);
    }

    public String getCardiologist() {
        return cardiologist.get();
    }

    public StringProperty cardiologistProperty() {
        return cardiologist;
    }

    public void setCardiologist(String cardiologist) {
        this.cardiologist.set(cardiologist);
    }

    public String getEndocrinologist() {
        return endocrinologist.get();
    }

    public StringProperty endocrinologistProperty() {
        return endocrinologist;
    }

    public void setEndocrinologist(String endocrinologist) {
        this.endocrinologist.set(endocrinologist);
    }

    public String getTherapist() {
        return therapist.get();
    }

    public StringProperty therapistProperty() {
        return therapist;
    }

    public void setTherapist(String therapist) {
        this.therapist.set(therapist);
    }
}