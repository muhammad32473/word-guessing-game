package com.annas.fullstack.practice;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@jakarta.persistence.Entity
@Table(name = "test")
public class Users {

    @Id
    private Long id;
    private String name;
    private String address;
}
