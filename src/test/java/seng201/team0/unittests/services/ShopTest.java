package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.Shop;
import seng201.team0.models.Tower;
import seng201.team0.models.Upgrade;
import seng201.team0.models.exceptions.BelowMinimumTowerException;
import seng201.team0.models.exceptions.InventoryFullException;
import seng201.team0.models.exceptions.NotEnoughMoneyException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("checkstyle:NoWhitespaceBefore")
public class ShopTest {
    private Shop shopTester;
    private List<Tower> mainTowerList;
    private List<Tower> reserveTowerList;
    private int currentRound;
    private List<Upgrade> upgradeList;

    public ShopTest() {
        this.mainTowerList = new ArrayList<>();
        this.reserveTowerList = new ArrayList<>();
        this.upgradeList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            mainTowerList.add(new Tower(1));
        }
        this.currentRound = 1;
}
@BeforeEach
public void setupTest() {
    shopTester = new Shop(currentRound, mainTowerList, reserveTowerList,
            upgradeList);
}

@Test
public void testListsFull() {
    mainTowerList = new ArrayList<>();
    reserveTowerList = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
        mainTowerList.add(new Tower(1));
        reserveTowerList.add(new Tower(1));
        }
    shopTester = new Shop(currentRound, mainTowerList, reserveTowerList,
                          upgradeList);
    final int money = 100;
    assertThrows(InventoryFullException.class, () -> {
        shopTester.buyTower(new Tower(1), money);
    });
}

@Test
public void buyingTower() {
    reserveTowerList = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
        reserveTowerList.add(new Tower(1));
    }
    shopTester = new Shop(currentRound, mainTowerList, reserveTowerList,
                          upgradeList);
        assertEquals(8, shopTester.getTowersList().size());
        try {
            shopTester.buyTower(new Tower(1), 1000);
            assertEquals(9, shopTester.getTowersList().size());
        } catch (InventoryFullException | NotEnoughMoneyException e) {
            System.out.println(e.getMessage());
    }
}

