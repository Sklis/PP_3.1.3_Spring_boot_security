package ru.sklis.spring_boot.model;


import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "first_name")
    @NotEmpty(message = "Поле имя не может быть пустым." )
//    @Pattern(regexp = "^[a-zA-Zа-яА-Я]*$", message = "Имя должно содержать только буквы.")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "Поле фамилия не может быть пустой")
//    @Pattern(regexp = "^[a-zA-Zа-яА-Я]*$", message = "Фамилия должна содержать только буквы.")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNamber;

    @Column(name = "email", unique = true)
    @Email(message = "Не корректный ввод Email ")
    private String email;

    public User() {
    }

    public User(String firstName, String lastName, String phoneNamber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNamber = phoneNamber;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getPhoneNamber() {
        return phoneNamber;
    }

    public void setPhoneNamber(String phoneNamber) {
        this.phoneNamber = phoneNamber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNamber='" + phoneNamber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}


