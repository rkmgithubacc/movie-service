package com.rkmgithubacc.mba.moviems.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", precision = 10)
    private int userID;

    @Column(name = "first_name")
    @NotBlank(message = "First Name must not be blank")
    @NotNull(message = "First Name must not be null")
    @Pattern(regexp = "^[A-Z][a-zA-Z]{0,19}$",
            message = "First Name must start with an upper case alphabet and followed by 0-19 alphabets")
    @Size(max = 20)
    private String firstName;

    @Column(name = "last_name")
    @Nullable
    @Pattern(regexp = "^[A-Z][a-zA-Z]{0,19}$",
            message = "Last Name must start with an upper case alphabet and followed by 0-19 alphabets")
    @Size(max = 20)
    private String lastName;

    @Column(name = "username", unique = true)
    @NotBlank(message = "User Name must not be blank")
    @NotNull(message = "User Name must not be null")
    @Pattern(regexp = "^[a-zA-Z]([.-](?![.-])|[a-zA-Z0-9]){0,18}[a-zA-Z0-9]$",
            message = "User Name must start with an alphabet, \\n" +
                    "must end with an alphanumeric character, \\n" +
                    "can have zero or more non-consecutive dot or hyphen in-between, \\n" +
                    "can have zero or more alphanumeric characters in-between, \\n" +
                    "and can be maximum 20 characters long.")
    @Size(max = 20)
    private String userName;

    @Column(name = "password")
    @NotBlank(message = "Password must not be blank")
    @NotNull(message = "Password must not be null")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{5,20}$",
            message = "Password must contain at least one digit, \\n" +
                    "must contain at least one lowercase alphabet, \\n" +
                    "must contain at least one uppercase alphabet, \\n" +
                    "must contain at least one special character, \\n" +
                    "must contain a length of at-least 5 characters and a maximum of 20 characters.")
    @Size(min = 5, max = 20)
    private String password;

    @Column(name = "date_of_birth")
//    @NotBlank(message = "Date of birth must not be blank")
    @NotNull(message = "Date of birth must not be null")
//    @Pattern(regexp = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$",
//            message = "Date of Birth should be in YYYY-MM-DD format")
    private LocalDate dateOfBirth;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_contact", joinColumns = @JoinColumn(name = "user_ID"))
    @Column(name = "phone_number", nullable = false)
//    @NotBlank(message = "Phone Number must not be blank")
//    @Pattern(regexp = "^\\d{10}$", message = "Phone number should have 10 digits")
    private Set<String> phoneNumbersSet = new HashSet<>();

    public User() {
    }

    public User(String firstName, @Nullable String lastName, String userName, String password,
                LocalDate dateOfBirth, Set<String> phoneNumbersSet) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumbersSet = phoneNumbersSet;
    }

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

    @Nullable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@Nullable String lastName) {
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
        return "User{" +
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

        User user = (User) o;

        return userID == user.userID;
    }

    @Override
    public int hashCode() {
        return userID;
    }
}
