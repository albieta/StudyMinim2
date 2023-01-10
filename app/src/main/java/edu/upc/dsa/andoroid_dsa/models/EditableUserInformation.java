package edu.upc.dsa.andoroid_dsa.models;

public class EditableUserInformation {
    String idUser;
    String username;
    String surname;
    String birthday;
    String email;
    String password;

    public EditableUserInformation(){}

    public EditableUserInformation(String idUser, String username, String surname, String birthday, String email, String password) {
        this.idUser = idUser;
        this.username = username;
        this.surname = surname;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
