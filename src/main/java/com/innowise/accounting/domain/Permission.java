package com.innowise.accounting.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "permission")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "permissions", fetch = FetchType.EAGER)
    private Set<Role> roleSet;
}
