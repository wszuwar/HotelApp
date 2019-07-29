package com.crud.orders.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "waste_crud")
@EntityListeners(AuditingEntityListener.class)
public class Waste {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "date")
    @UpdateTimestamp
    private Date date;

    @Column(name = "breakfastChefs")
    private long breakfastChefs;

    @Column(name = "breakfastWaiters")
    private long breakfastWaiters;

    @Column(name = "totalBreakfast")
    private long totalBreakfast;

    @Column(name = "lunchChefs")
    private long lunchChefs;

    @Column(name = "lunchWaiters")
    private long lunchWaiters;

    @Column(name = "lunchTotal")
    private long lunchTotal;

    @Column(name = "ktChefs")
    private long ktChefs;

    @Column(name = "ktWaiters")
    private long ktWaiters;

    @Column(name = "ktTotal")
    private long ktTotal;
}
