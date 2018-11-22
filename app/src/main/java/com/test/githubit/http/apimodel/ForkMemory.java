package com.test.githubit.http.apimodel;

import java.util.List;

public class ForkMemory {

  private String name;
  private List<Fork> forks;

  public ForkMemory() {
    this.name = "";
  }

  public ForkMemory(String name, List<Fork> forks) {
    this.name = name;
    this.forks = forks;
  }

  public List<Fork> getForks() {
    return forks;
  }

  public void setForks(List<Fork> forks) {
    this.forks = forks;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
