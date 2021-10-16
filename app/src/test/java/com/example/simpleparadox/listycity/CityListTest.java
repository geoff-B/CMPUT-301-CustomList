package com.example.simpleparadox.listycity;





import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CityListTest {
    private CityList mockCityList(){
        CityList cityList = new CityList();
        cityList.add(mockCity());
        return cityList;
    }

    private City mockCity() {
        return new City("Edmonton", "AB");

    }
    @Test
    public void testAdd() {
        CityList cityList = mockCityList();
        assertEquals(1, cityList.getCities().size());
    }

    @Test
    void testAddException() {
        CityList cityList = mockCityList();
        City city = new City("Yellowknife", "Northwest Territories");
        cityList.add(city);
        assertThrows(IllegalArgumentException.class, () -> {
            cityList.add(city);
        });
    }


    @Test
    void testGetCities() {
        CityList cityList = mockCityList();
        assertEquals(0, mockCity().compareTo(cityList.getCities().get(0)));
        City city = new City("Charlottetown", "Prince Edward Island");
        cityList.add(city);
        assertEquals(0, city.compareTo(cityList.getCities().get(0)));
        assertEquals(0, mockCity().compareTo(cityList.getCities().get(1)));
    }
    @Test
    void testHasCity() {
        CityList cityList = mockCityList();
        City city = new City("Charlottetown", "Prince Edward Island");
        City notCity = new City("Uranium City","SK");
        cityList.add(city);
        assertEquals(true,cityList.hasCity(city));
        assertEquals(false,cityList.hasCity(notCity));
    }

    @Test
    void testDelete() {
        CityList cityList = mockCityList();
        City city = new City("Charlottetown", "Prince Edward Island");
        cityList.add(city);
        assertEquals(2, cityList.getCities().size()); // city should be added
        cityList.delete(city);
        assertEquals(1, cityList.getCities().size()); // city should be removed

        assertThrows(IllegalArgumentException.class,() -> {
            cityList.delete(city); // test exception
        });
    }
    @Test
    void testCountCities() {
        CityList cityList = mockCityList();
        assertEquals(1, cityList.countCities());
        City city = new City("Charlottetown", "Prince Edward Island");
        cityList.add(city);
        assertEquals(2, cityList.countCities());
    }




}


