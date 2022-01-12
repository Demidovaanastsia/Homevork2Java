package com.example.demo.entity;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "DISTRICT",
        uniqueConstraints = {
            @UniqueConstraint(name="district_title_unique", columnNames = "title")
        }
)
public class District {

    @Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    @SequenceGenerator(
            name = "district_sequence",
            sequenceName = "district_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = SEQUENCE,
                    generator = "district_sequence")
    @Column(name = "id",
            updatable = false)
    private Long id;

    @Column(name = "title",
            nullable = false,
            columnDefinition = "TEXT")
    private String title;

    @OneToMany(mappedBy="district", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Address> addresses;

    public District(){}

    public District(String title) {
        super();
        this.title = title;
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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return title;
    }
}
