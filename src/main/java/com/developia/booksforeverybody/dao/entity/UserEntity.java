package com.developia.booksforeverybody.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Length(min = 3, max = 20)
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Length(min = 5, max = 25)
    @Column(name = "last_name")
    private String lastName;


    @Column(name = "address")
    private String address;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private UserStatus status;

    @NotBlank
    @Length(min = 9, max = 11)
    @Column(name = "phone_number")
    private String phoneNumber;

    @Email
    @Column(name = "email")
    private String  email;

    @NotBlank
    @Length(min = 5, max = 20)
    @Column(name = "username")
    private String  username;

    @NotBlank
    @Length(min = 8, max = 20)
    @Column(name = "password")
    private String  password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEntity> roles = new ArrayList<>();

}
