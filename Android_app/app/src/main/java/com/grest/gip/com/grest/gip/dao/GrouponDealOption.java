package com.grest.gip.com.grest.gip.dao;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Maksim.Superfin on 07.06.2016.
 */
public class GrouponDealOption implements Parcelable {
    String buyUrl;
    String details;
    String price;
    String title;

    public GrouponDealOption() {

    }

    protected GrouponDealOption(Parcel in) {
        buyUrl = in.readString();
        details = in.readString();
        price = in.readString();
        title = in.readString();
    }

    public static final Creator<GrouponDealOption> CREATOR = new Creator<GrouponDealOption>() {
        @Override
        public GrouponDealOption createFromParcel(Parcel in) {
            return new GrouponDealOption(in);
        }

        @Override
        public GrouponDealOption[] newArray(int size) {
            return new GrouponDealOption[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(buyUrl);
        dest.writeString(details);
        dest.writeString(price);
        dest.writeString(title);
    }

    public String getBuyUrl() {
        return buyUrl;
    }

    public void setBuyUrl(String buyUrl) {
        this.buyUrl = buyUrl;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
