package com.spring.project.organicfoodshop.service;

import com.spring.project.organicfoodshop.domain.Address;
import com.spring.project.organicfoodshop.repository.AddressRepository;
import com.spring.project.organicfoodshop.util.FormatterUtil;
import com.spring.project.organicfoodshop.util.constant.ModuleEnum;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    public Address handleSaveAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(FormatterUtil.formateExistExceptionMessage("id", id, ModuleEnum.ADDRESS)));
    }

    public void deleteAddressById(Long id) {
        addressRepository.deleteById(id);
    }
}
