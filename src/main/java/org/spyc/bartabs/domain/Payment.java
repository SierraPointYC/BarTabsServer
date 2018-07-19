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
public class Payment {

	public enum PaymentMethod {
		CASH_BOX, CASH_REGISTER, SQUARE, CLUBEXPRESS
	}
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
	@JoinColumn (name="user_id")
	@JsonBackReference 
	private User user;
        
    @Column(nullable = false)
	@Enumerated(EnumType.STRING)    
    private Department department;

    @Column(nullable = false)
    private Integer amount;
    
    @Column(nullable = false)
	@Enumerated(EnumType.STRING)    
    private PaymentMethod method;
    
    @Column(nullable = false)
    private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public PaymentMethod getMethod() {
		return method;
	}

	public void setMethod(PaymentMethod method) {
		this.method = method;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}	
}
