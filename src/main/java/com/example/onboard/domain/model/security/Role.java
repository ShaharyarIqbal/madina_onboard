package com.example.onboard.domain.model.security;

import com.example.onboard.constant.RoleName;
import com.example.onboard.domain.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseModel {

    private static final long serialVersionUID = 7508721226576356803L;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 60)
    private RoleName name;

    @Column(name = "description")
    private String description;

}
