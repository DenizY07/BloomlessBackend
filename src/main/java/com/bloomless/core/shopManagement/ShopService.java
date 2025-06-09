package com.bloomless.core.shopManagement;

import com.bloomless.core.accountManagement.database.AccountEntity;
import com.bloomless.core.accountManagement.manager.AccountManager;
import com.bloomless.core.shopManagement.database.ShopItemEntity;
import com.bloomless.core.shopManagement.exceptions.NotEnoughCurrency;
import com.bloomless.core.shopManagement.manager.ShopManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

    @Autowired
    ShopManager shopManager;

    @Autowired
    AccountManager accountManager;

    public ShopItemEntity getRandomShopItem(Long accountId){
        AccountEntity account = accountManager.findEntityInRepositoryById(accountId);

        if (account.getCurrency() < 300) {
            throw new NotEnoughCurrency("Nicht genug Currency für einen Pull");
        }

        account.setCurrency(account.getCurrency() - 300);
        accountManager.saveToRepository(account);

        return shopManager.returnRandom();
    }

}
