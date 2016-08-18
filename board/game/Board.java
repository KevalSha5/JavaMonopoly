package board.game;

import board.gui.BoardGUI;
import board.places.special.Community;
import board.places.special.Chance;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import board.places.helper.PropertyIdentifier;
import board.places.places.Railroad;
import board.places.places.SpecialPlace;
import board.places.places.Street;
import board.places.places.Utility;
import board.places.helper.Action;
import board.places.helper.Places;
import board.places.places.Property;
import java.awt.Color;
import java.util.Scanner;

public class Board {

    private List<Places> placesList;
    private List<Player> playersList;
    private List<Chance> chanceCards;
    private List<Community> communityCards;
    private Bank bank;
    private Die die;
    private Player currentPlayer;
    private int currentPlayerCounter;
    private Scanner in;
    public BoardGUI boardGUI;

    public Board(List<Player> players) {
        setPlaces();
        this.playersList = players;

        setChanceCards();
        setCommunityCards();

        bank = new Bank();
        die = new Die();

        currentPlayerCounter = 0;
        currentPlayer = players.get(0);

        in = new Scanner(System.in);
        boardGUI = new BoardGUI(this);
    }

    public void play() {

        

        int rolled = die.roll();
        boardGUI.write(currentPlayer.getName() + " rolled " + die.getRoll() + ".");
        if (die.isDoubles()) {
            currentPlayer.setRolledDoubles(true);
            currentPlayer.incrementTimesRolledDoubles();
        }

        Action action = Game.movePlayer(rolled, this);
        Game.processAction(action, this);

        currentPlayer = getNextPlayer();
        boardGUI.updateCurentPlayerLabel(currentPlayer);
        boardGUI.updatePlayerStatuses();
    }

    public Bank getBank() {
        return this.bank;
    }

    public Die getDie() {
        return this.die;
    }

    public List<Street> getBuildableStreets(Player player) {

        List<Street> buildableStreets = new ArrayList<Street>();

        Street street = null;

        for (Places place : placesList) {
            if (place instanceof Street) {
                street = (Street) place;
                if (street.getOwnerId() == player.getId()) {
                    buildableStreets.add(street);
                }
            }
        }
        return buildableStreets;
    }

    public Player getNextPlayer() {
        if (this.currentPlayer != null) {
            boardGUI.write("");
            if (this.currentPlayer.rolledDouble()) {
                if (this.currentPlayer.getTimesRolledDoubles() <= 3) {
                    currentPlayer.setRolledDoubles(false);
                    boardGUI.write(currentPlayer.getName() + " plays again, because he/she rolled doubles previously.");
                    return this.currentPlayer;
                } else {
                    boardGUI.write("Player needs to go to jail. This is not yet supported.");
                }
            } else {
                currentPlayer.resetTimesRolledDoubles();
            }
        }

        if (currentPlayerCounter < playersList.size() - 1) {
            currentPlayerCounter += 1;
            currentPlayer = playersList.get(currentPlayerCounter);
        } else {
            currentPlayerCounter = 0;
            currentPlayer = playersList.get(currentPlayerCounter);
        }
        return currentPlayer;
    } //Looks messy

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getPlayer(int playerId) {
        Player player = null;
        for (Player aPlayer : playersList) {
            if (aPlayer.getId() == playerId) {
                player = aPlayer;
                break;
            }
        }
        return player;
    }

    public List getPlayers() {
        return playersList;
    }

    private void setPlayers() {
        Player p1 = new Player("Superman");
        Player p2 = new Player("Batman");

        p1.setColor(Color.YELLOW);
        p2.setColor(Color.GRAY);

        playersList = new ArrayList<Player>();
        playersList.add(p1);
        playersList.add(p2);
    } //Needs to be made dynamic (gets input form GUI)

