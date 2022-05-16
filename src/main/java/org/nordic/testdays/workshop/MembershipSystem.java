package org.nordic.testdays.workshop;

import org.nordic.testdays.workshop.data.model.Member;

import java.util.Map;

public class MembershipSystem {
    Map<String, Member> memberShip = Map.of(
            "memberId1", new Member("memberId1", "Tony", "070-12345"),
            "memberId2", new Member("memberId2", "Helen", "070-45678"),
            "memberId3", new Member("memberId3", "Jack", "070-55555")
    );

    public Member lookup(String memberId) {
        return memberShip.get(memberId);
    }
}
