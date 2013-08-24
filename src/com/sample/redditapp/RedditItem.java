package com.sample.redditapp;

public class RedditItem {
	public String author;
	public String thumbail;
	public String title;
	
	@Override
	public String toString() {
		return "RedditItem [author=" + author + ", thumbail=" + thumbail
				+ ", title=" + title + "]";
	}
}
