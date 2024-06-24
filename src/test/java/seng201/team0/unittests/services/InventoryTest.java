package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.Inventory;
import seng201.team0.models.Tower;
import seng201.team0.models.Upgrade;
import seng201.team0.models.exceptions.BelowMinimumTowerException;
import seng201.team0.models.exceptions.InvalidSelectionException;
import seng201.team0.models.exceptions.ResourcesDontMatchException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {
    private Inventory inventoryTest;
    private List<Tower> mainTowerList;
    private List<Tower> reserveTowerList;
    private List<Upgrade> upgradeList;

    public InventoryTest() {

        this.mainTowerList = new ArrayList<>();
        this.reserveTowerList = new ArrayList<>();
        this.upgradeList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            mainTowerList.add(new Tower(1));
        }
        for (int i = 0; i < 2; i++) {
            reserveTowerList.add(new Tower(1));
        }
        for (int i = 0; i < 2; i++) {
            upgradeList.add(new Upgrade(1));
        }
    }
    @BeforeEach
    public void setupTest() {
        inventoryTest = new Inventory(mainTowerList, reserveTowerList,
                upgradeList);
    }
    @Test
    public void testInitializeCase1() {
        Tower reserveTowerSwap = reserveTowerList.get(0);
        Tower mainTowerSwap = mainTowerList.get(0);
        try {
            inventoryTest.swap(0, 0);
            assertFalse(mainTowerList.contains(mainTowerSwap));
            assertFalse(reserveTowerList.contains(reserveTowerSwap));
            assertTrue(reserveTowerList.contains(mainTowerSwap));
            assertTrue(mainTowerList.contains(reserveTowerSwap));
            assertEquals(4, mainTowerList.size());
            assertEquals(2, reserveTowerList.size());
        } catch (BelowMinimumTowerException | InvalidSelectionException e) {
            System.out.print(e.getMessage());
        }
    }
    @Test
    public void testInitializeCase2() {
//        swap empty with one containing tower
        Tower mainTowerSwap = mainTowerList.get(0);
        try {
            inventoryTest.swap(0, 4);
            assertFalse(mainTowerList.contains(mainTowerSwap));
            assertTrue(reserveTowerList.contains(mainTowerSwap));
            assertEquals(3, mainTowerList.size());
            assertEquals(3, reserveTowerList.size());
        }  catch (BelowMinimumTowerException | InvalidSelectionException e) {
            System.out.print(e.getMessage());
        }
    }
    @Test
    public void testInitializeCase3() {
        Tower reserveTowerSwap = reserveTowerList.get(0);
        try {
            inventoryTest.swap(4, 0);
            assertFalse(reserveTowerList.contains(reserveTowerSwap));
            assertTrue(mainTowerList.contains(reserveTowerSwap));
            assertEquals(5, mainTowerList.size());
            assertEquals(1, reserveTowerList.size());
        }  catch (BelowMinimumTowerException | InvalidSelectionException e) {
            System.out.print(e.getMessage());
        }
    }
    @Test
    public void testInitializeCase4() {
        try {
            inventoryTest.swap(4, 4);
            assertEquals(4, mainTowerList.size());
            assertEquals(2, reserveTowerList.size());
        }  catch (BelowMinimumTowerException | InvalidSelectionException e) {
            System.out.print(e.getMessage());
        }
    }
    @Test
    public void testInitializeCase5() {
        Tower mainTower = mainTowerList.get(0);
        Upgrade upgradeApply = upgradeList.get(0);
        upgradeApply.setResourceType(mainTower.getResourceType());
        try {
        inventoryTest.applyUpgrade(upgradeApply, mainTower);
        } catch (ResourcesDontMatchException e) {
            System.out.println(e.getMessage());
        }
        inventoryTest.removeUpgrade(upgradeApply);
        assertEquals(1, upgradeList.size());

    }
    @Test
    public void testInitializeCase6() {
        Tower mainTower = mainTowerList.get(0);
        Upgrade upgradeApply = upgradeList.get(0);
        if (mainTower.getResourceType().equals("GOLD")) {
        upgradeApply.setResourceType(mainTower.getResourceType());
        }
        try {
            inventoryTest.applyUpgrade(upgradeApply, mainTower);
        } catch (ResourcesDontMatchException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(2, upgradeList.size());
    }
    @Test
    public void testInitializeCase7() {
        Tower onlyInMain = mainTowerList.get(0);
        mainTowerList.clear();
        mainTowerList.add(onlyInMain);
        reserveTowerList.clear();
        assertThrows(BelowMinimumTowerException.class,
                () ->{inventoryTest.swap(0,
                        0);} );
    }
    @Test
    public void testInitializeCase8() {
        assertThrows(InvalidSelectionException.class,
                () ->{inventoryTest.swap(6,
                        8);} );
    }
    @Test
    public void testInitializeCase9() {
        Tower mainTower = mainTowerList.get(0);
        Upgrade upgradeApply = upgradeList.get(0);
        upgradeApply.setResourceType("Gold");
        mainTower.setResourceType("Coal");
        assertThrows(ResourcesDontMatchException.class,
                ()-> {mainTower.applyUpgrade(upgradeApply);});
    }}
