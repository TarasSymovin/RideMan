package entity;

public final class User {
    private static User instance;
    public Employee value;

    public User(Employee value) {
        this.value = value;
    }

    public static User getInstance(Employee value) {
        if (instance == null) {
            instance = new User(value);
        }
        return instance;
    }
}
