package hospital.model;

public enum Profession {

    CARDIOLOGIST("Кардиолог"),
    ENDOCRINOLOGIST("Эндокринолог"),
    LOR("Лор"),
    NEUROLOGIST("Невролог"),
    OPHTHALMOLOGIST("Офтальмолог"),
    SURGEON("Хирург"),
    THERAPIST("Терапевт");

    private final String text;

    private Profession(String text) {
        this.text = text;
    }

    public String toString() {
        return text;
    }

}
