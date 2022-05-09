package com.example.demo.utils;

import com.example.demo.model.Zone;
import com.example.demo.model.dto.ZoneDTO;

public class ZoneMapper {

    public ZoneDTO convertZoneToDTO(Zone zone){
        return ZoneDTO.builder()
                .name(zone.getName())
                .restaurants(zone.getRestaurants())
                .build();
    }

    public Zone convertDTOtoZone(ZoneDTO zoneDTO){
        return Zone.builder()
                .name((zoneDTO.getName()))
                .restaurants(zoneDTO.getRestaurants())
                .build();
    }

}
