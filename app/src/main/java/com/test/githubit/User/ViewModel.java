package com.test.githubit.User;


public class ViewModel {

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


}
