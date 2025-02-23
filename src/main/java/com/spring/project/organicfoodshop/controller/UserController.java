package com.spring.project.organicfoodshop.controller;

import com.spring.project.organicfoodshop.domain.Address;
import com.spring.project.organicfoodshop.domain.Cart;
import com.spring.project.organicfoodshop.domain.Role;
import com.spring.project.organicfoodshop.domain.User;
import com.spring.project.organicfoodshop.domain.request.management.user.AddCustomerRequest;
import com.spring.project.organicfoodshop.domain.request.management.user.AddEmployeeRequest;
import com.spring.project.organicfoodshop.domain.request.management.user.EditCustomerRequest;
import com.spring.project.organicfoodshop.domain.response.management.user.*;
import com.spring.project.organicfoodshop.service.RoleService;
import com.spring.project.organicfoodshop.service.UserService;
import com.spring.project.organicfoodshop.service.mapper.UserMapper;
import com.spring.project.organicfoodshop.util.annotation.ApiRequestMessage;
import com.spring.project.organicfoodshop.util.constant.GenderEnum;
import com.spring.project.organicfoodshop.util.constant.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/customers")
    @ApiRequestMessage("Call add customer API request")
    public ResponseEntity<AddedCustomerResponse> addCustomer(@RequestBody AddCustomerRequest request) {
        User user = UserMapper.INSTANCE.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleService.getRoleByName(RoleEnum.CUSTOMER);

        for (Address address : request.getAddresses()) {
            address.setUser(user);
            user.getAddresses().add(address);
        }

        Cart cart = new Cart();
        cart.setUser(user);
        user.setCart(cart);

        user.setAddresses(request.getAddresses());
        user.setRoles(Collections.singleton(role));
        user.setAddresses(request.getAddresses());
        user = userService.handleSaveUser(user);
        AddedCustomerResponse response = UserMapper.INSTANCE.toAddedCustomerResponse(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/customers/{customerId}")
    @ApiRequestMessage("Call edit customer API request")
    public ResponseEntity<EditedCustomerResponse> editCustomer(@PathVariable Long customerId, @RequestBody EditCustomerRequest request) {
        User user = userService.getUserById(customerId);
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setGender(request.getGender());
        user.setDob(request.getDob());

        user.getAddresses().clear();
        for (Address address : request.getAddresses()) {
            address.setUser(user);
            user.getAddresses().add(address);
        }

        user = userService.handleSaveUser(user);
        EditedCustomerResponse response = UserMapper.INSTANCE.toEditedCustomerResponse(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/customers/{customerId}")
    @ApiRequestMessage("Call customer detail API request")
    public ResponseEntity<GotCustomerDetailResponse> getCustomerDetail(@PathVariable Long customerId) {
        User user = userService.getUserById(customerId);
        GotCustomerDetailResponse response = UserMapper.INSTANCE.toGotCustomerDetailResponse(user);
        response.setGenderName(user.getGender().name());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/customers")
    @ApiRequestMessage("Call get all customers API request")
    public ResponseEntity<GotAllCustomersResponse> getAllCustomers() {
        List<User> customers = userService.getAllCustomers();
        List<GotAllCustomersResponse.Item> items = UserMapper.INSTANCE.toAllCustomerItems(customers);
        GotAllCustomersResponse response = GotAllCustomersResponse.builder().items(items).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/employees")
    @ApiRequestMessage("Call get all employees API request")
    public ResponseEntity<GotAllEmployeesResponse> getAllEmployees() {
        List<User> employees = userService.getAllEmployees();
        List<GotAllEmployeesResponse.Item> items = UserMapper.INSTANCE.toAllEmployeeItems(employees);
        GotAllEmployeesResponse response = GotAllEmployeesResponse.builder().items(items).build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/employees")
    @ApiRequestMessage("Call add employee API request")
    public ResponseEntity<AddedEmployeeResponse> addEmployee(@RequestBody AddEmployeeRequest request) {
        User user = UserMapper.INSTANCE.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleService.getRoleByName(RoleEnum.EMPLOYEE);
        user.getRoles().add(role);
        user = userService.handleSaveUser(user);
        AddedEmployeeResponse response = UserMapper.INSTANCE.toAddedEmployeeResponse(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
