package entity;

import java.sql.Timestamp;

public class User {

    private long id;

    private String name;

    private String email;

    private int age;
    // LocalDateTime
    private Timestamp createdDate;

    public User() {
        id = 0;
        name = " ";
        email = " ";
        age = 0;
        createdDate = new Timestamp(System.currentTimeMillis());
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
        return id == 0;
    }
}
