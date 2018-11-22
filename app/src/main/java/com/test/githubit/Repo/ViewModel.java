package com.test.githubit.Repo;


public class ViewModel {

    private String username;
    private String name;
    private String description;
    private int forkCount;
    private String licenseName;

    public ViewModel(String username, String name, String description, int forkCount ,String licenseName
    ) {
        this.username=username;
        this.name = name;
        this.description = description;
        this.forkCount = forkCount;
        this.licenseName = licenseName;

    }

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

}
