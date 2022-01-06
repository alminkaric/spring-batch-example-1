package com.techprimers.springbatchexample1.listener;

import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

import com.techprimers.springbatchexample1.model.User;

@Component
public class InvalidItemsListener implements ItemProcessListener<User, User> {

	@Override
	public void beforeProcess(User user) {
	}

	@Override
	public void afterProcess(User user, User result) {
		if (result == null) {
			System.out.println(user + " has been filtered because it is invalid");
		}
	}

	@Override
	public void onProcessError(User user, Exception e) {
		System.out.println(user + " is invalid due to " + e.getMessage() );
	}
}
