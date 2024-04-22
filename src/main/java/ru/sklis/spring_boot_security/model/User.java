package ru.sklis.spring_boot_security.model;



import lombok.Data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "first_name")
    @NotEmpty(message = "Поле не может быть пустым.")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]*$", message = "Имя должно содержать только буквы.")
    @Size(min = 2, message = "Минимальная длина имени - 2 символа.")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "Поле не может быть пустой")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]*$", message = "Фамилия должна содержать только буквы.")
    @Size(min = 2, message = "Минимальная длина фамилии - 2 символа.")
    private String lastName;

    @Column(name = "email")
    @Email(message = "Не корректный ввод Email ")
    private String email;

    @Column(name = "username", unique = true)
    @NotEmpty(message = "Поле не может быть пустой")
    @Size(min = 4, message = "Минимальная длина - 4 символа.")
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "Поле фамилия не может быть пустой")
    @Size(min = 4, message = "Минимальная длина - 6 символа.")
    private String password;


    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roles;

    public User() {
    }

    public User(String firstName, String lastName, String email, String username, String password, Set<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

}


