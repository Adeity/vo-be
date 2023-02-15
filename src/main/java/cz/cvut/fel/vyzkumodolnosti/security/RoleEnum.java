package cz.cvut.fel.vyzkumodolnosti.security;

public enum RoleEnum {
    ADMIN("ROLE_ADMIN"), READER("ROLE_READER"), USER("ROLE_USER"), GUEST("ROLE_GUEST");

    private final String name;

    RoleEnum(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
