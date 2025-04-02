package com.tartigrado.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.*;

@MappedSuperclass
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class LongModel {
    private Long id;
    private Long owner;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Column(name = "owner")
    public Long getOwner() {
        return owner;
    }
}
