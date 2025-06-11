package com.bloomless.core.equipmentManagement.restController;


import com.bloomless.core.accountManagement.AccountService;
import com.bloomless.core.equipmentManagement.EquipmentService;
import com.bloomless.core.equipmentManagement.rest.dto.ActorDto;
import com.bloomless.core.equipmentManagement.rest.resources.ActorResource;
import com.bloomless.core.shopManagement.rest.resources.ItemResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class EquipmentRestController {
    @Autowired
    EquipmentService equipmentService;
    @Autowired
    AccountService accountService;

    @PostMapping("bloomless/player/equipItemId")
    public ActorResource equipItemId(
            @RequestParam Long actorId,
            @RequestParam Long itemId,
            @RequestParam String slotNumber) {
        return equipmentService.equipItemWithId(actorId, itemId, slotNumber);
    }

    @PutMapping("bloomless/player/unequipItemId")
    public ActorResource unequipItemId(
            @RequestParam Long actorId,
            @RequestParam Long itemId,
            @RequestParam String slotNumber) {
        return equipmentService.unequipItemWithId(actorId, itemId, slotNumber);
    }

    /*@PutMapping("bloomless/player/levelUpItem")
    public ItemResource levelUpItem(
            @RequestParam Long itemId,
            @RequestParam Long upgradeItemId,
            @RequestParam Long accountId) {
        ItemResource leveledItem = equipmentService.levelUpItemWithUpgrade(itemId, upgradeItemId);
        accountService.removeItemFromInventory(accountId, upgradeItemId);
        return leveledItem;
    }*/

    @GetMapping("bloomless/player/all")
    public List<ActorResource> getAllPlayers() {
        return equipmentService.getAllActors();
    }

    @PostMapping("bloomless/player/equipItemName")
    public ActorResource equipItemName(
            @RequestParam Long actorId,
            @RequestParam String itemName,
            @RequestParam String slotNumber) {
        return equipmentService.equipItemWithName(actorId, itemName, slotNumber);
    }


    @PutMapping("bloomless/player/update")
    public ActorResource updatePlayer(@RequestParam ActorDto actorDto){
        return equipmentService.updatePlayer(actorDto);
    }
}
