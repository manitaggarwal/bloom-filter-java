package com.manitaggarwal.bloom.entiry;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_details")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "username", unique = true)
    private String username;
    private String msisdn;

    public User(String username, String msisdn) {
        this.username = username;
        this.msisdn = msisdn;
    }
}
