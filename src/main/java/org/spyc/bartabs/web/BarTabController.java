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

package org.spyc.bartabs.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.spyc.bartabs.domain.User;
import org.spyc.bartabs.service.BarTabService;

@Controller
public class BarTabController {

    @Autowired
    private BarTabService service;

    @RequestMapping("/")
    public String index() {
        return "index";
    }    

        
    @RequestMapping("/admin/exportDB")
    @ResponseBody
    public String exportDB() {
        return this.service.exportDB();
    }

    @GetMapping("/admin/{command}")
    String getView(@PathVariable String command, Model model) {
        return command;
    }    

    @GetMapping("/admin/updateTag")
    String updateTagForm(@RequestParam String userId, Model model) {
    	User user = this.service.getUser(Long.valueOf(userId));
        model.addAttribute("user", user);
        return "updateTag";
    }    

    @GetMapping("/admin/updatePin")
    String updatePinForm(@RequestParam String userId, Model model) {
    	User user = this.service.getUser(Long.valueOf(userId));
        model.addAttribute("user", user);
        return "updatePin";
    }        
    
    @PostMapping("/admin/updateTag")
    String updateTagRequest(@RequestParam String userId, @RequestParam String tagId, Model model) {
    	User user = this.service.getUser(Long.valueOf(userId));
    	user.setTag(tagId);
    	service.updateUser(user);
        model.addAttribute("users", this.service.getAllUsers());
        return "listUsers";
    }
 
    @PostMapping("/admin/updatePin")
    String updatePinRequest(@RequestParam String userId, @RequestParam String pin, Model model) {
    	User user = this.service.getUser(Long.valueOf(userId));
    	user.setPin(pin);
    	service.updateUser(user);
        model.addAttribute("users", this.service.getAllUsers());
        return "listUsers";
    }    
    
    @PostMapping("/admin/addUser")
    String updateTagRequest(@RequestParam String userName, @RequestParam String pin, @RequestParam String tagId, Model model) {
    	User user = new User();
    	user.setName(userName);
    	user.setPin(pin);
   	
    	user.setTag(tagId);
    	service.updateUser(user);
        model.addAttribute("users", this.service.getAllUsers());
        return "listUsers";
    }
    
    @PostMapping("/admin/importDB")
    String postData(@RequestParam("file") MultipartFile file, Model model) {
    	try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
			service.cleanDatabase();
			while(true) {
				String line = reader.readLine();
				if (line == null) {
					break;
				}
				service.importLine(line);
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        return "importDBResult";
    }    
    
    @RequestMapping("/admin/listUsers")
    String getListUsers(Model model) {
        model.addAttribute("users", this.service.getAllUsers());
        return "listUsers";
    }        

    @RequestMapping("/admin/listTransactions")
    String getListTransactions(Model model) {
        model.addAttribute("transactions", this.service.getTransactions());
        return "listTransactions";
    }            
    
    @RequestMapping("/admin/listPayments")
    String getListPayments(Model model) {
        model.addAttribute("payments", this.service.getPayments());
        return "listPayments";
    }         

    @RequestMapping("/admin/listBarTabs")
    String getListBarTabs(Model model) {
        model.addAttribute("bartabs", this.service.getBarTabs());
        return "listBarTabs";
    }
    
    @RequestMapping("/admin/showBarTab")
    String getShowBarTab(@RequestParam String userId, Model model) {    	
        model.addAttribute("bartab", this.service.getBarTab(Long.valueOf(userId)));
        return "showBarTab";
    }    
    
    @RequestMapping("/admin/listItems")
    String getListItems(Model model) {
        model.addAttribute("items", this.service.getItems());
        return "listItems";
    }       
    
    @RequestMapping("/admin/initDB")
    String getAddUser(Model model) {
    	this.service.addDefaultUsers();
    	this.service.addDefaultItems();
        return "initDB";
    }            
}
