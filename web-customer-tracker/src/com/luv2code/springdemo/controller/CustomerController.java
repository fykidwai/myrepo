package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.ICustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private ICustomerService customerService;

	@GetMapping("/list")
	public String listCustomers(Model model) {

		// get the customers from DAO
		List<Customer> customers = customerService.getCustomers();

		// add customer list to model
		model.addAttribute("customers", customers);

		return "list-customers";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {

		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "customer-form";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {

		customerService.saveCustomer(customer);
		return "redirect:/customer/list";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id, Model model) {

		// get the customer from service
		Customer customer = customerService.getCustomer(id);

		// set customer as model attribute to pre-populate the form
		model.addAttribute("customer", customer);
		// send over to edit form
		return "customer-form";
	}

	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int id) {

		// delete the customer
		customerService.deleteCustomer(id);
		return "redirect:/customer/list";
	}
}
