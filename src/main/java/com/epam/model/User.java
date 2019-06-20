package com.epam.model;

import java.io.Serializable;
import java.util.*;

public class User implements Serializable {
    private String username;
    private String password;
    private Role role;
    private List<Role> roles;

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        setRolesByRole(role);
    }

    public User() {
    }

    private void setRolesByRole(Role role) {
        Role ADMIN_ROLE = new Role("administrator");
        Role MODERATOR_ROLE = new Role("moderator");
        Role USER_ROLE = new Role("user");

        this.role = role;

        if (ADMIN_ROLE.equals(role)) {
            roles = Arrays.asList(ADMIN_ROLE, MODERATOR_ROLE, USER_ROLE);
        } else if (MODERATOR_ROLE.equals(role)) {
            roles = Arrays.asList(MODERATOR_ROLE, USER_ROLE);
        } else if (USER_ROLE.equals(role)) {
            roles = Collections.singletonList(USER_ROLE);
        }
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        setRolesByRole(role);
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
                .add("roles=" + roles)
                .toString();
    }
}
