package com.example.instasitter.classes;

public class ServiceProviderModel {

    String name;
    String location;
    String serviceType;
    int id_;
    int image;

    public ServiceProviderModel(String name, String location, String serviceType, int id_, int image) {
        this.name = name;
        this.location = location;
        this.serviceType = serviceType;
        this.id_ = id_;
        this.image=image;
    }



    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getServiceType() {
        return serviceType;
    }

    public int getImage() {
        return image;
    }

    public int getId() {
        return id_;
    }
}

