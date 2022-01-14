package org.craftedsw.tripservicekata.trip;

import java.util.Collections;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

	public List<Trip> findFriendTrips(User Friend) throws UserNotLoggedInException {
		// Sandro Mancuso: check whether user is logged in should be done by client (e.g. controller)
		// TripService should receive the loggedUser as a parameter
		// See https://www.codurance.com/publications/2011/07/18/testing-legacy-hard-wired-dependencies_17
		User loggedUser = loggedUser();
		ensureUserIsLoggedIn(loggedUser);
		return loggedUser.isFriendsWith(Friend) ? findTripsForFriend(Friend) : Collections.emptyList();
	}

	private void ensureUserIsLoggedIn(User loggedUser) {
		if (loggedUser == null) {
			throw new UserNotLoggedInException();
		}
	}

	protected List<Trip> findTripsForFriend(User friend) {
		return TripDAO.findTripsByUser(friend);
	}

	protected User loggedUser() {
		return UserSession.getInstance().getLoggedUser();
	}

}
