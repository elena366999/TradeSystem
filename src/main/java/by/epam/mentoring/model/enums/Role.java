package by.epam.mentoring.model.enums;

public enum Role {

    ROLE_USER("User"), ROLE_ADMIN("Admin");

    private String value;

    Role(String value) {
        this.value = value;
    }

    Role() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