    private void setPlaces() {

        SpecialPlace sp = null;
        Street street = null;
        Railroad rr = null;
        Utility util = null;

        placesList = new ArrayList<Places>();

        sp = new SpecialPlace("Go");
        placesList.add(sp);

        street = new Street("Medeteranean Avenue");
        street.setPropertyIdentifier(PropertyIdentifier.PURPLE);
        street.setPrice(60);
        street.setRent(2);
        street.setHouseRent(10, 30, 90, 160);
        street.setHotelRent(250);
        street.setBuildingCost(50);
        placesList.add(street);

        sp = new SpecialPlace("Community Chest");
        placesList.add(sp);

        street = new Street("Baltic Avenue");
        street.setPropertyIdentifier(PropertyIdentifier.PURPLE);
        street.setPrice(60);
        street.setRent(4);
        street.setHouseRent(20, 60, 180, 320);
        street.setHotelRent(450);
        street.setBuildingCost(50);
        placesList.add(street);

        sp = new SpecialPlace("Income Tax");
        placesList.add(sp);

        rr = new Railroad("Reading Railroad");
        rr.setPropertyIdentifier(PropertyIdentifier.RAILROAD);
        placesList.add(rr);

        street = new Street("Oriental Avenue");
        street.setPropertyIdentifier(PropertyIdentifier.LIGHTBLUE);
        street.setPrice(100);
        street.setRent(6);
        street.setHouseRent(30, 90, 270, 400);
        street.setHotelRent(550);
        street.setBuildingCost(50);
        placesList.add(street);

        sp = new SpecialPlace("Chance");
        placesList.add(sp);


        street = new Street("Vermont Avenue");
        street.setPropertyIdentifier(PropertyIdentifier.LIGHTBLUE);
        street.setPrice(100);
        street.setRent(6);
        street.setHouseRent(30, 90, 270, 400);
        street.setHotelRent(550);
        street.setBuildingCost(50);
        placesList.add(street);
        street = new Street("Connecticut Avenue");
        street.setPropertyIdentifier(PropertyIdentifier.LIGHTBLUE);
        street.setPrice(100);
        street.setRent(8);
        street.setHouseRent(40, 100, 300, 450);
        street.setHotelRent(600);
        street.setBuildingCost(50);
        placesList.add(street);

        sp = new SpecialPlace("Just Visiting");
        placesList.add(sp);

        street = new Street("St. Charles Place");
        street.setPropertyIdentifier(PropertyIdentifier.MAROON);
        street.setPrice(140);
        street.setRent(10);
        street.setHouseRent(50, 150, 450, 625);
        street.setHotelRent(750);
        street.setBuildingCost(100);
        placesList.add(street);

        util = new Utility("Electric Company");
        util.setPropertyIdentifier(PropertyIdentifier.UTILITY);
        placesList.add(util);

        street = new Street("States Avenue");
        street.setPropertyIdentifier(PropertyIdentifier.MAROON);
        street.setPrice(140);
        street.setRent(10);
        street.setHouseRent(50, 150, 450, 625);
        street.setHotelRent(750);
        street.setBuildingCost(100);
        placesList.add(street);

        street = new Street("Virginia Avenue");
        street.setPropertyIdentifier(PropertyIdentifier.MAROON);
        street.setPrice(160);
        street.setRent(12);
        street.setHouseRent(60, 180, 500, 700);
        street.setHotelRent(900);
        street.setBuildingCost(100);
        placesList.add(street);

        rr = new Railroad("Pennsylvania Railroad");
        rr.setPropertyIdentifier(PropertyIdentifier.RAILROAD);
        placesList.add(rr);

        street = new Street("St. James Place");
        street.setPropertyIdentifier(PropertyIdentifier.ORGANGE);
        street.setPrice(180);
        street.setRent(14);
        street.setHouseRent(70, 200, 550, 750);
        street.setHotelRent(950);
        street.setBuildingCost(100);
        placesList.add(street);

        sp = new SpecialPlace("Community Chest");
        placesList.add(sp);

        street = new Street("Tennessee Avenue");
        street.setPropertyIdentifier(PropertyIdentifier.ORGANGE);
        street.setPrice(180);
        street.setRent(14);
        street.setHouseRent(70, 200, 550, 750);
        street.setHotelRent(950);
        street.setBuildingCost(100);
        placesList.add(street);

        street = new Street("New York Avenue");
        street.setPropertyIdentifier(PropertyIdentifier.ORGANGE);
        street.setPrice(200);
        street.setRent(16);
        street.setHouseRent(80, 220, 600, 800);
        street.setHotelRent(1000);
        street.setBuildingCost(100);
        placesList.add(street);

        sp = new SpecialPlace("Free Parking");
        placesList.add(sp);

        street = new Street("Kentucky Avenue");
        street.setPropertyIdentifier(PropertyIdentifier.RED);
        street.setPrice(220);
        street.setRent(18);
        street.setHouseRent(90, 250, 700, 875);
        street.setHotelRent(1050);
        street.setBuildingCost(150);
        placesList.add(street);

        sp = new SpecialPlace("Chance");
        placesList.add(sp);

        street = new Street("Indiana Avenue");
        street.setPropertyIdentifier(PropertyIdentifier.RED);
        street.setPrice(220);
        street.setRent(18);
        street.setHouseRent(90, 250, 700, 875);
        street.setHotelRent(1050);
        street.setBuildingCost(150);
        placesList.add(street);

        street = new Street("Illinois Avenue");
        street.setPropertyIdentifier(PropertyIdentifier.RED);
        street.setPrice(240);
        street.setRent(20);
        street.setHouseRent(100, 300, 750, 925);
        street.setHotelRent(1100);
        street.setBuildingCost(150);
        placesList.add(street);

        rr = new Railroad("B. &. O Railroad");
        rr.setPropertyIdentifier(PropertyIdentifier.RAILROAD);
        placesList.add(rr);

        street = new Street("Atlantic Avenue");
        street.setPropertyIdentifier(PropertyIdentifier.YELLOW);
        street.setPrice(260);
        street.setRent(22);
        street.setHouseRent(110, 330, 800, 975);
        street.setHotelRent(1150);
        street.setBuildingCost(150);
        placesList.add(street);

        street = new Street("Ventnor Avenue");
        street.setPropertyIdentifier(PropertyIdentifier.YELLOW);
        street.setPrice(220);
        street.setRent(18);
        street.setHouseRent(90, 250, 700, 875);
        street.setHotelRent(1050);
        street.setBuildingCost(150);
        placesList.add(street);

        util = new Utility("Water Works");
        util.setPropertyIdentifier(PropertyIdentifier.UTILITY);
        placesList.add(util);

        street = new Street("Marvin Gardens");
        street.setPropertyIdentifier(PropertyIdentifier.YELLOW);
        street.setPrice(280);
        street.setRent(24);
        street.setHouseRent(120, 360, 850, 1025);
        street.setHotelRent(1200);
        street.setBuildingCost(150);
        placesList.add(street);

        sp = new SpecialPlace("Go To Jail");
        placesList.add(sp);

        street = new Street("Pacific Avenue");
        street.setPropertyIdentifier(PropertyIdentifier.GREEN);
        street.setPrice(300);
        street.setRent(26);
        street.setHouseRent(130, 390, 900, 1100);
        street.setHotelRent(1275);
        street.setBuildingCost(200);
        placesList.add(street);

        street = new Street("North Carolina Avenue");
        street.setPropertyIdentifier(PropertyIdentifier.GREEN);
        street.setPrice(300);
        street.setRent(26);
        street.setHouseRent(130, 390, 900, 1100);
        street.setHotelRent(1050);
        street.setBuildingCost(200);
        placesList.add(street);

        sp = new SpecialPlace("Community Chest");
        placesList.add(sp);

        street = new Street("Pennsylvania Avenue");
        street.setPropertyIdentifier(PropertyIdentifier.GREEN);
        street.setPrice(320);
        street.setRent(28);
        street.setHouseRent(150, 450, 1000, 1200);
        street.setHotelRent(1050);
        street.setBuildingCost(200);
        placesList.add(street);

        rr = new Railroad("Short Line");
        rr.setPropertyIdentifier(PropertyIdentifier.RAILROAD);
        placesList.add(rr);

        sp = new SpecialPlace("Chance");
        placesList.add(sp);

        street = new Street("Park Place");
        street.setPropertyIdentifier(PropertyIdentifier.BLUE);
        street.setPrice(350);
        street.setRent(35);
        street.setHouseRent(175, 500, 1100, 1300);
        street.setHotelRent(1500);
        street.setBuildingCost(200);
        placesList.add(street);

        sp = new SpecialPlace("Luxury Tax");
        placesList.add(sp);

        street = new Street("Boardwalk");
        street.setPropertyIdentifier(PropertyIdentifier.BLUE);
        street.setPrice(400);
        street.setRent(50);
        street.setHouseRent(200, 600, 1400, 1700);
        street.setHotelRent(2000);
        street.setBuildingCost(200);
        placesList.add(street);

    }

