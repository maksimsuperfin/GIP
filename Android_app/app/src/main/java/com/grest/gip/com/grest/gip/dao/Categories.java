package com.grest.gip.com.grest.gip.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Maksim.Superfin on 5/13/2016.
 */
public class Categories {
    public static Map<String, String> categories2Abbrevations = new HashMap<String, String>();
    static {
        categories2Abbrevations.put("Food & Drink", "food-and-drink");
        categories2Abbrevations.put("Beauty & spas", "beauty-and-spas");
        categories2Abbrevations.put("Women", "women");
        categories2Abbrevations.put("Men", "men");
        categories2Abbrevations.put("Home", "home-improvement|auto-and-home-improvement|food-and-drink-goods|home-and-garden-goods|household-essentials");
        categories2Abbrevations.put("Kids", "baby-kids-and-toys");
    }

    public static Map<String, String> abbrevations2Categories = new HashMap<String, String>();
    static {
        abbrevations2Categories.put("food-and-drink", "Food & Drink");
        abbrevations2Categories.put("beauty-and-spas", "Beauty & spas");
        abbrevations2Categories.put("women", "Women");
        abbrevations2Categories.put("men", "Men");
        abbrevations2Categories.put("home-improvement|auto-and-home-improvement|food-and-drink-goods|home-and-garden-goods|household-essentials", "Home");
        abbrevations2Categories.put("baby-kids-and-toys", "Kids");
    }

    public static String[] categories = getCategoriesNames();

    private static String[] getCategoriesNames() {

        Set<String> keys = categories2Abbrevations.keySet();
        String[] result = new String[keys.size()];
        int i = 0;
        for (String key: keys) {
            result[i++] = key;
        }
        return result;
    }
}
