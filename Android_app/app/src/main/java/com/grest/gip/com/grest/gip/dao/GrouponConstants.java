package com.grest.gip.com.grest.gip.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Maksim.Superfin on 5/13/2016.
 */
public class GrouponConstants {
    /*

    * */
    public static String[] countries = new String[]
            {"US","AE", "AR", "AT", "BE", "BR", "CH", "CO", "DE", "DK", "ES", "FI", "FR", "GR",
                    "IE", "IL", "IN", "IT", "MX", "NL", "NO", "NZ", "PE", "PH", "PL", "PT",
                    "RO", "SE", "SG", "TH", "TR", "UK", "ZA", "AU", "HK", "MY"
            };

    public static int[] countriesCodes = new int[]
            {
                    500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510, 511, 512, 513, 514,
                    515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529,
                    530, 531, 532, 533, 534, 535
            };

    public static Map<String, Integer> countries2Codes = new HashMap<String, Integer>();
    static {
        for (int i = 0; i < countries.length; i++) {
            countries2Codes.put(countries[i], countriesCodes[i]);
        }
    }


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
        for (int i = 0; i < categories.length; i++) {
            categories2Abbrevations.put(categories[i], categoriesAPI[i]);
        }
    }

    public static Map<String, String> abbrevations2Categories = new HashMap<String, String>();
    static {
        for (int i = 0; i < categories.length; i++) {
            abbrevations2Categories.put(categoriesAPI[i], categories[i]);
        }
    }
}
