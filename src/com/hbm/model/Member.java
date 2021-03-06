package com.hbm.model;

import java.util.HashSet;
import java.util.Set;

public class Member {
	private String id;
	private User user;
	private double amount;
	private String info;
	private Activity activity;
	private int type = 0;
	private Set<Item> joinItems = new HashSet<Item>();
	
	private Set<Message> messages=new HashSet<Message>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Set<Item> getJoinItems() {
		return joinItems;
	}
	public void setJoinItems(Set<Item> joinItems) {
		this.joinItems = joinItems;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Set<Message> getMessages() {
		return messages;
	}
	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}
}
