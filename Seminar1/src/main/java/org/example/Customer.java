package org.example;

import java.time.LocalDate;

public class Customer {

    private int id;
    private String username;
    private String email;
    private LocalDate birthday;

    public Customer(int id, String username, String email, LocalDate birthday) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.birthday = birthday;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDate getBirthday() {
        return birthday;
    }


    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return ("------------\nCustomer mit:\nid:" + id
                + "\nusername:" + username
                + "\nemail:" + email
                + "\nbirthday: " + birthday);
    }
}
