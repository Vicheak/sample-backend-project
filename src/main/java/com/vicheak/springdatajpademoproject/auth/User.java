package com.vicheak.springdatajpademoproject.auth;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String uuid;
    @Column(unique = true, length = 80, nullable = false)
    private String username;
    @Column(unique = true, length = 80, nullable = false)
    private String email;
    @Column(name = "encrypted_password", nullable = false)
    private String password;
    @Column(length = 30)
    private String nickname;
    @Column(length = 10)
    private String verifiedCode;
    private Boolean isVerified;
    @ManyToMany
    @JoinTable(joinColumns =
    @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;
}
