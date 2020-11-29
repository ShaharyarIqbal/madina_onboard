package com.example.onboard.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Currency extends BaseModel {

    @Size(min = 1, max = 5)
    @Column(unique = true)
    String currency;
    Boolean isActive;

    @OneToOne (mappedBy = "currency")
    private ClientSetting clientSetting;
}
