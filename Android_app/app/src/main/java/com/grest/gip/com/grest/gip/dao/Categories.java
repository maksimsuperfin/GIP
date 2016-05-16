package com.grest.gip.com.grest.gip.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Maksim.Superfin on 5/13/2016.
 */
public class Categories {
    public static Map<String, String> categories2Abbrevations = new HashMap<String, String>();
    static {
        categories2Abbrevations.put("Food & Drink", "food-and-drink");
    }

    public static Map<String, String> abbrevations2Categories = new HashMap<String, String>();
    static {
        abbrevations2Categories.put("food-and-drink", "Food & Drink");
    }
}
