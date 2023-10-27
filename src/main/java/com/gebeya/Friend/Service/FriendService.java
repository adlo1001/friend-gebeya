package com.gebeya.Friend.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gebeya.Friend.Model.Friend;

@Repository
public interface FriendService extends CrudRepository<Friend, Integer> {
	public Iterable<Friend> findByFirstNameAndLastName(String firstName, String LastName);

	public Iterable<Friend> findByFirstName(String firstName);

	public Iterable<Friend> findByLastName(String LastName);
}
