package com.spring.project.organicfoodshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.project.organicfoodshop.repository.CartRepository;
import com.spring.project.organicfoodshop.util.RandomUtil;
import com.spring.project.organicfoodshop.util.constant.GenderTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
//@Table(name = "users", indexes = {
//        @Index(name = "idx_user_id", columnList = "user_id"),
//        @Index(name = "idx_role_id", columnList = "role_id")
//})
@Table(name = "users")
public class User extends AbstractAuditingEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String username;

    @JsonIgnore
    private String password;

    private String fullName;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private GenderTypeEnum gender;

    private String email;

    private String phone;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean activated;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean blocked;

    private String resetPasswordToken;

    private String activationToken;

    private String avatar;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    @PrePersist
    protected void onPrePersistUser() {
        this.activated = false;
        this.blocked = false;
        this.resetPasswordToken = null;
        this.activationToken = UUID.randomUUID().toString();
        this.setCreatedBy(this.email);
    }
}

