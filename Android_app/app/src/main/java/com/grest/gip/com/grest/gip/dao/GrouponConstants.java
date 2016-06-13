package com.grest.gip.com.grest.gip.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Maksim.Superfin on 5/13/2016.
 */
public class GrouponConstants {
    public static String AFFILIATE_ID = "209000";

    public static String COUNTRY_UNITED_STATES = "United States";
    public static String COUNTRY_UNITED_ARAB_EMIRATES = "United Arab Emirates";
    public static String COUNTRY_ARGENTINA = "Argentina";
    public static String COUNTRY_AUSTRIA = "Austria";
    public static String COUNTRY_BELGIUM = "Belgium";
    public static String COUNTRY_BRAZIL = "Brazil";
    public static String COUNTRY_SWITZERLAND = "Switzerland";
    public static String COUNTRY_COLUMBIA = "Colombia";
    public static String COUNTRY_GERMANY = "Germany";
    public static String COUNTRY_DENMARK = "Denmark";
    public static String COUNTRY_SPAIN = "Spain";
    public static String COUNTRY_FINLAND = "Finland";
    public static String COUNTRY_FRANCE = "France";
    public static String COUNTRY_GREECE = "Greece";
    public static String COUNTRY_IRELAND = "Ireland";
    public static String COUNTRY_ISRAEL = "Israel";
    public static String COUNTRY_INDIA = "India";
    public static String COUNTRY_ITALY = "Italy";
    public static String COUNTRY_MEXICO = "Mexico";
    public static String COUNTRY_NETHERLANDS = "Netherlands";
    public static String COUNTRY_NORWAY = "Norway";
    public static String COUNTRY_NEW_ZEALAND = "New Zealand";
    public static String COUNTRY_PERU = "Peru";
    public static String COUNTRY_PHILIPPINES = "Philippines";
    public static String COUNTRY_POLAND = "Poland";
    public static String COUNTRY_PORTUGAL = "Portugal";
    public static String COUNTRY_ROMANIA = "Romania";
    public static String COUNTRY_SWEDEN = "Sweden";
    public static String COUNTRY_SINGAPORE = "Singapore";
    public static String COUNTRY_THAILAND = "Thailand";
    public static String COUNTRY_TURKEY = "Turkey";
    public static String COUNTRY_UNITED_KINGDOM = "United Kingdom";
    public static String COUNTRY_SOUTH_AFRICA = "South Africa";
    public static String COUNTRY_AUSTRALIA = "Australia";
    public static String COUNTRY_HONG_KONG = "Hong Kong";
    public static String COUNTRY_MALAYSIA = "Malaysia";

    public static String CATEGORY_FOOD_AND_DRINK = "Food & Drink";
    public static String CATEGORY_BEAUTY_AND_SPAS = "Beauty & spas";
    public static String CATEGORY_WOMEN = "Women";
    public static String CATEGORY_MEN = "Men";
    public static String CATEGORY_AUTO_AND_HOME_IMPROVEMENT = "Auto and home improvement";
    public static String CATEGORY_HOME_FOOD_AND_DRINK_GOODS = "Home: Food & drink goods";
    public static String CATEGORY_HOME_AND_GARDEN_GOODS = "Home & garden goods";
    public static String CATEGORY_HOUSEHOLD_ESSENTIALS = "Household essentials";
    public static String CATEGORY_KIDS = "Kids";
    public static String CATEGORY_TRAVEL_ACCOMODATION = "Travel accommodation";
    public static String CATEGORY_BED_AND_BREAKFAST_TRAVEL = "Bed & breakfast travel";
    public static String CATEGORY_CABIN_TRAVEL = "Cabin travel";
    public static String CATEGORY_CRUISE_TRAVEL = "Cruise travel";
    public static String CATEGORY_HOTELS = "Hotels";
    public static String CATEGORY_RESORT_TRAVEL = "Resort travel";
    public static String CATEGORY_TOUR_TRAVEL = "Tour travel";
    public static String CATEGORY_VACATION_RENTAL_TRAVEL = "Vacation rental travel";

    public static String MENU_SHOPPING = "Shop";
    public static String MENU_TRAVEL = "Travel";

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
            {COUNTRY_UNITED_STATES, COUNTRY_UNITED_ARAB_EMIRATES, COUNTRY_ARGENTINA, COUNTRY_AUSTRIA,
                    COUNTRY_BELGIUM, COUNTRY_BRAZIL, COUNTRY_SWITZERLAND, COUNTRY_COLUMBIA,
                    COUNTRY_GERMANY, COUNTRY_DENMARK, COUNTRY_SPAIN, COUNTRY_FINLAND,
                    COUNTRY_FRANCE, COUNTRY_GREECE, COUNTRY_IRELAND, COUNTRY_ISRAEL, COUNTRY_INDIA,
                    COUNTRY_ITALY, COUNTRY_MEXICO, COUNTRY_NETHERLANDS, COUNTRY_NORWAY,
                    COUNTRY_NEW_ZEALAND, COUNTRY_PERU, COUNTRY_PHILIPPINES, COUNTRY_POLAND,
                    COUNTRY_PORTUGAL, COUNTRY_ROMANIA, COUNTRY_SWEDEN, COUNTRY_SINGAPORE,
                    COUNTRY_THAILAND, COUNTRY_TURKEY, COUNTRY_UNITED_KINGDOM, COUNTRY_SOUTH_AFRICA,
                    COUNTRY_AUSTRALIA, COUNTRY_HONG_KONG, COUNTRY_MALAYSIA
            };

