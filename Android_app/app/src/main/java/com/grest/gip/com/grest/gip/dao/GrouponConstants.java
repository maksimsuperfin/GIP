package com.grest.gip.com.grest.gip.dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Maksim.Superfin on 5/13/2016.
 */
public class GrouponConstants {
    public static String AFFILIATE_ID = "209000";

    public static String[] countriesAbbr = new String[]
            {"US", "AE", "AR", "AT", "BE", "BR", "CH", "CO", "DE", "DK", "ES", "FI", "FR", "GR",
                    "IE", "IL", "IN", "IT", "MX", "NL", "NO", "NZ", "PE", "PH", "PL", "PT",
                    "RO", "SE", "SG", "TH", "TR", "UK", "ZA", "AU", "HK", "MY"
            };

    public static int[] countriesCodes = new int[]
            {
                    500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510, 511, 512, 513, 514,
                    515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529,
                    530, 531, 532, 533, 534, 535
            };

    public static Map<String, Integer> countriesAbbr2Codes = new HashMap<String, Integer>();
    static {
        for (int i = 0; i < countriesAbbr.length; i++) {
            countriesAbbr2Codes.put(countriesAbbr[i], countriesCodes[i]);
        }
    }

    public static String[] countries = new String[]
            {"United States", "United Arab Emirates", "Argentina", "Austria", "Belgium",
                    "Brazil", "Switzerland", "Colombia", "Germany", "Denmark", "Spain", "Finland",
                    "France", "Greece", "Ireland", "Israel", "India", "Italy", "Mexico",
                    "Netherlands", "Norway", "New Zealand", "Peru", "Philippines", "Poland",
                    "Portugal", "Romania", "Sweden", "Singapore", "Thailand", "Turkey",
                    "United Kingdom", "South Africa", "Australia", "Hong Kong", "Malaysia"
            };

    public static Map<String, String> countries2Codes = new HashMap<String, String>();
    static {
        for (int i = 0; i < countriesAbbr.length; i++) {
            countries2Codes.put(countries[i], countriesAbbr[i]);
        }
    }

    public static String[] sortedCountries = new String[]
            {"Argentina", "Australia", "Austria", "Belgium", "Brazil", "Colombia", "Denmark",
                    "Finland", "France", "Germany", "Greece", "Hong Kong", "India", "Ireland",
                    "Israel", "Italy", "Malaysia", "Mexico", "Netherlands", "New Zealand",
                    "Norway", "Peru", "Philippines", "Poland", "Portugal", "Romania",
                    "Singapore", "South Africa", "Spain", "Sweden", "Switzerland",
                    "Thailand", "Turkey", "United Kingdom", "United Arab Emirates",
                    "United States"
            };

    public static String[] categories = new String[]
            {"Food & Drink", "Beauty & spas", "Women", "Men", "Auto and home improvement",
                    "Home: Food & drink goods", "Home & garden goods", "Household essentials",
                    "Kids", "Travel accommodation", "Bed & breakfast travel", "Cabin travel",
                    "Cruise travel", "Hotels", "Resort travel", "Tour travel",
                    "Vacation rental travel"
            };

    public static String[] categoriesAPI = new String[]
            {"food-and-drink", "beauty-and-spas", "women", "men",
                    "home-improvement|auto-and-home-improvement", "food-and-drink-goods",
                    "home-and-garden-goods", "household-essentials", "baby-kids-and-toys",
                    "accommodation", "bed-and-breakfast-travel", "cabin-travel", "cruise-travel",
                    "hotels", "resort-travel", "tour-travel", "vacation-rental-travel"
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
