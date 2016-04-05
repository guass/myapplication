package com.guass.navapp.bean;

import java.util.List;

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

    //~~~~~~~~~~~~~~~~~~~//

    private String downLoadNum;
    private String version;
    private String date;
    private String author;
    private List<String> screen;
    private List<String> safeUrl;
    private List<String> safeDesUrl;
    private List<String> safeDes;
    private List<Integer> safeDesColor;


    public String getDownLoadNum() {
        return downLoadNum;
    }

    public void setDownLoadNum(String downLoadNum) {
        this.downLoadNum = downLoadNum;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getScreen() {
        return screen;
    }

    public void setScreen(List<String> screen) {
        this.screen = screen;
    }

    public List<String> getSafeUrl() {
        return safeUrl;
    }

    public void setSafeUrl(List<String> safeUrl) {
        this.safeUrl = safeUrl;
    }

    public List<String> getSafeDesUrl() {
        return safeDesUrl;
    }

    public void setSafeDesUrl(List<String> safeDesUrl) {
        this.safeDesUrl = safeDesUrl;
    }

    public List<String> getSafeDes() {
        return safeDes;
    }

    public void setSafeDes(List<String> safeDes) {
        this.safeDes = safeDes;
    }

    public List<Integer> getSafeDesColor() {
        return safeDesColor;
    }

    public void setSafeDesColor(List<Integer> safeDesColor) {
        this.safeDesColor = safeDesColor;
    }

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

    public AppInfo(long id, String name, String packageName, String iconUrl, double stars,
                   long size, String downloadUrl, String des, String downLoadNum, String version,
                   String date, String author, List<String> screen, List<String> safeUrl,
                   List<String> safeDesUrl, List<String> safeDes, List<Integer> safeDesColor) {
        this.id = id;
        this.name = name;
        this.packageName = packageName;
        this.iconUrl = iconUrl;
        this.stars = stars;
        this.size = size;
        this.downloadUrl = downloadUrl;
        this.des = des;
        this.downLoadNum = downLoadNum;
        this.version = version;
        this.date = date;
        this.author = author;
        this.screen = screen;
        this.safeUrl = safeUrl;
        this.safeDesUrl = safeDesUrl;
        this.safeDes = safeDes;
        this.safeDesColor = safeDesColor;
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", packageName='" + packageName + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", stars=" + stars +
                ", size=" + size +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", des='" + des + '\'' +
                ", downLoadNum='" + downLoadNum + '\'' +
                ", version='" + version + '\'' +
                ", date='" + date + '\'' +
                ", author='" + author + '\'' +
                ", screen=" + screen +
                ", safeUrl=" + safeUrl +
                ", safeDesUrl=" + safeDesUrl +
                ", safeDes=" + safeDes +
                ", safeDesColor=" + safeDesColor +
                '}';
    }
}
