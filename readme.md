# Workshop for Nordic testing Days 2022

## Code used in workshop

There are 10 Tests that demonstrate how could we write quality Unit tests, which we will be walking through in the workshop

We start by writing tests for a simple Restaurant Booking System, which allows customers to submit booking request.

The system is capable of:
- Validating the Booking Request
- Connecting to a Membership system
- Connecting to a Restaurant Enquiry System

To simply the scenario, let's assume we have two restaurants available:

**Under RestaurantEnquiryServiceImpl**

```
private final List<String> supportedRestaurants = List.of("CrazyChicken", "YummyYummy");
```

**Under MembershipSystem**

```
Map<String, Member> memberShip = Map.of(
"memberId1", new Member("memberId1", "Tony", "070-12345"),
"memberId2", new Member("memberId2", "Helen", "070-45678"),
"memberId3", new Member("memberId3", "Jack", "070-55555")
);
```