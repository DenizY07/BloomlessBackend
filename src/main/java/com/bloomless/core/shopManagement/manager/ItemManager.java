package com.bloomless.core.shopManagement.manager;


import com.bloomless.core.shopManagement.database.DMGItemEntity;
import com.bloomless.core.shopManagement.database.HPItemEntity;
import com.bloomless.core.shopManagement.database.ItemEntity;
import com.bloomless.core.shopManagement.database.UpgradeItemEntity;
import com.bloomless.core.shopManagement.database.repositories.ItemRepository;
import com.bloomless.core.shopManagement.exceptions.ItemNotFound;
import com.bloomless.core.shopManagement.exceptions.ItemTypeNotMatching;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ItemManager {
    @Autowired
    ItemRepository itemRepository;

    public ItemEntity findItemInRepositoryById(Long id){
        return itemRepository.findById(id).get();
    }

    public ItemEntity findItemInRepositoryByName(String name){
        return itemRepository.findByName(name).get();
    }

    public UpgradeItemEntity upgradeItemSave(UpgradeItemEntity item) {
        return itemRepository.save(item);
    }

    public ItemEntity itemSave(ItemEntity item) {
        return itemRepository.save(item);
    }

    public DMGItemEntity findDMGItemEntityById(Long id) {
        ItemEntity item = findItemInRepositoryById(id);
        if (item instanceof DMGItemEntity dmgItem) {
            return dmgItem;
        }
        throw new ItemTypeNotMatching("Item mit ID " + id + " ist kein DMGItemEntity!");
    }

    public DMGItemEntity findDMGItemEntityByName(String name) {
        ItemEntity item = findItemInRepositoryByName(name);
        if (item instanceof DMGItemEntity dmgItem) {
            return dmgItem;
        }
        throw new ItemTypeNotMatching("Item mit dem Namen " + name+ " ist kein DMGItemEntity!");
    }

    public HPItemEntity findHPItemEntityById(Long id) {
        ItemEntity item = findItemInRepositoryById(id);
        if (item instanceof HPItemEntity hpItem) {
            return hpItem;
        }
        throw new ItemTypeNotMatching("Item mit ID " + id + " ist kein HPItemEntity!");
    }

    public HPItemEntity findHPItemEntityByName(String name) {
        ItemEntity item = findItemInRepositoryByName(name);
        if (item instanceof HPItemEntity hpItem) {
            return hpItem;
        }
        throw new ItemTypeNotMatching("Item mit dem Namen " + name + " ist kein HPItemEntity!");
    }

}
