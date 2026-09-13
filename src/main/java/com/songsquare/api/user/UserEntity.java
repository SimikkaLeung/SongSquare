package com.songsquare.api.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="ss_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE, 
        generator = "ss_user_seq"
    )
    @SequenceGenerator(
        name = "ss_user_seq", 
        sequenceName = "ss_user_seq", // Exact name of the sequence in your database
        allocationSize = 1             // Match your DB sequence increment (default in JPA is 50)
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "display_name")
    private String displayName;
}
