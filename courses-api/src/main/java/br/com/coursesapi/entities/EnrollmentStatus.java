package br.com.coursesapi.entities;

public enum EnrollmentStatus {
    ACTIVE(1), LOCKED(2), FINISHED(3);
    private final int value;

    private EnrollmentStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
