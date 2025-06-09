package com.bloomless.core.shopManagement.manager;

import com.bloomless.core.shopManagement.database.DMGItemEntity;
import com.bloomless.core.shopManagement.database.HPItemEntity;
import com.bloomless.core.shopManagement.database.ShopItemEntity;
import com.bloomless.core.shopManagement.database.repositories.ShopItemRepository;
import com.bloomless.core.shopManagement.exceptions.ItemNotFound;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class ShopManager {
    @Autowired
    ShopItemRepository shopItemRepository;

    private final Random random = new Random();

    @PostConstruct
    public void init() {
        if (shopItemRepository.count() == 0) {
            shopItemRepository.saveAll(createAllUniqueItems());
        }
    }

    public ShopItemEntity returnRandom() {
        List<ShopItemEntity> allItems = shopItemRepository.findAll();

        List<ShopItemEntity> commons = new ArrayList<>();
        List<ShopItemEntity> uncommons = new ArrayList<>();
        List<ShopItemEntity> rares = new ArrayList<>();
        List<ShopItemEntity> epics = new ArrayList<>();
        List<ShopItemEntity> legendaries = new ArrayList<>();

        for (ShopItemEntity item : allItems) {
            switch (item.getRarity()) {
                case COMMON -> commons.add(item);
                case UNCOMMON -> uncommons.add(item);
                case RARE -> rares.add(item);
                case EPIC -> epics.add(item);
                case LEGENDARY -> legendaries.add(item);
            }
        }

        double rand = Math.random() * 100;
        List<ShopItemEntity> pool;
        if (rand < 40) {
            pool = commons;
        } else if (rand < 70) {
            pool = uncommons;
        } else if (rand < 90) {
            pool = rares;
        } else if (rand < 98) {
            pool = epics;
        } else {
            pool = legendaries;
        }

        if (pool.isEmpty()) throw new ItemNotFound("Keine Items dieser Rarity vorhanden!");
        return pool.get(random.nextInt(pool.size()));
    }

    public List<ShopItemEntity> createAllUniqueItems() {
        List<ShopItemEntity> items = new ArrayList<>();

        // COMMON
        HPItemEntity leatherHelmet = new HPItemEntity();
        leatherHelmet.setName("Leather Helmet");
        leatherHelmet.setRarity(com.bloomless.core.shopManagement.data.items.Rarity.COMMON);
        leatherHelmet.setLevel(1);
        leatherHelmet.setXp(0);
        leatherHelmet.setPassive("Small Regeneration");
        leatherHelmet.setItemHP(15);
        leatherHelmet.setItemDEF(3);
        leatherHelmet.setItemRegen(1.0);
        items.add(leatherHelmet);

        DMGItemEntity woodenSword = new DMGItemEntity();
        woodenSword.setName("Wooden Sword");
        woodenSword.setRarity(com.bloomless.core.shopManagement.data.items.Rarity.COMMON);
        woodenSword.setLevel(1);
        woodenSword.setXp(0);
        woodenSword.setPassive("Quick Strike");
        woodenSword.setItemDMG(10);
        woodenSword.setItemCritRate(2.0);
        woodenSword.setItemCritDMG(5);
        items.add(woodenSword);

        // UNCOMMON
        HPItemEntity chainMail = new HPItemEntity();
        chainMail.setName("Chainmail");
        chainMail.setRarity(com.bloomless.core.shopManagement.data.items.Rarity.UNCOMMON);
        chainMail.setLevel(2);
        chainMail.setXp(0);
        chainMail.setPassive("Strong Regeneration");
        chainMail.setItemHP(30);
        chainMail.setItemDEF(7);
        chainMail.setItemRegen(2.0);
        items.add(chainMail);

        DMGItemEntity ironAxe = new DMGItemEntity();
        ironAxe.setName("Iron Axe");
        ironAxe.setRarity(com.bloomless.core.shopManagement.data.items.Rarity.UNCOMMON);
        ironAxe.setLevel(2);
        ironAxe.setXp(0);
        ironAxe.setPassive("Pierce");
        ironAxe.setItemDMG(22);
        ironAxe.setItemCritRate(4.0);
        ironAxe.setItemCritDMG(12);
        items.add(ironAxe);

        // RARE
        HPItemEntity silverChestplate = new HPItemEntity();
        silverChestplate.setName("Silver Chestplate");
        silverChestplate.setRarity(com.bloomless.core.shopManagement.data.items.Rarity.RARE);
        silverChestplate.setLevel(3);
        silverChestplate.setXp(0);
        silverChestplate.setPassive("Block");
        silverChestplate.setItemHP(55);
        silverChestplate.setItemDEF(15);
        silverChestplate.setItemRegen(3.0);
        items.add(silverChestplate);

        DMGItemEntity silverDagger = new DMGItemEntity();
        silverDagger.setName("Silver Dagger");
        silverDagger.setRarity(com.bloomless.core.shopManagement.data.items.Rarity.RARE);
        silverDagger.setLevel(3);
        silverDagger.setXp(0);
        silverDagger.setPassive("Poison Blade");
        silverDagger.setItemDMG(35);
        silverDagger.setItemCritRate(7.0);
        silverDagger.setItemCritDMG(20);
        items.add(silverDagger);

        // EPIC
        HPItemEntity goldenShield = new HPItemEntity();
        goldenShield.setName("Golden Shield");
        goldenShield.setRarity(com.bloomless.core.shopManagement.data.items.Rarity.EPIC);
        goldenShield.setLevel(4);
        goldenShield.setXp(0);
        goldenShield.setPassive("Reflection");
        goldenShield.setItemHP(90);
        goldenShield.setItemDEF(25);
        goldenShield.setItemRegen(4.5);
        items.add(goldenShield);

        DMGItemEntity dragonSword = new DMGItemEntity();
        dragonSword.setName("Dragon Sword");
        dragonSword.setRarity(com.bloomless.core.shopManagement.data.items.Rarity.EPIC);
        dragonSword.setLevel(4);
        dragonSword.setXp(0);
        dragonSword.setPassive("Flame Slash");
        dragonSword.setItemDMG(60);
        dragonSword.setItemCritRate(12.0);
        dragonSword.setItemCritDMG(35);
        items.add(dragonSword);

        // LEGENDARY
        HPItemEntity phoenixArmor = new HPItemEntity();
        phoenixArmor.setName("Phoenix Armor");
        phoenixArmor.setRarity(com.bloomless.core.shopManagement.data.items.Rarity.LEGENDARY);
        phoenixArmor.setLevel(5);
        phoenixArmor.setXp(0);
        phoenixArmor.setPassive("Rebirth");
        phoenixArmor.setItemHP(150);
        phoenixArmor.setItemDEF(40);
        phoenixArmor.setItemRegen(7.0);
        items.add(phoenixArmor);

        DMGItemEntity excalibur = new DMGItemEntity();
        excalibur.setName("Excalibur");
        excalibur.setRarity(com.bloomless.core.shopManagement.data.items.Rarity.LEGENDARY);
        excalibur.setLevel(5);
        excalibur.setXp(0);
        excalibur.setPassive("Holy Strike");
        excalibur.setItemDMG(120);
        excalibur.setItemCritRate(20.0);
        excalibur.setItemCritDMG(60);
        items.add(excalibur);

        shopItemRepository.saveAll(items);
        return items;
    }

}
