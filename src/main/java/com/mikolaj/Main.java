package com.mikolaj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

//@SpringBootApplication
@ComponentScan(basePackages = "com.mikolaj")
@EnableAutoConfiguration
@Configuration
@RestController
@RequestMapping("api/v1/customers")

public class Main {

    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

//    @GetMapping("/greet")
//    public GreetResponse greet(){
//        GreetResponse response = new GreetResponse(
//                "Hello",
//                List.of("Java","Python", "C++"),
//                new Person("Andrew", 18,0));
//
//        return response;
//    }

//    record GreetResponse(String greet, List<String> favProgrammingLanguages, Person person) {
//    }
//
//    record Person(String name, int age, double savings){}

    @GetMapping
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    record NewCustomerRequest(
            String name,
            String email,
            Integer age
    ){ }

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.name);
        customer.setEmail(request.email);
        customer.setAge(request.age);
        customerRepository.save(customer);
    }
    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }

    @PutMapping("{customerId}")
    public void updateCustomer(@RequestBody NewCustomerRequest customerUpdate, @PathVariable("customerId") Integer id){
        Optional<Customer> existingCustomerOptional = customerRepository.findById(id);

        if (existingCustomerOptional.isPresent()) {
            Customer existingCustomer = existingCustomerOptional.get();
            existingCustomer.setName(customerUpdate.name);
            existingCustomer.setEmail(customerUpdate.email);
            existingCustomer.setAge(customerUpdate.age);

            customerRepository.save(existingCustomer);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with ID " + id + " not found.");
        }
    }


}
