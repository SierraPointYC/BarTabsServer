/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.spyc.bartabs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.spyc.bartabs.domain.Department;
import org.spyc.bartabs.domain.Item;
import org.spyc.bartabs.domain.ItemType;
import org.spyc.bartabs.domain.Payment;
import org.spyc.bartabs.domain.Payment.PaymentMethod;
import org.spyc.bartabs.domain.Transaction;
import org.spyc.bartabs.domain.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class BarTabService {

    private static final String TRANSACTION = "Transaction";

	private static final String PAYMENT = "Payment";

	private static final String SEPARATOR = ",";

	private static final String ITEM = "Item";

	private static final String USER = "User";

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
	
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    public List<User> getAllUsers() {
    	Iterable<User> it = userRepository.findAll();
    	List<User> users = new ArrayList<User>();
    	for (User user : it) {
    		users.add(user);
    	}
        return users;
    }

    public User getUser(long id) {
    	User user = userRepository.findOne(id);
        return user;
    }    
    
    public void updateUser(User user) {
    	userRepository.save(user);
    }
    
    public List<Transaction> getTransactions() {
    	Iterable<Transaction> it = transactionRepository.findAll();
    	List<Transaction> transactions = new ArrayList<Transaction>();
    	for (Transaction transaction : it) {
    		transactions.add(transaction);   		
    	}
        return transactions;
    }    

    public List<Payment> getPayments() {
    	Iterable<Payment> it = paymentRepository.findAll();
    	List<Payment> payments = new ArrayList<Payment>();
    	for (Payment payment : it) {
    		payments.add(payment);
    	}
        return payments;
    }    
    
    public List<Item> getItems() {
    	Iterable<Item> it = itemRepository.findAll();
    	List<Item> items = new ArrayList<Item>();
    	for (Item item : it) {
    		items.add(item);
    	}
        return items;
    }        
    
    public BarTab getBarTab(long userId) {
    	User user = userRepository.findOne(userId);
    	Iterable<Transaction> it = transactionRepository.findByStatusAndUserId(Transaction.Status.UNPAID, userId);
    	BarTab barTab = null;
    	for (Transaction transaction : it) {
    		if (barTab == null) {
    			barTab = new BarTab(user, transaction.getOpenDate());
    		}
    		barTab.addToItemCount(transaction.getItem(), transaction.getItems());   
    		barTab.addToItemTotal(transaction.getItem(), transaction.getAmount());   
    		barTab.incTotalTransactions();
    		barTab.addToTotalAmount(transaction.getAmount());
    		barTab.setLastTime(transaction.getOpenDate());
    	}
    	return barTab;
    }
    
    public List<BarTab> getBarTabs() {
    	Iterable<Transaction> it = transactionRepository.findByStatus(Transaction.Status.UNPAID);
    	List<String> users = new ArrayList<String>();
    	Map<String, BarTab> tabMap = new HashMap<String, BarTab>();
    	for (Transaction transaction : it) {
    		String name = transaction.getUser().getName();
    		BarTab tab = tabMap.get(name);
    		if (tab == null) {
    			users.add(name);
    			tab = new BarTab(transaction.getUser(), transaction.getOpenDate());
    			tabMap.put(name, tab);
    		}
    		tab.addToItemCount(transaction.getItem(), transaction.getItems());   
    		tab.addToItemTotal(transaction.getItem(), transaction.getAmount());   
    		tab.incTotalTransactions();
    		tab.addToTotalAmount(transaction.getAmount());
    		tab.setLastTime(transaction.getOpenDate());
    	}
    	List<BarTab> tabs = new ArrayList<BarTab>(tabMap.size());
    	for (String name : users) {
    		tabs.add(tabMap.get(name));
    	}
        return tabs;    	
    }
    
    public String exportDB() {
        Iterator<User> users = userRepository.findAll().iterator();
        StringBuilder out = new StringBuilder();
        while(users.hasNext()){
            out.append(USER).append(SEPARATOR);
            User u = users.next();
            out.append(u.getId()).append(SEPARATOR);
            out.append(u.getName()).append(SEPARATOR);
            out.append(u.getPin()).append(SEPARATOR);
            out.append(u.getTag()).append(System.lineSeparator());          
        }
        Iterator<Item> items = itemRepository.findAll().iterator();
        while(items.hasNext()){
            out.append(ITEM).append(SEPARATOR);
            Item i = items.next();
            out.append(i.getId()).append(SEPARATOR);
            out.append(i.getType().toString()).append(SEPARATOR);
            out.append(i.getDepartment().toString()).append(SEPARATOR);            
            out.append(i.getCost()).append(System.lineSeparator());            
        }
        Iterator<Payment> payments = paymentRepository.findAll().iterator();
        while(payments.hasNext()){
            out.append(PAYMENT).append(SEPARATOR);
            Payment p = payments.next();
            out.append(p.getId()).append(SEPARATOR);
            out.append(p.getMethod().toString()).append(SEPARATOR);
            out.append(p.getDepartment().toString()).append(SEPARATOR);            
            out.append(p.getAmount()).append(SEPARATOR);            
            out.append(p.getDate()).append(SEPARATOR);
            out.append(p.getUser().getName()).append(System.lineSeparator());           
        }        
        Iterator<Transaction> transactions = transactionRepository.findAll().iterator();
        while(transactions.hasNext()){
            out.append(TRANSACTION).append(SEPARATOR);
            Transaction t = transactions.next();
            out.append(t.getId()).append(SEPARATOR);
            out.append(t.getDepartment().toString()).append(SEPARATOR);
            out.append(t.getItem().toString()).append(SEPARATOR);            
            out.append(t.getItems()).append(SEPARATOR);
            out.append(t.getAmount()).append(SEPARATOR);            
            out.append(t.getUser().getName()).append(SEPARATOR);           
            out.append(t.getStatus().toString()).append(SEPARATOR);           
            out.append(t.getOpenDate()).append(SEPARATOR);
            out.append(t.getCloseDate()).append(SEPARATOR).append(System.lineSeparator());
        }                
        return out.toString();
    }
        
    public void addDefaultUsers() {
    	User user = new User();
    	user.setName("Martin Tali");
    	user.setPin("94404");
    	user.setTag("045A14C29F3C80");
    	userRepository.save(user);
    	user = new User();
    	user.setName("Michael Bell");
    	user.setPin("12345");
    	user.setTag("042ED36A373C80");
    	userRepository.save(user);
    	user = new User();
    	user.setName("Michael Lael");
    	user.setPin("54321");
    	userRepository.save(user);
    }   
    
    public void addDefaultItems() {
    	Item item = new Item();
    	item.setType(ItemType.SODA);
    	item.setCost(1);
    	item.setDepartment(Department.BAR);
    	itemRepository.save(item);
    	item = new Item();
    	item.setType(ItemType.JUICE);
    	item.setCost(2);
    	item.setDepartment(Department.BAR);
    	itemRepository.save(item);
    	item = new Item();
    	item.setType(ItemType.BEER);
    	item.setCost(3);
    	item.setDepartment(Department.BAR);
    	itemRepository.save(item);
    	item = new Item();
    	item.setType(ItemType.WINE);
    	item.setCost(5);
    	item.setDepartment(Department.BAR);
    	itemRepository.save(item);
    	item = new Item();
    	item.setType(ItemType.WINE_BOTTLE);
    	item.setCost(20);
    	item.setDepartment(Department.BAR);
    	itemRepository.save(item);    	
    	item = new Item();
    	item.setType(ItemType.DRINK);
    	item.setCost(5);
    	item.setDepartment(Department.BAR);
    	itemRepository.save(item);  
    	item = new Item();
    	item.setType(ItemType.CALL_DRINK);
    	item.setCost(7);
    	item.setDepartment(Department.BAR);
    	itemRepository.save(item);
    	item = new Item();
    	item.setType(ItemType.CORKAGE);
    	item.setCost(5);
    	item.setDepartment(Department.DONATION);
    	itemRepository.save(item);
    	item = new Item();
    	item.setType(ItemType.MEAL);
    	item.setCost(9);
    	item.setDepartment(Department.FOOD);
    	itemRepository.save(item);
    	item = new Item();
    	item.setType(ItemType.DINNER);
    	item.setCost(20);
    	item.setDepartment(Department.FOOD);
    	itemRepository.save(item);
     }

	public void importLine(String line) {
		String[] tokens = line.split(SEPARATOR);
		if (USER.equals(tokens[0])) {
			importUser(tokens);
			return;
		}
		if (ITEM.equals(tokens[0])) {
			importItem(tokens);
			return;
		}
		if (PAYMENT.equals(tokens[0])) {
			importPayment(tokens);
			return;
		}
		if (TRANSACTION.equals(tokens[0])) {
			importTransaction(tokens);
			return;
		}				
	}

	private void importUser(String[] tokens) {
    	User user = new User();
    	user.setId(Long.valueOf(tokens[1]));
    	user.setName(tokens[2]);
    	user.setPin(tokens[3]);
    	user.setTag(tokens[4]);
    	userRepository.save(user);
	}
	
	private void importItem(String[] tokens) {
    	Item item = new Item();
    	item.setId(Long.valueOf(tokens[1]));
    	item.setType(ItemType.valueOf(tokens[2]));
    	item.setDepartment(Department.valueOf(tokens[3]));
    	item.setCost(Integer.valueOf(tokens[4]));
    	itemRepository.save(item);
	}
	
	private void importPayment(String[] tokens) {
    	Payment payment = new Payment();
    	payment.setId(Long.valueOf(tokens[1]));
    	payment.setMethod(PaymentMethod.valueOf(tokens[2]));
    	payment.setDepartment(Department.valueOf(tokens[3]));
    	payment.setAmount(Integer.valueOf(tokens[4]));
    	try {
    		String date = tokens[5];
    		if (!"null".equals(date)) {
    			payment.setDate(dateFormat.parse(date));
    		}
 		} catch (ParseException e) {
			e.printStackTrace();
		}    	
    	payment.setUser(userRepository.findByName(tokens[6]));
    	paymentRepository.save(payment);
	}	

	private void importTransaction(String[] tokens) {
    	Transaction  payment = new Transaction();
    	payment.setId(Long.valueOf(tokens[1]));
    	payment.setDepartment(Department.valueOf(tokens[2]));
    	payment.setItem(ItemType.valueOf(tokens[3]));
    	payment.setItems(Integer.valueOf(tokens[4]));
    	payment.setAmount(Integer.valueOf(tokens[5]));
    	payment.setUser(userRepository.findByName(tokens[6]));
    	payment.setStatus(Transaction.Status.valueOf(tokens[7]));
    	try {
    		String date = tokens[8];
    		if (!"null".equals(date)) {
    			payment.setOpenDate(dateFormat.parse(date));
    		}
    		date = tokens[9];
    		if (!"null".equals(date)) {
    			payment.setCloseDate(dateFormat.parse(date));
    		}
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	transactionRepository.save(payment);
	}	
	
	public void cleanDatabase() {
		transactionRepository.deleteAll();
		paymentRepository.deleteAll();
		userRepository.deleteAll();
		itemRepository.deleteAll();
	}
}
