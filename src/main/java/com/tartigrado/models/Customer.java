package com.tartigrado.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.*;

@Entity
@SuperBuilder
@Table(name = "customer")
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends LongModel {

    private Person person;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "fk_customer_person_id"))
    public Person getPerson() {
        return person;
    }
}
