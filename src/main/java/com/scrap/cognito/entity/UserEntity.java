package com.scrap.cognito.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * UserEntity.
 *
 * @author Luis Alonso Ballena Garcia
 */
@Table(name = "user")
@Entity
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer id;
    @Column(name = "vc_name")
    private String name;
    @Column(name = "vc_lastname")
    private String lastName;
    @Column(name = "vc_email")
    private String email;

}
