package com.bloomless.core.shopManagement.rest.resources;

import lombok.Data;

@Data
public class UpgradeItemResource extends ItemResource {
    int givenXP;
    private Long id;
}
