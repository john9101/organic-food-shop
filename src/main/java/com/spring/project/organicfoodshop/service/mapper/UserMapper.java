package com.spring.project.organicfoodshop.service.mapper;

import com.spring.project.organicfoodshop.domain.User;
import com.spring.project.organicfoodshop.domain.request.auth.RegisterRequest;
import com.spring.project.organicfoodshop.domain.request.management.user.CreateUserRequest;
import com.spring.project.organicfoodshop.domain.response.auth.IntrospectedResponse;
import com.spring.project.organicfoodshop.domain.response.auth.LoggedInResponse;
import com.spring.project.organicfoodshop.domain.response.auth.RegisteredResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(RegisterRequest registerRequest);

    User toUser(CreateUserRequest createUserRequest);

    RegisteredResponse toRegisterResponse(User user);

//    LoggedInResponse.InitializedPrincipal toInitializedPrincipal(User user);

    LoggedInResponse.UserInfo toLoggedInUserInfo(User user);
    IntrospectedResponse.UserInfo toIntrospectedUserInfo(User user);
}
