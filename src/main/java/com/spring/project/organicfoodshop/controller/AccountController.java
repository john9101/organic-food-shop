package com.spring.project.organicfoodshop.controller;
import com.spring.project.organicfoodshop.domain.Address;
import com.spring.project.organicfoodshop.domain.User;
import com.spring.project.organicfoodshop.domain.request.common.account.AddAccountAddressRequest;
import com.spring.project.organicfoodshop.domain.request.common.account.ChangeAccountPasswordRequest;
import com.spring.project.organicfoodshop.domain.request.common.account.EditAccountAddressRequest;
import com.spring.project.organicfoodshop.domain.request.common.account.EditAccountInfoRequest;
import com.spring.project.organicfoodshop.domain.response.common.account.*;
import com.spring.project.organicfoodshop.service.AddressService;
import com.spring.project.organicfoodshop.service.UserService;
import com.spring.project.organicfoodshop.service.mapper.AddressMapper;
import com.spring.project.organicfoodshop.service.mapper.UserMapper;
import com.spring.project.organicfoodshop.util.SecurityUtil;
import com.spring.project.organicfoodshop.util.annotation.ApiRequestMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final UserService userService;
    private final AddressService addressService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/current/info")
    @ApiRequestMessage("Call get account info API request")
    public ResponseEntity<GotAccountInfoResponse> getAccountInfo() {
        User user = userService.getUserByEmail(SecurityUtil.getCurrentUserPrincipal().orElse(null), false);
        GotAccountInfoResponse response = UserMapper.INSTANCE.toGotAccountInfoResponse(user);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/current/info")
    @ApiRequestMessage("Call edit account API request")
    public ResponseEntity<EditedAccountInfoResponse> editAccount(@RequestBody EditAccountInfoRequest request) {
        User user = userService.getUserByEmail(SecurityUtil.getCurrentUserPrincipal().orElse(null), false);
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setFullName(request.getFullName());
        user.setGender(request.getGender());
        user.setDob(request.getDob());
        user = userService.handleSaveUser(user);
        EditedAccountInfoResponse response = UserMapper.INSTANCE.toEditedAccountInfoResponse(user);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/current/password")
    @ApiRequestMessage("Call change account password API request")
    public ResponseEntity<ChangedAccountPasswordResponse> changeAccountPassowrd(@RequestBody ChangeAccountPasswordRequest request) {
        User user = userService.getUserByEmail(SecurityUtil.getCurrentUserPrincipal().orElse(null), false);
        if (passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        }
        user = userService.handleSaveUser(user);
        ChangedAccountPasswordResponse response = ChangedAccountPasswordResponse.builder().id(user.getId()).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/current/addresses")
    @ApiRequestMessage("Call get account addresses API request")
    public ResponseEntity<GotAccountAddressesResponse> getAccountAddresses() {
        User user = userService.getUserByEmail(SecurityUtil.getCurrentUserPrincipal().orElse(null), false);
        Set<Address> addresses = user.getAddresses();
        Set<GotAccountAddressesResponse.Item> items = AddressMapper.INSTANCE.toGotAccountAddressItems(addresses);
        GotAccountAddressesResponse response = GotAccountAddressesResponse.builder().items(items).build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/current/addresses")
    @ApiRequestMessage("Call add account addresses API request")
    public ResponseEntity<AddedAccountAddressResponse> addAccountAddress(@RequestBody AddAccountAddressRequest request) {
        User user = userService.getUserByEmail(SecurityUtil.getCurrentUserPrincipal().orElse(null), false);
        Address address = new Address();
        address.setUser(user);
        address.setProvince(request.getProvince());
        address.setCommune(request.getCommune());
        address.setSpecificPlace(request.getSpecificPlace());
        address.setDistrict(request.getDistrict());
        address = addressService.handleSaveAddress(address);
        AddedAccountAddressResponse response = AddressMapper.INSTANCE.toAddedAccountAddressResponse(address);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/current/addresses/{addressId}")
    @ApiRequestMessage("Call add account addresses API request")
    public ResponseEntity<GotAccountAddressDetailResponse> getAccountAddressDetail(@PathVariable Long addressId) {
        Address address = addressService.getAddressById(addressId);
        GotAccountAddressDetailResponse response = AddressMapper.INSTANCE.toGotAccountAddressDetailResponse(address);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/current/addresses/{addressId}")
    @ApiRequestMessage("Call add account addresses API request")
    public ResponseEntity<EditedAccountAddressResponse> editAccountAddress(@RequestBody EditAccountAddressRequest request, @PathVariable Long addressId) {
        Address address = addressService.getAddressById(addressId);
        address.setProvince(request.getProvince());
        address.setCommune(request.getCommune());
        address.setSpecificPlace(request.getSpecificPlace());
        address.setDistrict(request.getDistrict());
        address = addressService.handleSaveAddress(address);
        EditedAccountAddressResponse response = AddressMapper.INSTANCE.toEditedAccountAddressResponse(address);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/current/addresses/{addressId}")
    @ApiRequestMessage("Call add account addresses API request")
    public ResponseEntity<DeletedAccountAddressResponse> deleteAccountAddress(@PathVariable Long addressId) {
        addressService.deleteAddressById(addressId);
        DeletedAccountAddressResponse response = DeletedAccountAddressResponse.builder().id(addressId).build();
        return ResponseEntity.ok(response);
    }
}
