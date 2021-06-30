package com.example.instasitter.classes;

import android.os.Parcelable;

import java.io.Serializable;

public class ServiceProvider extends User implements Serializable {

    private String  idNum;

    private boolean dogwalker;

    private boolean babysitter;

    private String profilePic;

    public ServiceProvider(User user) {
        super(user.getName(), user.getFamilyName(), user.getDateOfBirth(),user.getPhone(), user.getAddress(), user.getEmail(), user.getPassword());
        this.idNum = null;
        this.babysitter = false;
        this.dogwalker = false;
        this.profilePic = null;
    }
    public ServiceProvider(User user,String idNum, boolean dogwalker, boolean babysitter){
        super(user.getName(), user.getFamilyName(), user.getDateOfBirth(),user.getPhone(), user.getAddress(), user.getEmail(), user.getPassword());
        this.idNum = idNum;
        this.babysitter = babysitter;
        this.dogwalker = dogwalker;

    }

    public ServiceProvider() {
        super();
        this.idNum = null;
        this.babysitter = false;
        this.dogwalker = false;
        this.profilePic = null;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public boolean isBabysitter() {
        return babysitter;
    }

    public void setBabysitter(boolean babysitter) {
        this.babysitter = babysitter;
    }

    public boolean isDogwalker() {
        return dogwalker;
    }

    public void setDogwalker(boolean dogwalker) {
        this.dogwalker = dogwalker;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
    public String getServices(){
        if(dogwalker && babysitter){
            return ("Dog Walker, Babysitter");

        }
        if(dogwalker){
            return ("Dog Walker");
        }
        if(babysitter){
            return ("Babysitter");
        }
        return "Not a Service Provider";
    }



}
