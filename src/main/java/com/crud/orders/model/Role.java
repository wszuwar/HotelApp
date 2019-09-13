package com.crud.orders.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "role")
    private String role;


    public Role(String role) {
        this.role = role;
    }

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<AppUser> users;

    public String getRole() {
        return role;
    }

}
