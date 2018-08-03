package org.spyc.bartabs.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.spyc.bartabs.domain.ItemType;
import org.spyc.bartabs.domain.User;

public class BarTab {
	private User user;
	private int totalAmount;
	private int totalTransactions;
	private Date openTime;
	private Date lastTime;
	private int itemCount[] = new int[ItemType.values().length];
	private int itemTotal[] = new int[ItemType.values().length];
	
	public BarTab(User user, Date open) {
		this.user = user;
		this.openTime = open;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public void addToTotalAmount(int totalAmount) {
		this.totalAmount += totalAmount;
	}
	public int getTotalTransactions() {
		return totalTransactions;
	}
	public void setTotalTransactions(int totalTransactions) {
		this.totalTransactions = totalTransactions;
	}
	public void incTotalTransactions() {
		this.totalTransactions++;
	}
	
	public Date getOpenTime() {
		return openTime;
	}
	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public int[] getItemCount() {
		return itemCount;
	}
	public void setItemCount(int[] itemCount) {
		this.itemCount = itemCount;
	}	
	public void addToItemCount(ItemType item, int count) {
		this.itemCount[item.ordinal()] += count;
	}
	
	public void addToItemTotal(ItemType item, int amount) {
		this.itemTotal[item.ordinal()] += amount;
	}
	
	public int getItemCount(ItemType item) {
		return itemCount[item.ordinal()];
	}

	public int getItemTotal(ItemType item) {
		
		return itemTotal[item.ordinal()];
	}
		
	public List<ItemType> getItemsOnTab() {
		List<ItemType> items = new ArrayList<ItemType>();
		for (int i = 0; i < itemCount.length; i++) {
			if (itemCount[i] > 0) {
				items.add(ItemType.values()[i]);
			}
		}
		return items;
	}

}
