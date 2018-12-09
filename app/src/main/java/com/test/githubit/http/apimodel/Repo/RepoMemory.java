package com.test.githubit.http.apimodel.Repo;

import com.test.githubit.http.apimodel.Repo.Repo;

import java.util.List;

public class RepoMemory {

  private String name;
  private List<Repo> repos;

  public RepoMemory() {
    this.name = "";
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Repo> getRepos() {
    return repos;
  }

  public void setRepos(List<Repo> repos) {
    this.repos = repos;
  }

}
