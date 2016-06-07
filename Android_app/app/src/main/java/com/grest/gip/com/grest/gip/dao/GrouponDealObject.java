package com.grest.gip.com.grest.gip.dao;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maksim.Superfin on 06.06.2016.
 */
public class GrouponDealObject implements Parcelable {
    String id;
    String dealUrl;
    String title;
    String announcementTitle;
    //String shortAnnouncementTitle;
    String finePrint;
    String grid6ImageUrl;
    List<GrouponDealOption> options = new ArrayList<GrouponDealOption>();

    public GrouponDealObject() {

    }

    protected GrouponDealObject(Parcel in) {
        id = in.readString();
        dealUrl = in.readString();
        title = in.readString();
        announcementTitle = in.readString();
        //shortAnnouncementTitle = in.readString();
        finePrint = in.readString();
        grid6ImageUrl = in.readString();
        options = in.readArrayList(GrouponDealOption.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(dealUrl);
        dest.writeString(title);
        dest.writeString(announcementTitle);
        //dest.writeString(shortAnnouncementTitle);
        dest.writeString(finePrint);
        dest.writeString(grid6ImageUrl);
        dest.writeList(options);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GrouponDealObject> CREATOR = new Creator<GrouponDealObject>() {
        @Override
        public GrouponDealObject createFromParcel(Parcel in) {
            return new GrouponDealObject(in);
        }

        @Override
        public GrouponDealObject[] newArray(int size) {
            return new GrouponDealObject[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDealUrl() {
        return dealUrl;
    }

    public void setDealUrl(String dealUrl) {
        this.dealUrl = dealUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnnouncementTitle() {
        return announcementTitle;
    }

    public void setAnnouncementTitle(String announcementTitle) {
        this.announcementTitle = announcementTitle;
    }

    /*public String getShortAnnouncementTitle() {
        return shortAnnouncementTitle;
    }

    public void setShortAnnouncementTitle(String shortAnnouncementTitle) {
        this.shortAnnouncementTitle = shortAnnouncementTitle;
    }*/

    public String getFinePrint() {
        return finePrint;
    }

    public void setFinePrint(String finePrint) {
        this.finePrint = finePrint;
    }

    public String getGrid6ImageUrl() {
        return grid6ImageUrl;
    }

    public void setGrid6ImageUrl(String grid6ImageUrl) {
        this.grid6ImageUrl = grid6ImageUrl;
    }

    public List<GrouponDealOption> getOptions() {
        return options;
    }

    public void setOptions(List<GrouponDealOption> options) {
        this.options = options;
    }
}