    public static Map<String, String> countries2Codes = new HashMap<String, String>();
    static {
        for (int i = 0; i < countriesAbbr.length; i++) {
            countries2Codes.put(countries[i], countriesAbbr[i]);
        }
    }

    public static String[] sortedCountries = new String[]
            {COUNTRY_ARGENTINA, COUNTRY_AUSTRALIA, COUNTRY_AUSTRIA, COUNTRY_BELGIUM, COUNTRY_BRAZIL,
                    COUNTRY_COLUMBIA, COUNTRY_DENMARK, COUNTRY_FINLAND, COUNTRY_FRANCE,
                    COUNTRY_GERMANY, COUNTRY_GREECE, COUNTRY_HONG_KONG, COUNTRY_INDIA,
                    COUNTRY_IRELAND, COUNTRY_ISRAEL, COUNTRY_ITALY, COUNTRY_MALAYSIA,
                    COUNTRY_MEXICO, COUNTRY_NETHERLANDS, COUNTRY_NEW_ZEALAND,
                    COUNTRY_NORWAY, COUNTRY_PERU, COUNTRY_PHILIPPINES, COUNTRY_POLAND,
                    COUNTRY_PORTUGAL, COUNTRY_ROMANIA, COUNTRY_SINGAPORE, COUNTRY_SOUTH_AFRICA,
                    COUNTRY_SPAIN, COUNTRY_SWEDEN, COUNTRY_SWITZERLAND, COUNTRY_THAILAND,
                    COUNTRY_TURKEY, COUNTRY_UNITED_KINGDOM, COUNTRY_UNITED_ARAB_EMIRATES,
                    COUNTRY_UNITED_STATES
            };

    public static String[] categories = new String[]
            {CATEGORY_FOOD_AND_DRINK, CATEGORY_BEAUTY_AND_SPAS, CATEGORY_WOMEN, CATEGORY_MEN,
                    CATEGORY_AUTO_AND_HOME_IMPROVEMENT, CATEGORY_HOME_FOOD_AND_DRINK_GOODS,
                    CATEGORY_HOME_AND_GARDEN_GOODS, CATEGORY_HOUSEHOLD_ESSENTIALS,
                    CATEGORY_KIDS, CATEGORY_TRAVEL_ACCOMODATION, CATEGORY_BED_AND_BREAKFAST_TRAVEL,
                    CATEGORY_CABIN_TRAVEL, CATEGORY_CRUISE_TRAVEL, CATEGORY_HOTELS,
                    CATEGORY_RESORT_TRAVEL, CATEGORY_TOUR_TRAVEL, CATEGORY_VACATION_RENTAL_TRAVEL
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

    public static List<String> MENU_ITEMS_MAIN = new ArrayList<String>();
    static {
        MENU_ITEMS_MAIN.add(CATEGORY_FOOD_AND_DRINK);
        MENU_ITEMS_MAIN.add(MENU_SHOPPING);
        MENU_ITEMS_MAIN.add(CATEGORY_BEAUTY_AND_SPAS);
        MENU_ITEMS_MAIN.add(MENU_TRAVEL);
    }

    public static List<String> MENU_ITEMS_SHOP = new ArrayList<>();
    static {
        MENU_ITEMS_SHOP.add(CATEGORY_MEN);
        MENU_ITEMS_SHOP.add(CATEGORY_WOMEN);
        MENU_ITEMS_SHOP.add(CATEGORY_KIDS);
        MENU_ITEMS_SHOP.add(CATEGORY_AUTO_AND_HOME_IMPROVEMENT);
        MENU_ITEMS_SHOP.add(CATEGORY_HOME_FOOD_AND_DRINK_GOODS);
        MENU_ITEMS_SHOP.add(CATEGORY_HOME_AND_GARDEN_GOODS);
        MENU_ITEMS_SHOP.add(CATEGORY_HOUSEHOLD_ESSENTIALS);
    }

    public static List<String> MENU_ITEMS_TRAVEL = new ArrayList<>();
    static {
        MENU_ITEMS_TRAVEL.add(CATEGORY_TRAVEL_ACCOMODATION);
        MENU_ITEMS_TRAVEL.add(CATEGORY_BED_AND_BREAKFAST_TRAVEL);
        MENU_ITEMS_TRAVEL.add(CATEGORY_CABIN_TRAVEL);
        MENU_ITEMS_TRAVEL.add(CATEGORY_CRUISE_TRAVEL);
        MENU_ITEMS_TRAVEL.add(CATEGORY_HOTELS);
        MENU_ITEMS_TRAVEL.add(CATEGORY_RESORT_TRAVEL);
        MENU_ITEMS_TRAVEL.add(CATEGORY_TOUR_TRAVEL);
        MENU_ITEMS_TRAVEL.add(CATEGORY_VACATION_RENTAL_TRAVEL);
    }

    public static Map<String, List<String>> TOTAL_MENU = new HashMap<String, List<String>>();
    static {
        TOTAL_MENU.put(CATEGORY_FOOD_AND_DRINK, null);
        TOTAL_MENU.put(MENU_SHOPPING, MENU_ITEMS_SHOP);
        TOTAL_MENU.put(CATEGORY_BEAUTY_AND_SPAS, null);
        TOTAL_MENU.put(MENU_TRAVEL, MENU_ITEMS_TRAVEL);
    }
}
