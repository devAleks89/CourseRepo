package models;

import framework.Settings;

import static framework.Utils.random;

public class UserBuilder {

    private static final Settings settings = new Settings();

    public static User admin() {
        User user = new User(settings.getUserLogin(), settings.getUserPassword());
        user.setFirstName("Ivan");
        user.setLastName("Petrov");
        return user;
    }

    public static User randomUser() {
        User newUser = new User();
        newUser.setUsername("username_ " + random());
        newUser.setPassword("password_ " + random());
        newUser.setFirstName("firstName_ " + random());
        newUser.setLastName("lastName_ " + random());
        return newUser;
    }

    public static User userWithInvalidCreds() {
        return new User("admin", "invalid password");
    }
}

