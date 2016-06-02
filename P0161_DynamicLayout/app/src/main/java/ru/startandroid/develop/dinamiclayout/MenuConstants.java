package ru.startandroid.develop.dinamiclayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Maksim.Superfin on 6/2/2016.
 */
public class MenuConstants {

    public static List<String> ITEMS_MAIN = new ArrayList<String>();
    static {
        ITEMS_MAIN.add("Food and Drink");
        ITEMS_MAIN.add("Shop");
        ITEMS_MAIN.add("Spa/hear style");
        ITEMS_MAIN.add("Vacations");
    }

    public static List<String> ITEMS_FOOD = new ArrayList<String>();
    static {
        ITEMS_FOOD.add("Bars");
        ITEMS_FOOD.add("Restaurants");
        ITEMS_FOOD.add("Cafe");
    }

    public static List<String> ITEMS_SPA = new ArrayList<String>();
    static {
        ITEMS_SPA.add("Spa");
        ITEMS_SPA.add("Hears stile");
        ITEMS_SPA.add("Mani and Pedicure");
        ITEMS_SPA.add("Vacations");
    }

    public static List<String> ITEMS_SHOP = new ArrayList<>();
    static {
        ITEMS_SHOP.add("Men");
        ITEMS_SHOP.add("Women");
        ITEMS_SHOP.add("Kids");
        ITEMS_SHOP.add("For Home");
    }

    public static List<String> ITEMS_VACATION = new ArrayList<>();
    static {
        ITEMS_VACATION.add("Local");
        ITEMS_VACATION.add("Abroad");
        ITEMS_VACATION.add("Attractions");
    }

    public static Map<String, List<String>> TOTAL_MENU = new HashMap<String, List<String>>();
    static {
        TOTAL_MENU.put("Food and Drink", ITEMS_FOOD);
        TOTAL_MENU.put("Shop", ITEMS_SHOP);
        TOTAL_MENU.put("Spa/hear style", ITEMS_SPA);
        TOTAL_MENU.put("Vacations", ITEMS_VACATION);
    }
}
