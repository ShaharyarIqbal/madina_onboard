package com.example.onboard.domain.model.security;


import com.example.onboard.constant.UserStatus;
import com.example.onboard.domain.model.BaseModel;
import com.example.onboard.domain.model.ClientSetting;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "user_name"
        })

})
public class Client extends BaseModel {

    @NotBlank
    @Size(max = 15)
    @Column(name = "user_name")
    private String userName;

    private String password;

//    @NotBlank
//    @Size(max = 40)
//    @Email
//    @Column(name = "email_address")
//    private String email;

    @NotBlank
    @Column(name = "masjid_name")
    private String masjidName;

    @NotBlank
    @Column (name = "street")
    private  String street;

    @NotBlank
    @Column (name = "city")
    private String city;

    @NotBlank
    @Column (name = "zip")
    private String zip;

    @NotBlank
    @Column (name = "state")
    private String state;

    @NotBlank
    @Column (name = "country")
    private String country;

    @NotBlank
    @Column (name = "website")
    private String website;

    @NotBlank
    @Column (name = "contact_number")
    private String contactNumber;

    @NotBlank
    @Column (name = "time_zone")
    private String timeZone;




    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private UserStatus status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToOne (mappedBy = "currency")
    private ClientSetting clientSetting;

}
