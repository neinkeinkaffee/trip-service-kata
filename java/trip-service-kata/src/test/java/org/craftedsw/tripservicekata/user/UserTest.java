package org.craftedsw.tripservicekata.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {
    @Test
    void isFriendsWithShouldReturnTrueIfUserAreFriends() {
        User alice = new User();
        User bob = new User();
        bob.addFriend(alice);

        assertTrue(alice.isFriendsWith(bob));
    }
}
