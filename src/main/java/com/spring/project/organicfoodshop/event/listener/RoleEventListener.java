package com.spring.project.organicfoodshop.event.listener;

import com.spring.project.organicfoodshop.domain.Role;
import com.spring.project.organicfoodshop.domain.User;
import com.spring.project.organicfoodshop.event.RegisterEvent;
import com.spring.project.organicfoodshop.service.RoleService;
import com.spring.project.organicfoodshop.util.constant.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class RoleEventListener {
    private final RoleService roleService;

    @EventListener
    public void handleInitializeRoleForRegisterEvent(RegisterEvent event) {
        User user = event.getUser();
        Role role = roleService.getRoleByName(RoleEnum.CUSTOMER);
        user.setRoles(Set.of(role));
    }
}
