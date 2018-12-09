package com.test.githubit.http.apimodel.User;

import com.test.githubit.http.apimodel.User.UserDetails;

import java.util.List;

public class UserDetailsMemory {

  private String name;

  private List<UserDetails> userDetails;

  public UserDetailsMemory() {
    this.name = "";
  }

  public List<UserDetails> getUserDetails() {
    return userDetails;
  }

  public void setUserDetails(List<UserDetails> forks) {
    this.userDetails = forks;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
