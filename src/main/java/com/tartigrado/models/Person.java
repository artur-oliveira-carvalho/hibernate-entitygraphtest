package com.tartigrado.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Table(name = "person", uniqueConstraints = {@UniqueConstraint(columnNames = {"owner", "federal_registration"}, name = "unique_owner_federal_registration")})
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person extends LongModel {
    private String name;
    private String federalRegistration;

    private Establishment establishment;
    private Customer customer;
    private Supplier supplier;
    private Access access;
    private Collaborator collaborator;

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "federal_registration")
    public String getFederalRegistration() {
        return federalRegistration;
    }

    @OneToOne(mappedBy = "person", fetch = FetchType.LAZY)
    public Establishment getEstablishment() {
        return establishment;
    }

    @OneToOne(mappedBy = "person", fetch = FetchType.LAZY)
    public Customer getCustomer() {
        return customer;
    }

    @OneToOne(mappedBy = "person", fetch = FetchType.LAZY)
    public Supplier getSupplier() {
        return supplier;
    }

    @OneToOne(mappedBy = "person", fetch = FetchType.LAZY)
    public Access getAccess() {
        return access;
    }

    @OneToOne(mappedBy = "person", fetch = FetchType.LAZY)
    public Collaborator getCollaborator() {
        return collaborator;
    }
}
