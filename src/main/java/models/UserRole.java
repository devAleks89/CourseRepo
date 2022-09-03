package models;

public enum UserRole {
    USER("USER"),
    DEVELOPER("DEVELOPER");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getUserRole() {
        return role;
    }
}

