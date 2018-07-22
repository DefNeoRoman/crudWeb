package model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
@Entity
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private long id;

    @Column(name="age", length=200)
    private int age;

    @Column(name="name", length=2000)
    private String name;

    @Column(name="email", length=2000)
    private String email;

    @Column(name="createdDate", length=2000)
    private Timestamp createdDate;

    public User() {
        id = 0;
        name = " ";
        email = " ";
        age = 0;
        createdDate = new Timestamp(System.currentTimeMillis());
    }

    public User(int age, String name, String email) {
        this.age = age;
        this.name = name;
        this.email = email;
        this.createdDate = new Timestamp(System.currentTimeMillis());
    }

    public User(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.createdDate = new Timestamp(System.currentTimeMillis());
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isNew() {

        boolean b =  id == 0;

        return b;
    }
}
