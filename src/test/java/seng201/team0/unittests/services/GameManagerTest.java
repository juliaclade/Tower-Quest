package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.*;
import seng201.team0.models.exceptions.BelowMinimumTowerException;
import seng201.team0.services.GameManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameManagerTest {
    private GameManager gameManager;
    private List<Tower> mainTowerList;
    private int currentRound;

    public GameManagerTest() {
        this.mainTowerList = new ArrayList<>();
        this.currentRound = 2;
        for (int i = 0; i < 3; i++) {
            mainTowerList.add(new Tower(currentRound));
        }
    }
    @BeforeEach
    public void setupTest() {
        this.gameManager = new GameManager();
        gameManager.setMainTowerList(mainTowerList);
        gameManager.setCurrentRoundNumber(currentRound);
        gameManager.generateRoundOptions();
        gameManager.setPlayersName("Player1");
    }
    @Test
    public void checkValidateSelectedTowersSize() {
        List<Tower> selectedTowers = new ArrayList<Tower>();
        int necessaryListSize = 3;
        selectedTowers.add(new Tower(1));
        selectedTowers.add(new Tower(1));
        boolean valid = false;
        try {
            valid =
                    gameManager
            .validateSelectedTowerListSize(selectedTowers, necessaryListSize);
        } catch (BelowMinimumTowerException e) {
            System.out.println(e.getMessage());
        } finally {
            assertFalse(valid);
        }
        selectedTowers.add(new Tower(1));
        valid = false;
        try {
            valid =
                    gameManager
            .validateSelectedTowerListSize(selectedTowers, necessaryListSize);
        } catch (BelowMinimumTowerException e) {
            System.out.println(e.getMessage());
        } finally {
            assertTrue(valid);
        }
    }

    @Test
    public void checkResourceStartingTower() {
        List<Tower> startingTowers = gameManager.getStartingTowers();
        assertEquals(5, startingTowers.size());
        List<String> towerTypes = new ArrayList<>();
        for (Tower tower : startingTowers) {
            towerTypes.add(tower.getResourceType());
        }
        assertTrue(towerTypes.contains("Gold"));
        assertTrue(towerTypes.contains("Coal"));
        assertTrue(towerTypes.contains("Wood"));
    }
    @Test
    public void checkGenerateRoundOptions() {
        List<Round> roundOption = gameManager.getRoundOptions();
        assertEquals(3, roundOption.size());
        Round roundOne = roundOption.get(0);
        Round roundTwo = roundOption.get(1);
        Round roundThree = roundOption.get(2);
        assertTrue(roundOne.getNumberOfCarts()
                <= roundTwo.getNumberOfCarts());
        assertTrue(roundTwo.getNumberOfCarts()
                <= roundThree.getNumberOfCarts());
    }
    @Test
    public void setGameDifficultyAndCheckMoney() {
        int easyGameDifficulty = 0;
        int hardGameDifficulty = 1;
        gameManager.setGameDifficulty(easyGameDifficulty);
        int playerStartMoneyEasy = gameManager.getPlayersMoney();
        gameManager.setGameDifficulty(hardGameDifficulty);
        int playerStartMoneyHard = gameManager.getPlayersMoney();
        assertTrue(playerStartMoneyHard < playerStartMoneyEasy);
        assertEquals(50, playerStartMoneyHard);
        assertEquals(100, playerStartMoneyEasy);
    }
    @Test
    public void increaseCurrentRoundByOne() {
        gameManager.increaseCurrentRound();
        assertEquals(currentRound + 1, gameManager.getCurrentRound());
        gameManager.increaseCurrentRound();
        gameManager.increaseCurrentRound();
        assertEquals(currentRound + 3, gameManager.getCurrentRound());
    }
    @Test
    public void updatePlayersMoney() {
        int amountIncrease = 100;
        gameManager.setPlayersMoney(amountIncrease);
        assertEquals(gameManager.getPlayersMoney(), amountIncrease);

    }
    @Test
    public void nameAndValidate() {
        String errorName1 = "+Project";
        String errorName2 = "hi";
        String errorName3 = "katherineIrvings";
        String errorName4 = "Monday s";
        String errorName5 = "kitten's";
        String errorName6 = "Assing%";
        String[] errorNames = {errorName1, errorName2, errorName3, errorName4,
                errorName5, errorName6};
        String correctName1 = "Katie";
        String correctName2 = "123456789021345";
        assertTrue(gameManager.validatePlayerName(correctName1));
        assertTrue(gameManager.validatePlayerName(correctName2));
        for (String error : errorNames) {
        try {
            gameManager.validatePlayerName(error);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid name. Names must be "
                    + "between 3-15 characters and no special characters.",
                    e.getMessage());
        }
    }
    }
    @Test
    public void setPlayersTest() {
        String newPlayerName = "Katherine";
        String currentPlayerName = gameManager.getPlayersName();
        gameManager.setPlayersName(newPlayerName);
        assertTrue(!currentPlayerName.equals(gameManager.getPlayersName()));
        assertEquals(newPlayerName, gameManager.getPlayersName());
    }

    @Test
    public void testShopCreated() {
        gameManager.createShop();
        assertNotNull(gameManager.getCurrentShop());
    }
    @Test
    public void testGetRoundManager() {
        Round selectedRound = new Round(10);
        gameManager.setSelectedRound(selectedRound);
        assertEquals(gameManager.getSelectedRound(), selectedRound);
        gameManager.isRoundWon();
        assertNotNull(gameManager.getRoundManager());
    }
    @Test
    public void testGetNumberOfRound() {
        gameManager.setNumberOfRounds(10);
        assertEquals(gameManager.getNumberOfRounds(),10);

    }
    @Test
    public void testGameWon() {
        gameManager.setGameWon(false);
        assertFalse(gameManager.isGameWon());
        gameManager.setGameWon(true);
        assertTrue(gameManager.isGameWon());
    }
    @Test
    public void testUpgradeList() {
        List<Upgrade> upGradeOwnPlayer = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            upGradeOwnPlayer.add(new Upgrade(i));
        }
        gameManager.setUpgradeList(upGradeOwnPlayer);
        List<Upgrade> upGradesListManager = gameManager.getUpgradeList();
        assertEquals(upGradesListManager, upGradeOwnPlayer);
    }
    @Test
    public void testRandomEvent1() {
        List<Tower> listReserveTowers = new ArrayList<>();
        listReserveTowers.add(new Tower(gameManager.getCurrentRound()));
        gameManager.getInventory().setReserveTowers(listReserveTowers);
        gameManager.getInventory().setMainTowers(mainTowerList);
        gameManager.setReserveTowersList(listReserveTowers);
        gameManager.setNumberOfRounds(10);
        gameManager.createNewRandomEvent();
        RandomEvent randomEvent = gameManager.getRandomEvent();
        gameManager.setRandomEvent(randomEvent);
        assertEquals(randomEvent, gameManager.getRandomEvent());
        assertNotNull(randomEvent);
        int sizeReserveBeforeRandom = listReserveTowers.size();
        int sizeMainBeforeRandom = mainTowerList.size();
        try {
            String message = randomEvent.activateRandomEvent(1);
            assertEquals(message,
                    "Oh No! \nOne of your towers has been stolen");
        gameManager.setRandomEventMessage(message);
            randomEvent.activateRandomEvent(1);
            int sizeReserveBeforeAfterRandom =
                    gameManager.getReserveTowersList().size();
            int sizeMainBeforeAfterRandom =
                    gameManager.getMainTowerList().size();
            assertTrue(sizeReserveBeforeAfterRandom
                    != sizeReserveBeforeRandom || sizeMainBeforeAfterRandom
                    != sizeMainBeforeRandom);
        } catch (BelowMinimumTowerException e) {
            fail("Your main tower list can't have less than one tower");
        }
        assertEquals(gameManager.getRandomEventMessage(),
                "Oh No! \nOne of your towers has been stolen");

    }

}

