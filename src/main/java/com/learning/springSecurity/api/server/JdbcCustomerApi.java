package com.learning.springSecurity.api.server;

import com.learning.springSecurity.entity.JdbcCustomer;
import com.learning.springSecurity.repository.JdbcCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sqlinjection/v1")
public class JdbcCustomerApi {

    @Autowired
    private JdbcCustomerRepository repository;

    @GetMapping(value = "/customer/{email}")
    public JdbcCustomer findCustomerByEmail(@PathVariable(required=  true, name="email") String email){
        return repository.findCustomerByEmail(email);
    }

    @GetMapping(value="/customer")
    public List<JdbcCustomer> findCustomersByGender(@RequestParam(required = true,name="gendercode") String genderCode){
        return repository.findCustomersByGender(genderCode);
    }

    @PostMapping(value="/customer")
    public void createCustomer(@RequestBody(required = true) JdbcCustomer jdbcCustomer){
        repository.createCustomer(newCustomer);
    }

    @PatchMapping(value = "/customer/{customerId}")
    public void updateCustomersById(@PathVariable(required = true, name = "customerId") int customerId, @RequestBody(required = true) JdbccustomerPatchRequest request){
        repository.updateCustomerFullName(customerId, request.getNewFullName());
    }
}
