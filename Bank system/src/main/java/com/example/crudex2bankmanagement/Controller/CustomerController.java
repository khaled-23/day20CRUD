package com.example.crudex2bankmanagement.Controller;

import com.example.crudex2bankmanagement.ApiResponse.ApiResponse;
import com.example.crudex2bankmanagement.Model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    ArrayList<Customer> customers = new ArrayList<>();


    @RequestMapping("/add-customer")
    public ArrayList<Customer> displayCustomers() {
        return customers;
    }

    @GetMapping("/get")
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    @PostMapping("/add-customer")
    public ApiResponse addCustomer(@RequestBody Customer customer) {
        customers.add(customer);
        return new ApiResponse("customer added");
    }

    @PutMapping("/update-customer/{index}")
    public ApiResponse updateCustomer(@PathVariable int index, @RequestBody Customer customer) {
        customers.set(index, customer);
        return new ApiResponse("customer updated");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse updateCustomer(@PathVariable int index) {
        customers.remove(index);
        return new ApiResponse("customer deleted");
    }

    @PutMapping("/deposit/{index}/{amount}")
    public ApiResponse deposit(@PathVariable int index, @PathVariable double amount) {
        double balance = customers.get(index).getBalance() + amount;
        customers.get(index).setBalance(balance);
        return new ApiResponse("money deposited");
    }

    @PutMapping("/withdraw/{index}/{amount}")
    public ApiResponse withdraw(@PathVariable int index, @PathVariable double amount){
        double balance = customers.get(index).getBalance();

        if(customers.get(index).getBalance()>=amount){
            customers.get(index).setBalance(balance-amount);
            return new ApiResponse("money withdrew");
        }else return new ApiResponse("account doesn't have enough balance");

    }

}
