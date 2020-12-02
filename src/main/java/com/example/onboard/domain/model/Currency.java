package com.example.onboard.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Currency extends BaseModel {

    @Size(min = 3, max = 3,message = "size must be 3 digits")
    @Column(unique = true)
    String currency;
    Boolean isActive =true;

    @JsonIgnore
    @OneToMany(mappedBy = "currency")
    private List<ClientSetting> clientSettings;

}
