package seng201.team0.models;

import seng201.team0.models.exceptions.BelowMinimumTowerException;
import seng201.team0.models.exceptions.InventoryFullException;
import seng201.team0.models.exceptions.NotEnoughMoneyException;

import java.util.ArrayList;
import java.util.List;

/**
 * Store of the game which enables buying and selling of Items.
 * @author Julia Clade and Katherine Irving Seguel
 * @version 1.0, 11 Apr 2024
 */
@SuppressWarnings("checkstyle:JavadocType")
public class Shop {

    /**
     * List of upgrades for sale in the shop.
     */
    private final List<Upgrade> upgradesForSale = new ArrayList<>();

    /**
     * List of towers for sale in the shop.
     */
    private final List<Tower> towersForSale = new ArrayList<>();

    /**
     * Stores the main towers owned by the player.
     */
    private final List<Tower> mainTowersList;

    /**
     * Stores the reserve towers owned by the
     * player.
     */
    private final List<Tower> reserveTowersList;

    /**
     * Stores the upgrades owned by the player.
     */
    private final List<Upgrade> upgradeList;

    /**
     * Stores how many of each item we create to sell in the shop.
     */
    private static final int MAX_NUMBER_OF_ITEMS = 5;

    /**
     * Randomly generates towers and upgrades for sale in the shop.
     * @param currentRoundNumber Current round number.
     * @param mainTowersList     Player's main towers.
     * @param reserveTowersList  Player's reserve towers.
     * @param upgradeList        Player's upgrades.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public Shop(final int currentRoundNumber, final List<Tower> mainTowersList,
                final List<Tower> reserveTowersList,
                final List<Upgrade> upgradeList) {
        this.mainTowersList = mainTowersList;
        this.reserveTowersList = reserveTowersList;
        this.upgradeList = upgradeList;
        for (int i = 0; i < MAX_NUMBER_OF_ITEMS; i++) {
            towersForSale.add(new Tower(currentRoundNumber));
            upgradesForSale.add(new Upgrade(currentRoundNumber));
        }
    }

    /**
     * Returns the towers for sale.
     * @return towersForSale List of towers for sale in the shop.
     */
    public List<Tower> getTowersForSale() {
        return towersForSale;
    }

    /**
     * Returns upgrades for sale in the shop.
     * @return upgradesForSale List of upgrades for sale in the shop.
     */
    public List<Upgrade> getUpgradesForSale() {
        return upgradesForSale;
    }

    /** Adds the tower to the player's inventory and returns the player's new
     * balance if they have enough money to purchase the item and enough
     * space in the inventory. Otherwise, exceptions are thrown.
     * @param tower       Tower to be purchased.
     * @param playerMoney The player's money.
     * @return playerMoney The player's money.
     * @throws NotEnoughMoneyException Player doesn't have enough money
     *                                 to purchase the tower.
     * @throws InventoryFullException  Inventory is full when attempting
     *                                 to buy a tower.
     */
    @SuppressWarnings("checkstyle:FinalParameters")
    public int buyTower(final Tower tower, int playerMoney)
            throws InventoryFullException, NotEnoughMoneyException {

        if ((mainTowersList.size() < MAX_NUMBER_OF_ITEMS
                || reserveTowersList.size() < MAX_NUMBER_OF_ITEMS)
                && playerMoney - tower.getBuyingCost() >= 0) {
            if (mainTowersList.size() < MAX_NUMBER_OF_ITEMS) {
                mainTowersList.add(tower);
            } else {
                reserveTowersList.add(tower);
            }
            playerMoney -= tower.getBuyingCost();
        } else if (mainTowersList.size() == MAX_NUMBER_OF_ITEMS
                && reserveTowersList.size() == MAX_NUMBER_OF_ITEMS) {
            throw new InventoryFullException();
        } else if (playerMoney - tower.getBuyingCost() < 0) {
            throw new NotEnoughMoneyException();
        }
        return playerMoney;
    }

    /**
     * Adds the upgrade to the player's inventory and returns the player's new
     * balance if they have enough money to purchase the item.
     * Otherwise, an exception is thrown.
     * @param upgrade     Upgrade to purchase
     * @param playerMoney Player's money.
     * @return playerMoney Player's money.
     * @throws NotEnoughMoneyException Player doesn't have enough money to
     *                                 purchase the upgrade.
     */
    @SuppressWarnings("checkstyle:FinalParameters")
    public int buyUpgrades(final Upgrade upgrade, int playerMoney)
            throws NotEnoughMoneyException {
        if (playerMoney - upgrade.getBuyingCost() < 0) {
            throw new NotEnoughMoneyException();
        } else {
            upgradeList.add(upgrade);
            playerMoney -= upgrade.getBuyingCost();
        }
        return playerMoney;
    }

    /**
     * A tower is sold, it gets removed from the inventory and the new
     * balance is returned.
     * @param tower       Tower to sell.
     * @param playerMoney Player's money.
     * @param towerListSold Keeps track of sold towers.
     * @return newPlayersBalance Player's new balance.
     * @throws BelowMinimumTowerException Player tries to sell the last tower
     * they own.
     */
    public int sellTower(final Tower tower, final int playerMoney,
         final List<Tower> towerListSold) throws BelowMinimumTowerException {
        if (getTowersList().size() - towerListSold.size() == 1) {
        throw new BelowMinimumTowerException(
                "You can't have less than one tower");
        } else {
        return playerMoney + tower.getSellingCost();
        }
    }

    /**
     * An upgrade is sold, it gets removed from the inventory and the new
     * balance is returned.
     * @param upgrade     Upgrade to sell.
     * @param playerMoney Player's money.
     * @return newPlayersBalance PLayer's new balance.
     */
    @SuppressWarnings("checkstyle:FinalParameters")
    public int sellUpgrade(final Upgrade upgrade, int playerMoney) {
        return playerMoney + upgrade.getSellingCost();
    }

    /**
     * Returns a list of all the player's towers.
     * @return allTowerList List of all the player's towers.
     */
    public List<Tower> getTowersList() {
        List<Tower> allTowerList = new ArrayList<>();
        allTowerList.addAll(mainTowersList);
        allTowerList.addAll(reserveTowersList);
        return allTowerList;
    }

    /**
     * Returns a list of all the player's upgrades.
     * @return upgradeList List of all the player's upgrades.
     */
    public List<Upgrade> getUpgradeList() {
        return upgradeList;
    }

    /**
     * Removes a tower from the player's inventory.
     * @param towerList Player's list of towers.
     */
    public void removeTowerSold(final List<Tower> towerList) {
        for (Tower tower : towerList) {
            if (mainTowersList.contains(tower)) {
            mainTowersList.remove(tower);
        } else {
            reserveTowersList.remove(tower);
        }
        }
    }

    /**
     * Removes an upgrade from the player's inventory.
     * @param upgradeList List of the player's upgrades.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public void removeUpgradesSold(final List<Upgrade> upgradeList) {
        for (Upgrade upgrade : upgradeList) {
        this.upgradeList.remove(upgrade);
    }
    }
}


