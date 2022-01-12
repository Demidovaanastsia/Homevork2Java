package com.example.demo.entity;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "EDUCATIONAL_INSTITUTION",
        uniqueConstraints = {
                @UniqueConstraint(name="ed_inst_number_unique", columnNames = "number")
        })
public class EducationalInstitution {

    @Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    @SequenceGenerator(
            name = "ed_inst_sequence",
            sequenceName = "ed_inst_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = SEQUENCE,
            generator = "ed_inst_sequence")
    @Column(name = "id",
            updatable = false)
    private Long id;

    @Column(name = "number",
            nullable = false,
            columnDefinition = "TEXT")
    private String number;

    @ManyToOne(targetEntity = Address.class, fetch = FetchType.LAZY)
    @JoinColumn(name="address_id")
    private Address addressEd;

    @OneToMany(mappedBy="educationalInstitution", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Child> children;

    public EducationalInstitution(){}

    public EducationalInstitution(String number){
        super();
        this.number = number;
    }

    public EducationalInstitution(String number, Address address) {
        super();
        this.number = number;
        this.addressEd = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Address getAddressEd() {
        return addressEd;
    }

    public void setAddressEd(Address addressEd) {
        this.addressEd = addressEd;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return number;
    }
}
