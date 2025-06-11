package com.bloomless.core.shopManagement.restController;



import com.bloomless.core.accountManagement.AccountService;
import com.bloomless.core.accountManagement.rest.resources.AccountResource;
import com.bloomless.core.shopManagement.ShopService;
import com.bloomless.core.shopManagement.database.ShopItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ShopRestController {
    @Autowired
    private ShopService shopService;

    @Autowired
    private AccountService accountService;

    /*@GetMapping(value = "bloomless/shop/item/{accountId}")
    public ItemEntity getRandomShopItem(@PathVariable Long accountId) {
        return shopService.getRandomShopItem(accountId);
    }*/

    @PostMapping("bloomless/admin/init-shop")
    public String initShop() {
        shopService.loadAllItems();
        return "Shop initialized.";
    }

    @PostMapping("bloomless/shop/pull")
    public ResponseEntity<AccountResource> pullRandomItem(@RequestParam Long accountId) {
        ShopItemEntity item = shopService.getRandomShopItem(accountId);
        accountService.addItemToInventory(accountId, item);
        AccountResource updatedAccount = accountService.getAccountResourceById(accountId);
        return ResponseEntity.ok(updatedAccount);
    }

}
