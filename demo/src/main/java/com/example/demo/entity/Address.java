package com.example.demo.entity;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "ADDRESS",
        uniqueConstraints = {
                @UniqueConstraint(name="address_title_unique", columnNames = "title")
        }
)
public class Address {

    @Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    @SequenceGenerator(
            name = "address_sequence",
            sequenceName = "address_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = SEQUENCE,
            generator = "address_sequence")
    @Column(name = "id",
            updatable = false)
    private Long id;

    @Column(name = "title",
            nullable = false,
            columnDefinition = "TEXT")
    private String title;

    @ManyToOne(targetEntity = District.class, fetch = FetchType.LAZY)
    @JoinColumn(name="district_id")
    private District district;

    @OneToMany(mappedBy="addressParent", orphanRemoval = true)
    private List<Parent> parents;

    @OneToMany(mappedBy="addressEd", orphanRemoval = true)
    private List<EducationalInstitution> educationalInstitutions;

    public Address(){}

    public Address(String title, District district) {
        super();
        this.title = title;
        this.district = district;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public List<Parent> getParents() {
        return parents;
    }

    public void setChildren(List<Parent> parents) {
        this.parents = parents;
    }

    public List<EducationalInstitution> getEducationalInstitutions() {
        return educationalInstitutions;
    }

    public void setEducationalInstitutions(List<EducationalInstitution> educationalInstitutions) {
        this.educationalInstitutions = educationalInstitutions;
    }

    @Override
    public String toString() {
        return title;
    }
}