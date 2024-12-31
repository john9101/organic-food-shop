package com.spring.project.organicfoodshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.project.organicfoodshop.util.constant.EmploymentStatusEnum;
import com.spring.project.organicfoodshop.util.constant.GenderEnum;
import com.spring.project.organicfoodshop.util.constant.RoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends AbstractAuditingEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    private String fullName;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    private String email;

    private String phone;

    private LocalDate dob;

    private LocalDate hireDate;

    private Double salary;

    @Enumerated(EnumType.STRING)
    private EmploymentStatusEnum employmentStatus;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean activated;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean blocked;

    private String resetPasswordToken;

    private String activationToken;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Order> orders;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Address> addresses = new HashSet<>();

    @PrePersist
    protected void onPrePersistUser() {
        this.activated = false;
        this.blocked = false;
        this.resetPasswordToken = null;
        this.activationToken = UUID.randomUUID().toString();

        if (this.roles.stream().anyMatch(role -> role.getName().equals(RoleEnum.EMPLOYEE))){
            this.employmentStatus = EmploymentStatusEnum.ACTIVE;
        }

//        this.setCreatedBy(this.email);
    }
}

