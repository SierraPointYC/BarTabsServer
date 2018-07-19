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
import org.spyc.bartabs.domain.Name;
import org.spyc.bartabs.domain.Payment;
import org.spyc.bartabs.domain.Transaction;
import org.spyc.bartabs.domain.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class BarTabService {


    @Autowired
    private NameRepository nameRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    public List<String> getAllUsers() {
    	List<String> nameList = new ArrayList<String>();
        Iterator<Name> names = nameRepository.findAll().iterator();
        while(names.hasNext()){
            nameList.add(names.next().getValue());
        }
        return nameList;
    }
    
    public String exportDB() {
        Iterator<User> users = userRepository.findAll().iterator();
        StringBuilder out = new StringBuilder();
        while(users.hasNext()){
            out.append("User,");
            User u = users.next();
            out.append(u.getId()).append(",");
            out.append(u.getName()).append(",");
            out.append(u.getPin()).append(System.lineSeparator());          
        }
        Iterator<Item> items = itemRepository.findAll().iterator();
        while(items.hasNext()){
            out.append("Item,");
            Item i = items.next();
            out.append(i.getId()).append(",");
            out.append(i.getType().toString()).append(",");
            out.append(i.getDepartment().toString()).append(",");            
            out.append(i.getCost()).append(System.lineSeparator());            
        }
        Iterator<Payment> payments = paymentRepository.findAll().iterator();
        while(payments.hasNext()){
            out.append("Payment,");
            Payment p = payments.next();
            out.append(p.getId()).append(",");
            out.append(p.getMethod().toString()).append(",");
            out.append(p.getDepartment().toString()).append(",");            
            out.append(p.getAmount()).append(",");            
            out.append(p.getDate()).append(",");
            out.append(p.getUser().getId()).append(System.lineSeparator());           
        }        
        Iterator<Transaction> transactions = transactionRepository.findAll().iterator();
        while(transactions.hasNext()){
            out.append("Transaction,");
            Transaction t = transactions.next();
            out.append(t.getId()).append(",");
            out.append(t.getDepartment().toString()).append(",");
            out.append(t.getItem().toString()).append(",");            
            out.append(t.getItems()).append(",");
            out.append(t.getAmount()).append(",");            
            out.append(t.getUser().getId());           
            out.append(t.getStatus().toString());           
            out.append(t.getOpenDate()).append(",");
            out.append(t.getCloseDate()).append(",").append(System.lineSeparator());
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
}
