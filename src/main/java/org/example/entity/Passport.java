package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.CascadeType.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "passports")
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String INN;
    @OneToOne(cascade = {DETACH,MERGE,PERSIST,REFRESH}, mappedBy = "passport")
    private Client client;

    public Passport(String INN) {
        this.INN = INN;
    }

    public Passport(String INN, Client client) {
        this.INN = INN;
        this.client = client;
    }

    @Override
    public String toString() {
        return "Passport:" +
                "\nid = " + id +
                "\nINN = " + INN;
    }
}
