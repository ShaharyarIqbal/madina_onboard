package com.example.onboard.domain.model.security;

import com.example.onboard.constant.RoleName;
import com.example.onboard.domain.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

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

    @JsonIgnoreProperties("roles")
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private Set<Client> users;


    public Role(RoleName name, String description) {
        this.name = name;
        this.description = description;
    }
}
