package com.gebeya.Friend.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gebeya.Friend.Model.Friend;
	

public interface FriendService extends PagingAndSortingRepository<Friend, Integer> {
	public Iterable<Friend> findByFirstNameAndLastName(String firstName, String LastName);
	public Iterable<Friend> findByFirstName(String firstName);
	public Iterable<Friend> findByLastName(String LastName);
}
