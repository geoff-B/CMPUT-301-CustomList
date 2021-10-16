package com.example.simpleparadox.listycity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is a class that keeps track of a list of city objects
 */
public class CityList {
    private List<City> cities = new ArrayList<>();

    /**
     * This adds a city to the list if the city does not exist
     *
     * @param city This is a candidate city to add
     */
    public void add(City city) {
        if (cities.contains(city)) {
            throw new IllegalArgumentException();
        }
        cities.add(city);
    }

    /**
     * This returns a sorted list of cities
     *
     * @return Return the sorted list
     */
    public List<City> getCities() {
        List<City> list = cities;
        Collections.sort(list);
        return list;


    }

    /**
     * Checks if a given city exists
     *
     * @param city This is the city to check
     * @return Return a boolean noting if the city exists
     */
    public boolean hasCity(City city) {
        if (cities.contains(city)) {
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * Removes a city from the list if it exists
     *
     * @param city This is the city to check
     */
    public void delete(City city) {
        if (!cities.contains(city)) {
            throw new IllegalArgumentException();
        }
        else {
            cities.remove(city);
        }
    }
    /**
     * Returns how many cities are in the list
     *
     * @return Return an integer value of the amount of cities in the list
     */
    public int countCities() {
        return cities.size();
    }
}
