package com.grest.gip.com.grest.gip.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Maksim.Superfin on 5/13/2016.
 */
public class Categories {
    public static String[] categories = new String[]
            {"Food & Drink", "Beauty & spas", "Women", "Men", "Home", "Kids", "Travel"};

    public static Map<String, String> categories2Abbrevations = new HashMap<String, String>();
    static {
        categories2Abbrevations.put(categories[0], "food-and-drink");
        categories2Abbrevations.put(categories[1], "beauty-and-spas");
        categories2Abbrevations.put(categories[2], "women");
        categories2Abbrevations.put(categories[3], "men");
        categories2Abbrevations.put(categories[4], "home-improvement"/*"home-improvement|auto-and-home-improvement|food-and-drink-goods|home-and-garden-goods|household-essentials"*/);
        categories2Abbrevations.put(categories[5], "baby-kids-and-toys");
        categories2Abbrevations.put(categories[6], "accommodation"/*|bed-and-breakfast-travel|cabin-travel|cruise-travel|hotels|resort-travel|tour-travel|vacation-rental-travel"*/);
    }

    public static Map<String, String> abbrevations2Categories = new HashMap<String, String>();
    static {
        abbrevations2Categories.put("food-and-drink", categories[0]);
        abbrevations2Categories.put("beauty-and-spas", categories[1]);
        abbrevations2Categories.put("women", categories[2]);
        abbrevations2Categories.put("men", categories[3]);
        abbrevations2Categories.put("home-improvement", /*"home-improvement|auto-and-home-improvement|food-and-drink-goods|home-and-garden-goods|household-essentials",*/ categories[4]);
        abbrevations2Categories.put("baby-kids-and-toys", categories[5]);
        abbrevations2Categories.put("accommodation", categories[6]);
    }
}
