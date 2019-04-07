package com.crud.orders.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;

import java.util.Date;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_crud")
@EntityListeners(AuditingEntityListener.class)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "date")
    @UpdateTimestamp
    private Date date;

    @Column(name = "department")
    private String department;

    @Column(name = "product")
    private String product;

    @Column(name = "supplier")
    private String supplier;

    @Column(name = "status")
    private String status;


}
