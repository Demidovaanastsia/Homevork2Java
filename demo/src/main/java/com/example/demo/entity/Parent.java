package com.example.demo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "PARENT")
public class Parent {

    @Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    @SequenceGenerator(
            name = "parent_sequence",
            sequenceName = "parent_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = SEQUENCE,
            generator = "parent_sequence")
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

    @ManyToOne(targetEntity = Address.class, fetch = FetchType.LAZY)
    @JoinColumn(name="address_id")
    private Address addressParent;

    @ManyToMany(targetEntity = Child.class,
            //fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinTable(name = "parent_child",
            joinColumns = @JoinColumn(name = "parent_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name = "child_id", referencedColumnName="id")
    )
    private List<Child> children;

    public Parent(){}

    public Parent(String firstName, String lastName, String patronymicName, Address address) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymicName = patronymicName;
        this.addressParent = address;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

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

    public Address getAddressParent() {
        return addressParent;
    }

    public void setAddressParent(Address addressParent) {
        this.addressParent = addressParent;
    }

    public List<Child> getChildren() {
        if (this.children == null){
            List<Child> emptyChildrenList = new ArrayList<Child>();
            return emptyChildrenList;
        }
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public void addChild(Child child) {
        if (this.children == null){
            this.children = new ArrayList<>();
        }
        children.add(child);
    }

    @Override
    public String toString() {
        return "Parent [id=" + id
                + ", fullName=" + firstName + " " + lastName + " " + patronymicName
                + ", children=" + children
                + "]";
    }
}
