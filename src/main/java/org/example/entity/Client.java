package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.PERSIST;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "phone_number")
    private String PhoneNumber;
    @OneToOne(cascade = ALL)
    private Passport passport;
    @ManyToMany(cascade = {DETACH,MERGE,REFRESH,PERSIST})
    @Column(name = "banks")
    private List<Bank> bankList;

    public Client(String fullName, String phoneNumber) {
        this.fullName = fullName;
        PhoneNumber = phoneNumber;
    }

    public Client(String fullName, String phoneNumber, Passport passport, List<Bank> bankList) {
        this.fullName = fullName;
        PhoneNumber = phoneNumber;
        this.passport = passport;
        this.bankList = bankList;
    }

    @Override
    public String toString() {
        return "\nClient{" +
                "\nid=" + id +
                "\nfullName='" + fullName +
                "\nPhoneNumber='" + PhoneNumber +
                "\n" + passport+"\n";
    }
}
