package com.example.instasitter.classes;

import android.os.Parcel;
import android.os.Parcelable;

public class ServiceProviderModel implements Parcelable {

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


    protected ServiceProviderModel(Parcel in) {
        name = in.readString();
        location = in.readString();
        serviceType = in.readString();
        id_ = in.readInt();
        image = in.readInt();
    }

    public static final Creator<ServiceProviderModel> CREATOR = new Creator<ServiceProviderModel>() {
        @Override
        public ServiceProviderModel createFromParcel(Parcel in) {
            return new ServiceProviderModel(in);
        }

        @Override
        public ServiceProviderModel[] newArray(int size) {
            return new ServiceProviderModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(location);
        dest.writeString(serviceType);
        dest.writeInt(id_);
        dest.writeInt(image);
    }
}

