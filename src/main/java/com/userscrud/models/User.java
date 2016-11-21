package com.userscrud.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Size(min = 5, max = 25)
    @Column(name = "name")
    private String name;

    @NotNull
    @Min(0)
    @Max(150)
    @Column(name = "age")
    private int age;

    @NotNull
    @Column(name = "admin")
    private boolean admin;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdDate")
    private Date createdDate;

    public User() {
    }

    public void copyFrom(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.age = user.getAge();
        this.admin = user.getAdmin();
        this.createdDate = user.createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean isAdmin) {
        this.admin = isAdmin;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
