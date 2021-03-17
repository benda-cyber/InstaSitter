package com.example.instasitter.classes;

public class User {

    private String  name;

    private String  familyName;

    private String  dateOfBirth;

    private String  phone;

    private String  address;

    private String  email;

    private String  password;


    public User(String name, String familyName, String dateOfBirth, String phone, String address, String email, String password){

        this.name=name;
        this.familyName=familyName;
        this.dateOfBirth=dateOfBirth;
        this.phone=phone;
        this.address=address;
        this.email=email;
        this.password=password;

    }
    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
