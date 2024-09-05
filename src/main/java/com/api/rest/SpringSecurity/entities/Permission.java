package com.api.rest.SpringSecurity.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "permissions")
@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            unique = true,
            nullable = false,
            updatable = false
    )
    private String name;
}
