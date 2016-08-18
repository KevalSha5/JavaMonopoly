package board.game;

import board.places.special.Community;
import board.places.special.Chance;
import java.util.List;
import java.util.Scanner;
import board.places.places.Property;
import board.places.helper.Action;
import static board.places.helper.Action.CHANCE;
import static board.places.helper.Action.COMMUNITY_CHEST;
import static board.places.helper.Action.GO_TO_JAIL;
import static board.places.helper.Action.PAY_INCOME_TAX;
import static board.places.helper.Action.PAY_LUXURY_TAX;
import static board.places.helper.Action.PAY_PROPERTY_RENT;
import static board.places.helper.Action.PAY_UTILITY_RENT;
import static board.places.helper.Action.PROMPT_TO_BUY;
import board.places.helper.Places;
import board.exceptions.NotEnoughMoneyError;
import javax.swing.JOptionPane;

public class Game {

    private static Scanner in = new Scanner(System.in);

    static void processAction(Action action, Board board) {

        List<Places> places = board.getPlaces();
        Player currentPlayer = board.getCurrentPlayer();
        Bank bank = board.getBank();

        Property property = null;
        Player owner = null;

        if (places.get(currentPlayer.getLocation()) instanceof Property) {
            property = (Property) places.get(currentPlayer.getLocation());
            owner = board.getPlayer(property.getOwnerId());
        }

        switch (action) {
            case PROMPT_TO_BUY: {
                String message = ("Would you like to buy "
                        + property.getName() + " for "
                        + property.getPrice() + "?");
                int input = JOptionPane.showConfirmDialog(board.boardGUI, message, "Buy?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (input == 0) {
                    Game.handlePurchase(property, currentPlayer, bank);
                    board.boardGUI.write("Bought " + property.getName());
                } else {
                    board.boardGUI.write(property.getName() + " goes on auction.");
                }
                break;
            }

            case PAY_INCOME_TAX: {

                int amount = 75;
                if ((currentPlayer.getMoney() * .1) < 200) {
                    amount = (int) (currentPlayer.getMoney() * .1);
                }
                board.boardGUI.write(currentPlayer.getName() + " paid $" + amount + " of Income Tax!");
                Game.transferMoney(currentPlayer, bank, amount);
                break;
            }

            case PAY_LUXURY_TAX: {

                board.boardGUI.write(currentPlayer.getName() + " paid $75 of Luxury Tax!");
                Game.transferMoney(currentPlayer, bank, 75);
                break;
            }

            case PAY_PROPERTY_RENT: {

                int amount = property.getRent();
                board.boardGUI.write(currentPlayer.getName()
                        + " paid rent of $"
                        + amount + " to " + owner.getName());
                Game.transferMoney(currentPlayer, owner, amount);
                break;
            }

            case PAY_UTILITY_RENT: {

                int rentModifier = property.getRent();
                Die utilityDie = new Die();
                int amountRolled = utilityDie.roll();
                int amount = rentModifier * amountRolled;
                board.boardGUI.write(currentPlayer.getName()
                        + " paid rent of $"
                        + amount + " to " + owner.getName());
                Game.transferMoney(currentPlayer, owner, amount);
                break;
            }

            case GO_TO_JAIL: {
                System.err.println("Yet to handle GO TO JAIL");
                break;
            }

            case COMMUNITY_CHEST: {
                drawCommunity(board);
                break;
            }

            case CHANCE: {
                drawChance(board);
                break;
            }
            case DO_NOTHING:
                break;
            default:
                break;
        }
    }

    private static void drawCommunity(Board board) {

        List<Community> communityCards = board.getCommunityCards();
        Community drawnCard = communityCards.get(0);
        communityCards.remove(0);
        communityCards.add(drawnCard);

        Bank bank = board.getBank();
        Player currentPlayer = board.getCurrentPlayer();

        board.boardGUI.write("The community chest card you drew says: \n" + drawnCard);

        switch (drawnCard) {
            case INCOME_TAX_REFUND: {
                transferMoney(bank, currentPlayer, 20);
            }
            break;
            case STREET_REPAIRS: {
                //$40 per house
                //$115 per hotel
            }
            break;
            case INHERET_100: {
                transferMoney(bank, currentPlayer, 100);
            }
            break;
            case GRAND_OPERA_OPENING: {
                List<Player> players = board.getPlayers();
                for (Player otherPlayer : players) {
                    transferMoney(otherPlayer, otherPlayer, 50);
                }
            }
            break;
            case XMAS_FUND_MATURES: {
                transferMoney(bank, currentPlayer, 100);
            }
            break;
            case ADVANCE_TO_GO: {
                int move = 40 - currentPlayer.getLocation();
                movePlayer(move, board);
            }
            break;
            case BANK_ERROR_IN_FAVOR: {
                transferMoney(bank, currentPlayer, 200);
            }
            break;
            case GET_OUT_OF_JAIL: {
                currentPlayer.setGetOutOfJail(true);
            }
            break;
            case PAY_HOSPITAL_100: {
                transferMoney(currentPlayer, bank, 100);
            }
            break;
            case RECEIVE_FOR_SERVICES: {
                transferMoney(bank, currentPlayer, 25);
            }
            break;
            case GO_TO_JAIL: {
                System.err.println("Go to jail not yet supported");
            }
            break;
            case PAY_SCHOOL_TAX: {
                transferMoney(bank, currentPlayer, 150);
            }
            break;
            case DOCTORS_FEE: {
                transferMoney(currentPlayer, bank, 50);
            }
            break;
            case SALE_OF_STOCK: {
                transferMoney(bank, currentPlayer, 45);
            }
            break;
            case LIFE_INSURANCE: {
                transferMoney(bank, currentPlayer, 100);
            }
            break;
            case BEAUTY_CONTEST: {
                transferMoney(bank, currentPlayer, 100);
            }
            break;
        }

    }

