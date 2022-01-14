package org.craftedsw.tripservicekata.trip;

import java.util.Collections;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

	public List<Trip> findFriendTrips(User Friend) throws UserNotLoggedInException {
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
