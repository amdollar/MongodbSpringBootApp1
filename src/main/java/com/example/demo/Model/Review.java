package com.example.demo.Model;

public class Review {
	private String username;
	private int rating;
	private boolean approved;

	public Review() {
	}

	public Review(String username, int rating, boolean approved) {
		this.username = username;
		this.rating = rating;
		this.approved = approved;
	}

	@Override
	public String toString() {
		return "Review [username=" + username + ", rating=" + rating + ", approved=" + approved + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

}
