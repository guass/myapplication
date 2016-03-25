package com.guass.navapp.bean;

/**
 * Created by guass on 2016/3/20.
 */
public class AppInfo {
    private long id;
    private String name;
    private String packageName;
    private String iconUrl;
    private double stars;
    private long size;
    private String downloadUrl;
    private String des;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public AppInfo(String des, String downloadUrl, String iconUrl, long id, String name, String packageName, long size, double stars) {
        this.des = des;
        this.downloadUrl = downloadUrl;
        this.iconUrl = iconUrl;
        this.id = id;
        this.name = name;
        this.packageName = packageName;
        this.size = size;
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "des='" + des + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", packageName='" + packageName + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", stars=" + stars +
                ", size=" + size +
                ", downloadUrl='" + downloadUrl + '\'' +
                '}';
    }
}
