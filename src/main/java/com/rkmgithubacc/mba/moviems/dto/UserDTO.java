package com.rkmgithubacc.mba.moviems.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class UserDTO {
    private int userID;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private LocalDate dateOfBirth;
    private Set<String> phoneNumbersSet = new HashSet<>();

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<String> getPhoneNumbersSet() {
        return phoneNumbersSet;
    }

    public void setPhoneNumbersSet(Set<String> phoneNumbersSet) {
        this.phoneNumbersSet = phoneNumbersSet;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userID=" + userID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumbersSet=" + phoneNumbersSet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDTO userDTO = (UserDTO) o;

        return userID == userDTO.userID;
    }

    @Override
    public int hashCode() {
        return userID;
    }
}
