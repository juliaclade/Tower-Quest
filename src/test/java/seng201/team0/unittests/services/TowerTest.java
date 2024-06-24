package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.Tower;
import seng201.team0.models.Upgrade;
import seng201.team0.models.exceptions.ResourcesDontMatchException;

import java.util.function.DoubleToIntFunction;

import static java.lang.Math.rint;
import static java.lang.Math.round;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TowerTest {

    @SuppressWarnings("checkstyle:JavadocVariable")
    private Tower tower;

    @SuppressWarnings("checkstyle:DesignForExtension")
    @BeforeEach
    public void setUp() {
        tower = new Tower(1);
    }

    /**
     * Tests upgrading towers if resources match. Tests that stats go up and
     * XP remains the same after applying one upgrade and that they increase
     * after applying two upgrades
     * @throws ResourcesDontMatchException
     */
    @Test
    public void testApplyUpgradeSuccess() {
        try {
            tower.setResourceType("Wood");
            Upgrade upgrade = new Upgrade(1);
            upgrade.setResourceType("Wood");
            int initialReloadSpeed = tower.getReloadSpeed();
            int initialResourceAmount = tower.getResourceAmount();
            tower.applyUpgrade(upgrade);

            assertEquals(initialReloadSpeed
                     + upgrade.getReloadSpeed(), tower.getReloadSpeed());
            assertEquals(initialResourceAmount
                     + upgrade.getResourceAmount(), tower.getResourceAmount());
            assertEquals(1, tower.getLevelXP());
            assertEquals(1, tower.getUpgradeTracker().size());

            initialReloadSpeed = tower.getReloadSpeed();
            initialResourceAmount = tower.getResourceAmount();

            tower.applyUpgrade(upgrade);
            assertEquals(2, tower.getLevelXP());
            assertEquals(initialReloadSpeed + 2
                      + upgrade.getReloadSpeed(),
                         tower.getReloadSpeed());
            assertEquals(initialResourceAmount + 2
                      + upgrade.getResourceAmount(),
                         tower.getResourceAmount());
            assertEquals(0, tower.getUpgradeTracker().size());

        } catch (ResourcesDontMatchException e) {
            System.out.println(e.getMessage());
        }
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    @Test
    public void toStringTest() {
        final int level = 5;
        final int resAmount = 100;
        final int speed = 3;
        final int cost = 50;
        tower.setLevelXP(level);
        tower.setResourceAmount(resAmount);
        tower.setReloadSpeed(speed);
        tower.setBuyingCost(cost);
        String expectedString = "XP: 5\nLoad: 100\nSpeed: 3\nSell Cost: $40";
        String actualString = tower.toString("sell");
        assertEquals(expectedString, actualString);

        expectedString = "Load: 100\nSpeed: 3\nCost: $50";
        actualString = tower.toString("Buy");
        assertEquals(expectedString, actualString);

        expectedString = "XP: 5\nLoad: 100\nSpeed: 3\n";
        actualString = tower.toString("swap");
        assertEquals(expectedString, actualString);

    }
    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    @Test
    public void testApplyUpgradeInvalidResourceType() {
        Upgrade upgrade = new Upgrade(1);
        tower.setResourceType("Gold");
        upgrade.setResourceType("Wood");

        assertThrows(ResourcesDontMatchException.class, () -> {
            tower.applyUpgrade(upgrade);
        });
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    @Test
    public void testTowerImagePath() {

        tower.setResourceType("Wood");
        String imagePath = Tower.towerImagePath(tower);
        assertEquals("images/woodtower.png", imagePath);

        tower.setResourceType("Gold");
        imagePath = Tower.towerImagePath(tower);
        assertEquals("images/goldtower.png", imagePath);

        tower.setResourceType("Coal");
        imagePath = Tower.towerImagePath(tower);
        assertEquals("images/coaltower.png", imagePath);


    }
    @Test
    public void testTowerUpgradeApply(){
        Upgrade upgrade1 = new Upgrade(1);
        Upgrade upgrade2 = new Upgrade(1);
        tower.setResourceType("Gold");
        upgrade1.setResourceType("Gold");
        upgrade2.setResourceType("Gold");
        int towerSellPrice = tower.getSellingCost();
        int upgrade2SellPrice = upgrade2.getSellingCost();
        int upgrade1SellPrice = upgrade1.getSellingCost();
        try{
            tower.applyUpgrade(upgrade1);
            assertEquals(towerSellPrice + upgrade1SellPrice, tower.getSellingCost());
            towerSellPrice= tower.getSellingCost();
            tower.applyUpgrade(upgrade2);
        } catch (ResourcesDontMatchException e) {
            System.out.println(e.getMessage());
        }
        assertEquals((int)((towerSellPrice + upgrade2SellPrice)*1.2) ,
                tower.getSellingCost());

    }


}
