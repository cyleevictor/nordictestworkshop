package org.nordic.testdays.workshop;

import java.util.List;

public class RestaurantEnquiryServiceImpl implements RestaurantEnquiryService {
    private final List<String> supportedRestaurants = List.of("CrazyChicken", "YummyYummy");
    @Override
    public boolean query(String restaurantName) {
        return supportedRestaurants.contains(restaurantName);
    }
}
