package com.tartigrado.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Table(name = "access")
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Access extends LongModel {

    private Person person;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "fk_access_person_id"))
    public Person getPerson() {
        return person;
    }
}
