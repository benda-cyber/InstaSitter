package com.example.instasitter.classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class User implements Serializable {

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

    protected User(Parcel in) {
        name = in.readString();
        familyName = in.readString();
        dateOfBirth = in.readString();
        phone = in.readString();
        address = in.readString();
        email = in.readString();
        password = in.readString();
    }

//    public static final Creator<User> CREATOR = new Creator<User>() {
//        @Override
//        public User createFromParcel(Parcel in) {
//            return new User(in);
//        }
//
//        @Override
//        public User[] newArray(int size) {
//            return new User[size];
//        }
//    };


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



//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(name);
//        dest.writeString(familyName);
//        dest.writeString(dateOfBirth);
//        dest.writeString(phone);
//        dest.writeString(address);
//        dest.writeString(email);
//        dest.writeString(password);
//    }
}
