package com.example.onboard.domain.model;

import com.example.onboard.domain.model.security.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ClientSetting extends BaseModel {
     private  String latitude;
     private  String longitude;

     @OneToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "currency_id",referencedColumnName = "id")
     private  Currency currency;

     @OneToOne(cascade = CascadeType.ALL)
     @JoinColumn(name="client_id",referencedColumnName = "id")
     private Client client;


}
