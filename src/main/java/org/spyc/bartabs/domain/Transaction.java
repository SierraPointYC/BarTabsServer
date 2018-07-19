package org.spyc.bartabs.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * User: mtali
 * Date: 10/09/14
 * Time: 20:30
 */
@Entity
public class Transaction {

	public enum Status {
		UNPAID, PAID, CANCELLED
	}
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
	@JoinColumn (name="user_id")
	@JsonBackReference  
	private User user;

    @Column(nullable = false)
    private Integer items;

    @Column(nullable = false)
    private Integer amount;
    
    @Column(nullable = false)
	@Enumerated(EnumType.STRING)    
    private ItemType item;

    @Column(nullable = false)
	@Enumerated(EnumType.STRING)    
    private Department department;
    
    @Column(nullable = false)
	@Enumerated(EnumType.STRING)    
    private Status status;
    
    @Column(nullable = false)
    private Date openDate;
   
    @Column(nullable = true)
    private Date closeDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getItems() {
		return items;
	}

	public void setItems(Integer items) {
		this.items = items;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public ItemType getItem() {
		return item;
	}

	public void setItem(ItemType item) {
		this.item = item;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
}
