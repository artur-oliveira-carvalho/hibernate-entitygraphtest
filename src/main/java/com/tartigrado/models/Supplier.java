package com.tartigrado.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.*;

@Entity
@SuperBuilder
@Table(name = "supplier")
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supplier extends LongModel {

    private Person person;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "fk_supplier_person_id"))
    public Person getPerson() {
        return person;
    }
}
