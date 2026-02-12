package com.yousra.miawpaw.security.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "deleted_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeletedUser {
    @Id
    private Long id;

    private String firstName;

    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    private String CIN;

    private String phone;

    private LocalDateTime deletedAt;

}

