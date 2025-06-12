package com.bloomless.core.shopManagement.restController;



import com.bloomless.core.accountManagement.AccountService;
import com.bloomless.core.accountManagement.database.AccountEntity;
import com.bloomless.core.accountManagement.rest.resources.AccountResource;
import com.bloomless.core.shopManagement.ShopService;
import com.bloomless.core.shopManagement.database.ShopItemEntity;
import com.bloomless.core.shopManagement.mapper.ItemMapper;
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

    private ItemMapper itemMapper = new ItemMapper();

    @PostMapping("bloomless/admin/init-shop")
    public String initShop() {
        shopService.loadAllItems();
        return "Shop initialized.";
    }

    /*@PostMapping("bloomless/shop/pull")
    public ResponseEntity<AccountResource> pullRandomItem(@RequestParam Long accountId) {
        ItemEntity item = shopService.getRandomShopItem(accountId);
        accountService.addItemToInventory(accountId,Item);
        AccountResource updatedAccount = accountService.getAccountResourceById(accountId);
        return ResponseEntity.ok(updatedAccount);
    }*/

    /*ALTE VERS
    @PostMapping("bloomless/shop/pull")
    public ResponseEntity<AccountResource> pullRandomItem(@RequestParam Long accountId) {
        ShopItemEntity itemEntity = shopService.getRandomShopItem(accountId);
       // ShopItem item = itemMapper.convertShopItemEntityToShopItem(itemEntity);
        accountService.addItemToInventory(accountId, itemEntity);
        AccountResource updatedAccount = accountService.getAccountResourceById(accountId);
        return ResponseEntity.ok(updatedAccount);
    }*/
    @PostMapping("bloomless/shop/pull")
    public ResponseEntity<AccountResource> pullRandomItem(@RequestParam Long accountId) {
        ShopItemEntity itemEntity = shopService.getRandomShopItem(accountId);

        AccountEntity accountEntity = accountService.findEntityInRepositoryById(accountId);
        itemEntity.setAccount(accountEntity);

        accountService.addItemToInventory(accountId, itemEntity);
        AccountResource updatedAccount = accountService.getAccountResourceById(accountId);
        return ResponseEntity.ok(updatedAccount);
    }

}