    public Places getPlaceAt(int i) {
        return placesList.get(i);
    } //Useless Method

    public Property getPropertyByName(String propertyName) {

        for (Places places : placesList) {
            if (places instanceof Street) {
                if (places.getName().equals(propertyName)) {
                    return (Property) places;
                }
            }
        }
        return null;
    }

    public List getPlaces() {
        return this.placesList;
    }

    public List getChanceCards() {
        return chanceCards;
    }

    private void setChanceCards() {
        chanceCards = new ArrayList<Chance>();
        chanceCards.add(Chance.ADVANCE_ILLINOIS);
        chanceCards.add(Chance.ADVANCE_NEAREST_UTILITY);
        chanceCards.add(Chance.ADVANCE_READING);
        chanceCards.add(Chance.ADVANCE_ST_CHARLES);
        chanceCards.add(Chance.ADVANCE_TO_GO);
        chanceCards.add(Chance.BUILDING_LOAN_MATURES);
        chanceCards.add(Chance.ELECTED_CHAIRMAN);
        chanceCards.add(Chance.GET_OUT_OF_JAIL);
        chanceCards.add(Chance.GO_BACK_3_SPACES);
        chanceCards.add(Chance.GO_TO_JAIL);
        chanceCards.add(Chance.MAKE_GENERAL_REPAIRS);
        chanceCards.add(Chance.ADVANCE_NEAREST_RAILROAD);
        chanceCards.add(Chance.POOR_TAX_15);
        chanceCards.add(Chance.WALK_ON_BOARDWALK);

        Collections.shuffle(chanceCards);
    }

    public List getCommunityCards() {
        return communityCards;
    }

    private void setCommunityCards() {
        communityCards = new ArrayList<Community>();
        communityCards.add(Community.ADVANCE_TO_GO);
        communityCards.add(Community.BANK_ERROR_IN_FAVOR);
        communityCards.add(Community.BEAUTY_CONTEST);
        communityCards.add(Community.DOCTORS_FEE);
        communityCards.add(Community.GET_OUT_OF_JAIL);
        communityCards.add(Community.GO_TO_JAIL);
        communityCards.add(Community.GRAND_OPERA_OPENING);
        communityCards.add(Community.INCOME_TAX_REFUND);
        communityCards.add(Community.INHERET_100);
        communityCards.add(Community.LIFE_INSURANCE);
        communityCards.add(Community.PAY_HOSPITAL_100);
        communityCards.add(Community.PAY_SCHOOL_TAX);
        communityCards.add(Community.RECEIVE_FOR_SERVICES);
        communityCards.add(Community.SALE_OF_STOCK);
        communityCards.add(Community.STREET_REPAIRS);
        communityCards.add(Community.XMAS_FUND_MATURES);

        Collections.shuffle(communityCards);
    }
}