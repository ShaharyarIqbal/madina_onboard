package com.example.onboard.service;

        import com.example.onboard.domain.dto.ClientSettingDto;
        import com.example.onboard.domain.model.ClientSetting;

public interface ClientSettingService {

   public ClientSetting createClientSetting(ClientSettingDto clientSettingDto);

   ClientSetting clientSettingById(Long id);

   ClientSetting updateClientSetting(Long id,ClientSettingDto clientSettingDto);

    String deleteClientSetting(Long id);
}
