package com.spring.project.organicfoodshop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "events")
public class Event extends AbstractAuditingEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProductEvent> productEvents;

    private Instant startAt;

    private Instant endedAt;

    private Boolean expired;
}
