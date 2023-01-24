package com.example.storagemanagerapp.models;

import jakarta.persistence.*;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
public class Section implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @NotEmpty
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @NotEmpty
    private String tags;

    @OneToMany
    private List<Product> products;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
