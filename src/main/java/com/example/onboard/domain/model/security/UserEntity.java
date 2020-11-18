package com.example.onboard.domain.model.security;


import com.example.onboard.constant.UserStatus;
import com.example.onboard.domain.model.BaseModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "user_name"
        }),
        @UniqueConstraint(columnNames = {
                "email_address"
        })
})
public class UserEntity extends BaseModel {

    @NotBlank
    @Size(max = 15)
    @Column(name = "user_name")
    private String userName;

    private String password;
    @NotBlank
    @Size(max = 40)
    @Email
    @Column(name = "email_address")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private int age;

    @Column(name = "retry_count")
    private Integer retryCount;

    @Column(name = "is_locked")
    private Byte isLocked;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private UserStatus status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


}