    private static void drawChance(Board board) {

        List<Chance> chanceCards = board.getChanceCards();
        Chance drawnCard = chanceCards.get(0);
        chanceCards.remove(0);
        chanceCards.add(drawnCard);

        Bank bank = board.getBank();
        Player currentPlayer = board.getCurrentPlayer();

        board.boardGUI.write("The chance card you drew says: \n" + drawnCard);

        switch (drawnCard) {
            case ADVANCE_READING: {
                int newLocation = 40 - currentPlayer.getLocation() + 5;
                movePlayer(newLocation, board);
            }
            break;
            case BANK_PAYS_YOU_50: {
                transferMoney(bank, currentPlayer, 50);
            }
            break;
            case ADVANCE_ILLINOIS: {
                int move = 0;
                if (currentPlayer.getLocation() < 24) {
                    move = 24 - currentPlayer.getLocation();
                } else {
                    move = 40 - currentPlayer.getLocation() + 24;
                }
                movePlayer(move, board);
            }
            break;
            case GET_OUT_OF_JAIL: {
                currentPlayer.setGetOutOfJail(true);
            }
            break;
            case MAKE_GENERAL_REPAIRS: {
                //$25 for each house
                //$100 for each hotel
            }
            break;
            case ADVANCE_NEAREST_RAILROAD: {
                int move = 0;
                if (currentPlayer.getLocation() == 7) {
                    move = 3;
                }
                if (currentPlayer.getLocation() == 22) {
                    move = 8;
                }
                if (currentPlayer.getLocation() == 36) {
                    move = 9;
                }
                movePlayer(move, board);
            }
            break;
            case POOR_TAX_15: {
                transferMoney(currentPlayer, bank, 15);
            }
            break;
            case WALK_ON_BOARDWALK: {
                int move = 39 - currentPlayer.getLocation();
                movePlayer(move, board);
            }
            break;
            case ADVANCE_ST_CHARLES: {
                int move = 0;
                if (currentPlayer.getLocation() < 11) {
                    move = 11 - currentPlayer.getLocation();
                } else {
                    move = 40 - currentPlayer.getLocation() + 11;
                }
                movePlayer(move, board);
            }
            break;
            case ADVANCE_NEAREST_UTILITY: {
                int move = 0;
                if (currentPlayer.getLocation() == 7) {
                    move = 5;
                }
                if (currentPlayer.getLocation() == 22) {
                    move = 6;
                }
                if (currentPlayer.getLocation() == 36) {
                    move = 16;
                }
                movePlayer(move, board);
            }
            break;
            case GO_BACK_3_SPACES: {
                movePlayer(-3, board);
            }
            break;
            case ADVANCE_TO_GO: {
                int move = 40 - currentPlayer.getLocation();
                movePlayer(move, board);
            }
            break;
            case GO_TO_JAIL: {
                System.err.println("Go To Jail not yet handled");
            }
            break;
        }
    }

    private static void handlePurchase(Property property, Player player, Bank bank) {
        property.setOwner(player);
        property.setBought(true);
        Game.transferMoney(player, bank, property.getPrice());
    }

    static Action movePlayer(int amountRolled, Board board) {

        Player currentPlayer = board.getCurrentPlayer();
        List<Places> places = board.getPlaces();

        int oldPlace = currentPlayer.getLocation();
        int newPlace = oldPlace + amountRolled;

        if (newPlace >= places.size()) {
            newPlace -= places.size();
        }

        currentPlayer.setLocation(newPlace);

        board.boardGUI.movePlayerOnGUI(currentPlayer, oldPlace, newPlace);


//        board.boardGUI.write(currentPlayer.getName() + " rolled " + amountRolled);
        board.boardGUI.write(currentPlayer.getName()
                + " moved from " + places.get(oldPlace).getName()
                + " to " + places.get(newPlace).getName() + ".");

        Action action = places.get(newPlace).landPlayer(currentPlayer);
        return action;
    }

    private static void handleSell(Property property, Player currentOwner, Player newOwner, int amount) {
        property.setOwner(newOwner);
        Game.transferMoney(currentOwner, newOwner, amount);
    }

    private static void handleMortgage(Player currentPlayer, Bank bank) {
    }

    private static void transferMoney(Player fromPlayer, Bank toBank, int amount) {
        try {
            fromPlayer.subtractMoney(amount);
            toBank.addMoney(amount);

        } catch (NotEnoughMoneyError e) {
            //
        }
    }

    private static void transferMoney(Bank fromBank, Player toPlayer, int amount) {
        fromBank.subtractMoney(amount);
        toPlayer.addMoney(amount);
    }

    private static void transferMoney(Player fromPlayer, Player toPlayer, int amount) {
        fromPlayer.subtractMoney(amount);
        toPlayer.addMoney(amount);
    }
}
