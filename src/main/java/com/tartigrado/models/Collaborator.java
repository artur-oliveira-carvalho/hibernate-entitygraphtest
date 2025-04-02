package com.tartigrado.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Table(name = "supplier")
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Collaborator extends LongModel {

    private Person person;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "fk_collaborator_person_id"))
    public Person getPerson() {
        return person;
    }
}
