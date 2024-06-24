package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import seng201.team0.models.Upgrade;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpgradeTest {

    @SuppressWarnings("checkstyle:JavadocVariable")
    private Upgrade upgrade;

    @SuppressWarnings("checkstyle:DesignForExtension")
    @BeforeEach
    public void setUp() {
        final int resourceAmount = 8;
        final int speed = 6;
        final int cost = 7;
        upgrade = new Upgrade(1);
        upgrade.setResourceAmount(resourceAmount);
        upgrade.setReloadSpeed(speed);
        upgrade.setBuyingCost(cost);
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    @Test
    public void testToStringBuy() {

        String expectedString = "Load: 8\nSpeed: 6\nCost: $7";
        String actualString = upgrade.toString("buy");
        assertEquals(expectedString, actualString);
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    @Test
    public void testToString() {

        String expectedString = "Load: 8\nSpeed: 6\n";
        String actualString = upgrade.toString("apply upgrade");
        assertEquals(expectedString, actualString);
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    @Test
    public void testUpgradeImagePathWood() {

        Upgrade woodUpgrade = new Upgrade(1);
        woodUpgrade.setResourceType("Wood");
        String expectedImagePath = "images/woodUpgrade.png";
        String actualImagePath = Upgrade.upgradeImagePath(woodUpgrade);
        assertEquals(expectedImagePath, actualImagePath);
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    @Test
    public void testUpgradeImagePathGold() {

        Upgrade goldUpgrade = new Upgrade(1);
        goldUpgrade.setResourceType("Gold");
        String expectedImagePath = "images/goldUpgrade.png";
        String actualImagePath = Upgrade.upgradeImagePath(goldUpgrade);
        assertEquals(expectedImagePath, actualImagePath);
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    @Test
    public void testUpgradeImagePathCoal() {
        Upgrade coalUpgrade = new Upgrade(1);
        coalUpgrade.setResourceType("Coal");
        String expectedImagePath = "images/coalUpgrade.png";
        String actualImagePath = Upgrade.upgradeImagePath(coalUpgrade);
        assertEquals(expectedImagePath, actualImagePath);
    }

}


