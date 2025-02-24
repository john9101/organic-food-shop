package com.spring.project.organicfoodshop.service.mapper;

import com.spring.project.organicfoodshop.domain.User;
import com.spring.project.organicfoodshop.domain.request.auth.RegisterRequest;
import com.spring.project.organicfoodshop.domain.request.management.user.AddCustomerRequest;
import com.spring.project.organicfoodshop.domain.request.management.user.AddEmployeeRequest;
import com.spring.project.organicfoodshop.domain.response.auth.IntrospectedResponse;
import com.spring.project.organicfoodshop.domain.response.auth.LoggedInResponse;
import com.spring.project.organicfoodshop.domain.response.auth.RegisteredResponse;
import com.spring.project.organicfoodshop.domain.response.common.account.EditedAccountInfoResponse;
import com.spring.project.organicfoodshop.domain.response.common.account.GotAccountAddressesResponse;
import com.spring.project.organicfoodshop.domain.response.common.account.GotAccountInfoResponse;
import com.spring.project.organicfoodshop.domain.response.management.user.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(RegisterRequest registerRequest);

    User toUser(AddCustomerRequest createCustomerRequest);

    User toUser(AddEmployeeRequest addEmployeeRequest);

    RegisteredResponse toRegisterResponse(User user);

    LoggedInResponse.Metadata toLoggedInMetadataResponse(User user);

    IntrospectedResponse.UserInfo toIntrospectedUserInfo(User user);

    AddedCustomerResponse toAddedCustomerResponse(User user);

    AddedEmployeeResponse toAddedEmployeeResponse(User user);

    EditedCustomerResponse toEditedCustomerResponse(User user);

    GotCustomerDetailResponse toGotCustomerDetailResponse(User user);

    List<GotAllCustomersResponse.Item> toAllCustomerItems(List<User> users);

    List<GotAllEmployeesResponse.Item> toAllEmployeeItems(List<User> users);

    GotAccountInfoResponse toGotAccountInfoResponse(User user);

    EditedAccountInfoResponse toEditedAccountInfoResponse(User user);
}
