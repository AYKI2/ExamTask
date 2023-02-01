package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Banks")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String address;
    @ManyToOne(cascade = {DETACH,MERGE,REFRESH,PERSIST})
    private Region region;
    @ManyToMany(cascade = {DETACH,MERGE,REFRESH,PERSIST}, mappedBy = "bankList")
    @Column(name = "clients")
    private List<Client> clientList;

    public Bank(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Bank(String name, String address, Region region) {
        this.name = name;
        this.address = address;
        this.region = region;
    }

    @Override
    public String toString() {
        return "\nBank:" +
                "\nid=" + id +
                "\nname='" + name +
                "\naddress = '" + address+
                region+"\n";
    }
}
