package com.test.githubit.http.apimodel.Repo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class License implements Parcelable {

  @SerializedName("key")
  @Expose
  private String key;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("spdx_id")
  @Expose
  private String spdxId;
  @SerializedName("url")
  @Expose
  private String url;
  @SerializedName("node_id")
  @Expose
  private String nodeId;

  protected License(Parcel in) {
    key = in.readString();
    name = in.readString();
    spdxId = in.readString();
    url = in.readString();
    nodeId = in.readString();
  }

  public static final Creator<License> CREATOR = new Creator<License>() {
    @Override
    public License createFromParcel(Parcel in) {
      return new License(in);
    }

    @Override
    public License[] newArray(int size) {
      return new License[size];
    }
  };

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSpdxId() {
    return spdxId;
  }

  public void setSpdxId(String spdxId) {
    this.spdxId = spdxId;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getNodeId() {
    return nodeId;
  }

  public void setNodeId(String nodeId) {
    this.nodeId = nodeId;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(key);
    parcel.writeString(name);
    parcel.writeString(spdxId);
    parcel.writeString(url);
    parcel.writeString(nodeId);
  }
}
