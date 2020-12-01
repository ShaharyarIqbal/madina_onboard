package com.example.onboard.domain.model;

import com.example.onboard.domain.model.security.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ClientSetting extends BaseModel {
     private  String latitude;
     private  String longitude;
     private String juristicMethod;
     private String calculationMethod;


     @ManyToOne
     @JoinColumn(name = "currency_id")
     private  Currency currency;

     @JsonIgnoreProperties("clientSetting")
     @OneToOne(mappedBy = "clientSetting")
     private Client client;


}
