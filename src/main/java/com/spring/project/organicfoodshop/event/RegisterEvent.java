package com.spring.project.organicfoodshop.event;

import com.spring.project.organicfoodshop.domain.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class RegisterEvent extends ApplicationEvent {
    private final User user;

    public RegisterEvent(Object source, User user) {
        super(source);
        this.user = user;
    }
}
