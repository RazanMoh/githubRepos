package com.test.githubit.Forks;

import android.os.Parcel;
import android.os.Parcelable;

public class ViewModel implements Parcelable {

    private String name;
    private String avatar;
    private int publicRepos;
    private int followers;

    public ViewModel(String name, int publicRepos, String avatar, int followers) {
        this.avatar = avatar;
        this.name = name;
        this.publicRepos=publicRepos;
        this.followers=followers;
    }

    protected ViewModel(Parcel in) {
        name = in.readString();
        avatar = in.readString();
        publicRepos = in.readInt();
        followers = in.readInt();
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPublicRepos() {
        return publicRepos;
    }

    public void setPublicRepos(int publicRepos) {
        this.publicRepos = publicRepos;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(avatar);
        parcel.writeInt(publicRepos);
        parcel.writeInt(followers);
    }
}
