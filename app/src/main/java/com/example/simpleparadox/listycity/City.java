package com.example.simpleparadox.listycity;
/**
 * The class that holds a City name and its province name
 */
public class City implements Comparable<City> {

    private String city;
    private String province;

    City(String city, String province){
        this.city = city;
        this.province = province;
    }
    /**
     * This method returns the name of the city
     * @return String containing the name of the city
     */
    public String getCityName(){
        return this.city;
    }

    /**
     * This method returns the name of the city's province
     * @return String containing the name of the city's province
     */
    public String getProvinceName(){
        return this.province;
    }

    /**
     * This method compares cities based on their names
     * @param city
     *         Prospective city to compare
     * @return
     *      -1,0,1, based on whether a city comes before or after, or equal.
     */
    @Override
    public int compareTo(City city) {
        return this.city.compareTo(city.getCityName());
    }
}
