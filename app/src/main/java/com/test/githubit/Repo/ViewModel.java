package com.test.githubit.Repo;

import android.os.Parcel;
import android.os.Parcelable;

public class ViewModel implements Parcelable {

    private String username;
    private String name;
    private String description;
    private int forkCount;
    private String licenseName;

    public ViewModel(String username, String name, String description, int forkCount ,String licenseName) {
        this.username=username;
        this.name = name;
        this.description = description;
        this.forkCount = forkCount;
        this.licenseName = licenseName;
    }

    public ViewModel(String username, String name, String description, int forkCount) {
        this.username=username;
        this.name = name;
        this.description = description;
        this.forkCount = forkCount;
        this.licenseName = "not found";

    }

    protected ViewModel(Parcel in) {
        username = in.readString();
        name = in.readString();
        description = in.readString();
        forkCount = in.readInt();
        licenseName = in.readString();
    }

    public static final Creator<ViewModel> CREATOR = new Creator<ViewModel>() {
        @Override
        public ViewModel createFromParcel(Parcel in) {
            return new ViewModel(in);
        }

        @Override
        public ViewModel[] newArray(int size) {
            return new ViewModel[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public int getForkCount() {
        return forkCount;
    }

    public void setForkCount(int forkCount) {
        this.forkCount = forkCount;
    }

    public String getLicenseName() {
        return licenseName;
    }

    public void setLicenseName(String licenseName) {
        this.licenseName = licenseName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(username);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeInt(forkCount);
        parcel.writeString(licenseName);
    }
}