@Test
public void testInitializeCase1() {
    assertEquals(5, shopTester.getTowersForSale().size());
    assertEquals(5, shopTester.getUpgradesForSale().size());
    try {
        shopTester.buyTower(shopTester.getTowersForSale().get(1), 0);

    } catch (NotEnoughMoneyException | InventoryFullException e) {
        System.out.println(e.getMessage());
    }
    assertEquals(3, shopTester.getTowersList().size());
}
    @Test
    public void testInitializeCase2() {

        try {

            int money =
                    shopTester.buyTower(shopTester.getTowersForSale().get(1),
                    1000);
            assertEquals(1000 - shopTester.getTowersForSale()
                            .get(1).getBuyingCost(), money);
            assertEquals(4, mainTowerList.size()
                    + reserveTowerList.size());
        } catch (NotEnoughMoneyException | InventoryFullException e) {
            System.out.println(e.getMessage());
        }


    }
    @Test
    public void testInitializeCase3() {
        try {

            int money =
                    shopTester.buyUpgrades(shopTester
                                    .getUpgradesForSale().get(1),
                    1000);
            assertEquals(1000
                    - shopTester.getUpgradesForSale().get(1).getBuyingCost(),
                    money);
            assertEquals(1, shopTester.getUpgradeList().size());
        } catch (NotEnoughMoneyException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testInitializeCase4() {
        int money = 5;
        Upgrade upgrade =shopTester.getUpgradesForSale().get(0);
        upgrade.setBuyingCost(12);
        assertThrows(NotEnoughMoneyException.class, () -> {
            shopTester.buyUpgrades(upgrade, money);
        });
        assertEquals(money ,5);
    }
    @Test
    public void testInitializeCase5() {
        int money = 20;
        try {
            for (int i = 0; i < 5; i++) {
                money -= shopTester.buyTower(shopTester
                                .getTowersForSale().get(i), money);
            }
        } catch (NotEnoughMoneyException | InventoryFullException e) {
            assertEquals("Sorry, you don't have enough money",
                    e.getMessage());
        }
        assertTrue(money >= 0);
    }
    @Test
    public void testInitializeCase6() {
        try {
            int money = 1000;
            for (int i = 0; i < 5; i++) {
                money =
                        shopTester.buyTower(shopTester.getTowersForSale()
                                        .get(i), money);
            }
        } catch (NotEnoughMoneyException | InventoryFullException e) {
            assertEquals("Sorry, you don't have enough money",
                    e.getMessage());
        }
        assertEquals(8, mainTowerList.size()
                + reserveTowerList.size());
        assertEquals(3, reserveTowerList.size());
        assertEquals(5, mainTowerList.size());
    }
    @Test
    public void testInitializeCase7() {
        try {
            int money = 1000;
            for (int i = 0; i < 5; i++) {
                money = shopTester.buyUpgrades(shopTester
                                .getUpgradesForSale().get(i), money);
            }
        } catch (NotEnoughMoneyException e) {
            assertEquals("Sorry, you don't have enough money",
                    e.getMessage());
        }
        assertEquals(5, upgradeList.size());
    }
    @Test
    public void testInitializeCase8() {
        try {
            int money = 1000;
            for (int i = 0; i < 5; i++) {
                money = shopTester.buyUpgrades(
                        shopTester.getUpgradesForSale().get(i), money);
            }
            int newMoneyAfterBuying = money;
            int totalMoneySoldUpgrade = 0;
            List<Upgrade> itemsSold = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                totalMoneySoldUpgrade += upgradeList.get(i).getSellingCost();
                newMoneyAfterBuying =
                        shopTester.sellUpgrade(upgradeList.get(i),
                                newMoneyAfterBuying);
                itemsSold.add(upgradeList.get(i));
            }
            shopTester.removeUpgradesSold(itemsSold);

            assertEquals(money + totalMoneySoldUpgrade,
                    newMoneyAfterBuying);
        } catch (NotEnoughMoneyException e) {
            assertEquals("Sorry, you don't have enough money",
                    e.getMessage());
        }
        assertEquals(0, upgradeList.size());
    }
    @Test
    public void testInitializerCase9() {
        try {
            int money = 1000;
            int totalMoneySoldTower = 0;
            List<Tower> itemsSold = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                money = shopTester.buyTower(
                        shopTester.getTowersForSale().get(i),
                        money);
            }
            int newMoneyAfterBuying = money;
            for (Tower tower : reserveTowerList) {
                totalMoneySoldTower += tower.getSellingCost();
                newMoneyAfterBuying =
                        shopTester.sellTower(tower,
                                newMoneyAfterBuying, itemsSold);
                itemsSold.add(tower);
            }
            shopTester.removeTowerSold(itemsSold);
            assertEquals(money + totalMoneySoldTower,
                    newMoneyAfterBuying);
            assertEquals(0, reserveTowerList.size());
        } catch (BelowMinimumTowerException | InventoryFullException
                 | NotEnoughMoneyException e) {
            assertEquals("Your can't have less than one tower",
                    e.getMessage());
        }
        assertEquals(5, mainTowerList.size()
                + reserveTowerList.size());
        assertEquals(5, mainTowerList.size());
    }
    @Test
    public void testInitializeCase10() {
        int newMoneyAfterBuying = 0;
        List<Tower> itemsSold = new ArrayList<>();
        try {
            for (Tower tower: mainTowerList) {
                newMoneyAfterBuying =
                        shopTester.sellTower(tower,
                                newMoneyAfterBuying, itemsSold);
                itemsSold.add(tower);
            }
        } catch (BelowMinimumTowerException e) {
            assertEquals("You can't have less than one tower",
                    e.getMessage());
        }
        shopTester.removeTowerSold(itemsSold);

        assertEquals(1, mainTowerList.size());
    }
    @Test
    public void testInitializeCase11() {
        int money = 15;
        Tower tower =shopTester.getTowersForSale().get(0);
        assertThrows(NotEnoughMoneyException.class, () -> {
            shopTester.buyTower(tower, money);
        });
        assertEquals(money ,15);
    }
}
