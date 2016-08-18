package board.places.places;

import board.exceptions.HousingError;
import board.game.Game;

public class Street extends Property {

    private int[] houseRent;
    private int hotelRent;
    private int buildingCost;
    private int numOfHouses;
    private boolean hotel;
    private boolean houses;

    public Street(String name) {
        super(name);
    }


    public void buildHouse() {
        if ((numOfHouses + 1) > 4){
            throw new HousingError("There can't be mroe than 4 houses on a street.");
        }
        setHouses(true);
//        System.out.println("House built on: " + getName());
        
        numOfHouses++;
    }
    
    public void sellHouse(){
        if ((numOfHouses - 1) < 0){
            throw new HousingError("There are no houses to sell.");
        }
        if (numOfHouses == 0){
            setHouses(false);
        }
//        System.out.println("House sold on: " + getName());
        numOfHouses--;
    }

    public int getNumOfHouses() {
        return this.numOfHouses;
    }
   
    public boolean hasHotel() {
        return this.hotel;
    }

    public boolean hasHosues() {
        return houses;
    }
    

    public void setHotel(boolean hotel) {
        this.hotel = hotel;
    }

    public void setHouses(boolean houses) {
        this.houses = houses;
    }

   
    public int getBuildingCost() {
        return this.buildingCost;
    }

    public void setBuildingCost(int buildingCost) {
        this.buildingCost = buildingCost;
    }

    
    @Override
    public int getRent() {
        int rent = -1;

        if (isBought()) {
            if (!hasHotel() && !hasHosues()) {
                rent = super.getRent();
            } else if (hasHosues()) {
                rent = houseRent[numOfHouses - 1];
            } else {
                rent = hotelRent;
            }
        }
        return rent;
    } //Need to add support for doubling rent on undeveloped streets

    public void setHouseRent(int one, int two, int three, int four) {
        houseRent = new int[4];
        houseRent[0] = one;
        houseRent[1] = two;
        houseRent[2] = three;
        houseRent[3] = four;
    }

    public void setHotelRent(int hotelRent) {
        this.hotelRent = hotelRent;
    }
}