package edu.upc.dsa.andoroid_dsa.models;

public class User {
    private String idUser;
    private String name;
    private String surname;
    private String birthday;
    private String email;
    private String password;
    private Boolean currentlyPlaying;
    private Integer coins;
    private Integer experience;
    private String profilePicture;

    public User(){}

    public User(String idUser, String name, String surname, String birthday, String email, String password, Boolean currentlyPlaying, Integer coins, Integer experience, String profilePicture){
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.currentlyPlaying = currentlyPlaying;
        this.coins = coins;
        this.experience = experience;
        this.profilePicture = profilePicture;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Boolean getCurrentlyPlaying() {
        return currentlyPlaying;
    }

    public void setCurrentlyPlaying(Boolean currentlyPlaying) {
        this.currentlyPlaying = currentlyPlaying;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}