package com.bloomless.core.shopManagement.rest.dtos;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY, // Typ wird als JSON-Property eingebaut
        property = "type" // der Name im JSON
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DMGItemDto.class, name = "dmg"),
        @JsonSubTypes.Type(value = HPItemDto.class, name = "hp"),
        @JsonSubTypes.Type(value = UpgradeItemDto.class, name = "upgrade")
})
@Data
public class ItemDto {
    private Long id;
    private String name;
    private String type;
    private String rarity;
}
