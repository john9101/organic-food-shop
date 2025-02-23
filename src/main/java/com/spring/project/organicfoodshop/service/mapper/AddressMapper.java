package com.spring.project.organicfoodshop.service.mapper;

import com.spring.project.organicfoodshop.domain.Address;
import com.spring.project.organicfoodshop.domain.response.common.account.AddedAccountAddressResponse;
import com.spring.project.organicfoodshop.domain.response.common.account.EditedAccountAddressResponse;
import com.spring.project.organicfoodshop.domain.response.common.account.GotAccountAddressDetailResponse;
import com.spring.project.organicfoodshop.domain.response.common.account.GotAccountAddressesResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Set<GotAccountAddressesResponse.Item> toGotAccountAddressItems(Set<Address> addresses);

    AddedAccountAddressResponse toAddedAccountAddressResponse(Address address);

    EditedAccountAddressResponse toEditedAccountAddressResponse(Address address);

    GotAccountAddressDetailResponse toGotAccountAddressDetailResponse(Address address);
}
