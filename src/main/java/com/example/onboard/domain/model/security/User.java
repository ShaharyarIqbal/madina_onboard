package com.example.onboard.domain.model.security;

import com.example.onboard.constant.UserStatus;
import com.example.onboard.domain.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user",uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "user_name"
        })}
        )
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseModel {
    @Column(name = "user_name")
    private String userName;

    private String password;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private UserStatus status;



}
