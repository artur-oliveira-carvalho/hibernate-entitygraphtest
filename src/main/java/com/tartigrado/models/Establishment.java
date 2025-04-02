package com.tartigrado.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Optional;

@Entity
@SuperBuilder
@Table(name = "establishment")
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "graph.FetchFullEstablishments",
                attributeNodes = {
                        @NamedAttributeNode(value = "person")
                }
        )
})
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Establishment extends LongModel {

    private Person person;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "person_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "fk_establishment_person_id"))
    public Person getPerson() {
        return person;
    }

    @Transient
    public Long getCustomerId() {
        return Optional.ofNullable(getPerson()).map(Person::getCustomer).map(LongModel::getId).orElse(null);
    }

    @Transient
    public Long getCollaboratorId() {
        return Optional.ofNullable(getPerson()).map(Person::getCustomer).map(LongModel::getId).orElse(null);
    }

    @Transient
    public Long getSupplierId() {
        return Optional.ofNullable(getPerson()).map(Person::getCustomer).map(LongModel::getId).orElse(null);
    }

    @Transient
    public Long getAccessId() {
        return Optional.ofNullable(getPerson()).map(Person::getCustomer).map(LongModel::getId).orElse(null);
    }

    @Transient
    public Long getEstablishmentId() {
        return Optional.ofNullable(getPerson()).map(Person::getEstablishment).map(LongModel::getId).orElse(null);
    }
}
