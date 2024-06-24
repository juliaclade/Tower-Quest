package seng201.team0.models;

import seng201.team0.models.exceptions.BelowMinimumTowerException;
import seng201.team0.models.exceptions.InvalidSelectionException;
import seng201.team0.models.exceptions.ResourcesDontMatchException;

import java.util.List;

/**
 *  Inventory of the game. It keeps track of the player's
 *  upgrades and towers. Implements swapping of towers between lists.
 * @author Julia Clade and Katherine Irving Seguel
 * @version 1.0, 11 Apr 2024
 */
public class Inventory {

     /**
      * Stores the player's main towers.
     */
    private List<Tower> mainTowers;

    /**
     * Stores the player's reserve towers.
     */
    private List<Tower> reserveTowers;

    /**
     * Stores the player's upgrades.
     */
    private List<Upgrade> upgrades;

    /**
     * This method initialise the upgrades, reserveTowers
     * and mainTowers lists.
     * @param mainTowersList    List that holds all the player's main towers.
     * @param reserveTowersList List that holds all the player's reserve towers.
     * @param upgradeList       List that holds all the player's upgrades.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public Inventory(final List<Tower> mainTowersList,
                     final List<Tower> reserveTowersList,
                     final List<Upgrade> upgradeList) {
        this.mainTowers = mainTowersList;
        this.reserveTowers = reserveTowersList;
        this.upgrades = upgradeList;
    }

    /**
     * Swaps towers between mainTower and reserveTower lists.
     * @param indexMainTower         The index of the main tower (chosen by the
     *                               player when clicking on a button) to swap.
     * @param indexReserveTower The index of the reserve tower (chosen by the
     *                          player when clicking on a button) to swap.
     * @throws BelowMinimumTowerException If the main tower list contains
     * less than one tower.
     * @throws InvalidSelectionException If the player hasn't selected a
     * valid combination of items.
     */
    public void swap(final int indexMainTower, final int indexReserveTower)
            throws BelowMinimumTowerException, InvalidSelectionException {

        if (indexMainTower >= mainTowers.size() && indexReserveTower
                >= reserveTowers.size()) {
            throw new InvalidSelectionException("Can't swap what doesn't exist ");
        } else if (indexMainTower >= mainTowers.size()) {
            mainTowers.add(reserveTowers.get(indexReserveTower));
            reserveTowers.remove(reserveTowers.get(indexReserveTower));
        } else if (indexReserveTower >= reserveTowers.size()) {
            if (mainTowers.size() == 1) {
                throw new BelowMinimumTowerException("You main tower list "
                        + "must have at least one tower");
            }
            reserveTowers.add(mainTowers.get(indexMainTower));
            mainTowers.remove(mainTowers.get(indexMainTower));
        } else {
            Tower reserveTower = reserveTowers.get(indexReserveTower);
            Tower mainTower = mainTowers.get(indexMainTower);
            mainTowers.set(indexMainTower, reserveTower);
            reserveTowers.set(indexReserveTower, mainTower);
        }
    }

    /**
     * Returns a list of the player's main towers.
     * @return mainTowers list of the player's main towers.
     */
    public List<Tower> getMainTowers() {
        return mainTowers;
    }

    /**
     * Returns a list of the player's reserve towers.
     * @return reserveTowers List of the player's reserve towers.
     */
    public List<Tower> getReserveTowers() {
        return reserveTowers;
    }

    /**
     * Returns list of the player's upgrades.
     * @return upgrades List of upgrades.
     */
    public List<Upgrade> getUpgrades() {
        return upgrades;
    }

    /**
     * Sets the inventory's main towers.
     * @param mainTowers List of the player's main towers.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public void setMainTowers(final List<Tower> mainTowers) {
        this.mainTowers = mainTowers;
    }

    /**
     * Sets the inventory's reserve towers.
     * @param reserveTowers List of the player's reserve towers.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public void setReserveTowers(final List<Tower> reserveTowers) {
        this.reserveTowers = reserveTowers;
    }

    /**
     * Sets the inventory's upgrades.
     * @param upgrades List of the player's upgrades.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public void setUpgrades(final List<Upgrade> upgrades) {
        this.upgrades = upgrades;
    }

    /**
     * Removes an upgrade from upgrades list.
     * @param upgrade Upgrade to remove.
     */
    public void removeUpgrade(final Upgrade upgrade) {
        upgrades.remove(upgrade);
    }

    /**
     * Applies upgrades to the selected tower and then
     * deletes the upgrade from the inventory.
     * @param upgrade (Upgrade)
     * @param tower (Tower)
     * @throws ResourcesDontMatchException If tower and upgrade resources
     * don't match.
     */
    @SuppressWarnings("checkstyle:JavadocMethod")
    public void applyUpgrade(final Upgrade upgrade, final Tower tower)
            throws ResourcesDontMatchException {
        tower.applyUpgrade(upgrade);
    }
}
