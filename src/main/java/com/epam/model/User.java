package com.epam.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class User implements Serializable {
    private static final Role ADMIN_ROLE = new Role("Administrator");
    private static final Role MODERATOR_ROLE = new Role("Moderator");
    private static final Role USER_ROLE = new Role("User");
    private String username;
    private String password;
    private Role role;

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public List<Role> getRoles() {
        if (ADMIN_ROLE.equals(role)) {
            return Arrays.asList(ADMIN_ROLE, MODERATOR_ROLE, USER_ROLE);
        } else if (MODERATOR_ROLE.equals(role)) {
            return Arrays.asList(MODERATOR_ROLE, USER_ROLE);
        } else if (USER_ROLE.equals(role)) {
            return Arrays.asList(USER_ROLE);
        }
        return null;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("username='" + username + "'")
                .add("password='" + password + "'")
                .add("role=" + role)
                .toString();
    }
}
