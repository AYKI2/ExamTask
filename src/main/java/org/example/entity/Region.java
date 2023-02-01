package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Regions")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "region_Name")
    private String regionName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "region")
    @Column(name = "banks")
    private List<Bank> bankList;

    public Region(String regionName) {
        this.regionName = regionName;
    }

    public Region(String regionName, List<Bank> bankList) {
        this.regionName = regionName;
        this.bankList = bankList;
    }

    @Override
    public String toString() {
        return "\nRegion:" +
                "\nid = " + id +
                "\nregionName = '" + regionName;
    }
}
