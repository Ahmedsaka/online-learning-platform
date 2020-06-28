package io.medalytics.onlinelearningplatform.model.enums;

public enum RoleType {

    ROLE_STUDENT("STUDENT"),
    ROLE_INSTRUCTOR("INSTRUCTOR"),
    ROLE_ADMIN("ADMIN");

    private String value;

    RoleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
