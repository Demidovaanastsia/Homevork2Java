package com.example.demo.entity;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "CHILD")
public class Child {

    @Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    @SequenceGenerator(
            name = "child_sequence",
            sequenceName = "child_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = SEQUENCE,
            generator = "child_sequence")
    @Column(name = "id",
            updatable = false)
    private Long id;

    @Column(name = "first_name",
            nullable = false,
            columnDefinition = "TEXT")
    private String firstName;

    @Column(name = "last_name",
            nullable = false,
            columnDefinition = "TEXT")
    private String lastName;

    @Column(name = "patronymic_name",
            columnDefinition = "TEXT")
    private String patronymicName;

    @Column(name = "age")
    private int age;

    @ManyToMany(targetEntity = Parent.class,
            mappedBy = "children",
            cascade = CascadeType.ALL)
    private List<Parent> parents;

    @ManyToOne(targetEntity = EducationalInstitution.class, fetch = FetchType.LAZY)
    @JoinColumn(name="ed_inst_id")
    private EducationalInstitution educationalInstitution;

    public Child(){}

    public Child(String firstName, String lastName, String patronymicName,
                 int age) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymicName = patronymicName;
        this.age = age;
    }

    public Child(String firstName, String lastName, String patronymicName,
                 int age, EducationalInstitution educationalInstitution) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymicName = patronymicName;
        this.age = age;
        this.educationalInstitution = educationalInstitution;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymicName() {
        return patronymicName;
    }

    public void setPatronymicName(String patronymicName) {
        this.patronymicName = patronymicName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Parent> getParents() {
        return parents;
    }

    public void setParents(List<Parent> parents) {
        this.parents = parents;
    }

    public EducationalInstitution getEducationalInstitution() {
        if(educationalInstitution != null){
            return educationalInstitution;
        } else {
            EducationalInstitution EmptyEdInst = new EducationalInstitution(" ");
            return EmptyEdInst;
        }
    }

    public void setEducationalInstitution(EducationalInstitution educationalInstitution) {
        this.educationalInstitution = educationalInstitution;
    }

    @Override
    public String toString() {
        return "" + id;
    }
}
