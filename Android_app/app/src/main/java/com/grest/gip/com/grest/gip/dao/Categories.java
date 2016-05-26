package com.grest.gip.com.grest.gip.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Maksim.Superfin on 5/13/2016.
 */
public class Categories {
    public static String[] categories = new String[]
            {"Food & Drink", "Beauty & spas", "Women", "Men", "Home improvement",
                    "Auto and home improvement", "Home: Food & drink goods",
                    "Home & garden goods", "Household essentials" ,"Kids",
                    "Travel accommodation", "Bed & breakfast travel", "Cabin travel",
                    "Cruise travel", "Hotels", "Resort travel", "Tour travel",
                    "Vacation rental travel"
            };
    public static String[] categoriesAPI = new String[]
            {"food-and-drink", "beauty-and-spas", "women", "men", "home-improvement",
                    "auto-and-home-improvement", "food-and-drink-goods", "home-and-garden-goods",
                    "household-essentials", "baby-kids-and-toys", "accommodation",
                    "bed-and-breakfast-travel", "cabin-travel", "cruise-travel", "hotels",
                    "resort-travel", "tour-travel", "vacation-rental-travel"
            };

    public static Map<String, String> categories2Abbrevations = new HashMap<String, String>();
    static {
        categories2Abbrevations.put(categories[0], categoriesAPI[0]);
        categories2Abbrevations.put(categories[1], categoriesAPI[1]);
        categories2Abbrevations.put(categories[2], categoriesAPI[2]);
        categories2Abbrevations.put(categories[3], categoriesAPI[3]);
        categories2Abbrevations.put(categories[4], categoriesAPI[4]);
        categories2Abbrevations.put(categories[5], categoriesAPI[5]);
        categories2Abbrevations.put(categories[7], categoriesAPI[7]);
        categories2Abbrevations.put(categories[8], categoriesAPI[8]);
        categories2Abbrevations.put(categories[9], categoriesAPI[9]);
        categories2Abbrevations.put(categories[10], categoriesAPI[10]);
        categories2Abbrevations.put(categories[11], categoriesAPI[11]);
        categories2Abbrevations.put(categories[12], categoriesAPI[12]);
        categories2Abbrevations.put(categories[13], categoriesAPI[13]);
        categories2Abbrevations.put(categories[14], categoriesAPI[14]);
        categories2Abbrevations.put(categories[15], categoriesAPI[15]);
        categories2Abbrevations.put(categories[17], categoriesAPI[17]);
    }

    public static Map<String, String> abbrevations2Categories = new HashMap<String, String>();
    static {
        abbrevations2Categories.put(categoriesAPI[0], categories[0]);
        abbrevations2Categories.put(categoriesAPI[1], categories[1]);
        abbrevations2Categories.put(categoriesAPI[2], categories[2]);
        abbrevations2Categories.put(categoriesAPI[3], categories[3]);
        abbrevations2Categories.put(categoriesAPI[4], categories[4]);
        abbrevations2Categories.put(categoriesAPI[5], categories[5]);
        abbrevations2Categories.put(categoriesAPI[6], categories[6]);
        abbrevations2Categories.put(categoriesAPI[7], categories[7]);
        abbrevations2Categories.put(categoriesAPI[8], categories[8]);
        abbrevations2Categories.put(categoriesAPI[9], categories[9]);
        abbrevations2Categories.put(categoriesAPI[10], categories[10]);
        abbrevations2Categories.put(categoriesAPI[11], categories[11]);
        abbrevations2Categories.put(categoriesAPI[12], categories[12]);
        abbrevations2Categories.put(categoriesAPI[13], categories[13]);
        abbrevations2Categories.put(categoriesAPI[14], categories[14]);
        abbrevations2Categories.put(categoriesAPI[15], categories[15]);
        abbrevations2Categories.put(categoriesAPI[16], categories[16]);
        abbrevations2Categories.put(categoriesAPI[17], categories[17]);
    }
}
