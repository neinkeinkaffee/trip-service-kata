package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TripServiceTest {
    private User loggedUser = new User();
    private TripService tripService = createTripService();

    @Test
    void shouldThrowExceptionIfUserIsNotLoggedIn() {
        loggedUser = null;
        assertThrows(UserNotLoggedInException.class, () -> tripService.findFriendTrips(loggedUser));
    }

    @Test
    void shouldNotReturnTripsIfLoggedInUserIsNotFriendsWithUser() {
        User queriedUser = new User();
        queriedUser.addTrip(new Trip());

        List<Trip> searchResult = tripService.findFriendTrips(queriedUser);

        assertThat(searchResult, IsEmptyCollection.empty());
    }

    @Test
    void shouldReturnTripsIfLoggedInUserIsFriendsWithUser() {
        User queriedUser = new User();
        queriedUser.addTrip(new Trip());
        queriedUser.addFriend(loggedUser);

        List<Trip> searchResult = tripService.findFriendTrips(queriedUser);

        assertEquals(searchResult, queriedUser.trips());
    }

    public TripService createTripService() {
        return new TripService() {
            @Override
            protected User loggedUser() {
                return loggedUser;
            }

            @Override
            protected List<Trip> findTripsForFriend(User friend) {
                return friend.trips();
            }
        };
    }
	
}
