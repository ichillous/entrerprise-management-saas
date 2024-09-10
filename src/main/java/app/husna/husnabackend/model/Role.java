package app.husna.husnabackend.model;

public enum Role {
    ADMIN,
    ORG_ADMIN,
    TEACHER,
    PARENT,
    STUDENT;

    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
