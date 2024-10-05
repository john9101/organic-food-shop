package com.spring.project.organicfoodshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.project.organicfoodshop.util.constant.GenderEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_user_id", columnList = "user_id"),
        @Index(name = "idx_role_id", columnList = "role_id")
})
public class User extends AbstractAuditingEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String username;

    @JsonIgnore
    private String password;

    private String firstName;

    private String lastName;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    private String email;

    private String phone;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    private Boolean activated;

    @JsonIgnore
    private String activationKey;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean blocked;

    private String avatar;
}

