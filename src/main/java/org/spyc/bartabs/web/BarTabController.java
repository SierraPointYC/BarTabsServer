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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.spyc.bartabs.service.BarTabService;

@Controller
public class BarTabController {

    @Autowired
    private BarTabService service;

    @RequestMapping("/admin/exportDB")
    @ResponseBody
    public String exportDB() {
        return this.service.exportDB();
    }

    @RequestMapping("/admin/{command}")
    String getView(@PathVariable String command, Model model) {
        return command;
    }    

    @PostMapping("/admin/importDB")
    String postData(@RequestBody String data, Model model) {
    	service.exportDB();
        return "importDBResult";
    }    
    
    @RequestMapping("/admin/listUsers")
    String getListUsers(Model model) {
        model.addAttribute("msg", "Hello there, This message has been injected from the controller method");
        model.addAttribute("nameList", this.service.getAllUsers());
        return "listUsers";
    }        
 
    @RequestMapping("/admin/initDB")
    String getAddUser(Model model) {
    	this.service.addDefaultUsers();
    	this.service.addDefaultItems();
        return "initDB";
    }            
}
