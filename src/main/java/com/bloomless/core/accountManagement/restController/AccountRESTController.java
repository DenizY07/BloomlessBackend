package com.bloomless.core.accountManagement.restController;

import com.bloomless.core.accountManagement.AccountService;
import com.bloomless.core.accountManagement.rest.dtos.AccountUpdateDto;
import com.bloomless.core.accountManagement.rest.dtos.LoginDto;
import com.bloomless.core.accountManagement.rest.dtos.RegisterDto;
import com.bloomless.core.accountManagement.rest.resources.AccountResource;
import com.bloomless.core.equipmentManagement.EquipmentService;
import com.bloomless.core.equipmentManagement.manager.EquipmentManager;
import com.bloomless.core.shopManagement.rest.resources.ItemResource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AccountRESTController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private EquipmentService equipmentService;

    @PostMapping(value = "bloomless/user/login")
    public AccountResource login(@RequestBody LoginDto loginDto){
        return accountService.login(loginDto);
    }

    @PutMapping("bloomless/player/levelUpItem")
    public AccountResource levelUpItem(
            @RequestParam Long itemId,
            @RequestParam Long upgradeItemId,
            @RequestParam Long accountId) {
        accountService.levelUpItemInInventory(accountId, itemId, upgradeItemId);

        return accountService.getAccountResourceById(accountId);
    }

    @PostMapping(value ="bloomless/user/register")
    public AccountResource register(@RequestBody RegisterDto registerDto){
        return accountService.register(registerDto);
    }

    @PutMapping("bloomless/user/update")
    public AccountResource updateAccount(@RequestBody AccountUpdateDto updateDto) {
        return accountService.updateAccount(updateDto);
    }

    @PostMapping("bloomless/admin/giveTestUpgradeScroll")
    public AccountResource giveTestUpgradeScroll(@RequestParam Long accountId) {
        return accountService.giveTestUpgradeScroll(accountId);
    }

    @PostMapping("bloomless/player/giveRandomUpgradeScroll")
    public AccountResource giveRandomUpgradeScroll(@RequestParam Long accountId) {
        return accountService.giveRandomUpgradeScroll(accountId);
    }
}
