import java.awt.*;
import java.io.*;
import java.util.Random;
import java.awt.event.*;
import javax.swing.*;

public class Items  {

	private int itemnumber;
	private int itemEffect;
	private int itemType;
	private String itemName;
	private String itemDescription;
	private boolean inBattle;
	
    public Items(int i) {
    	itemnumber = i;
    }
    
    public String getItemName() {
    	if (itemnumber == 0) {
    		itemName = "";
    	}
    	
    	else if (itemnumber == 1) {
    		itemName = "Potion";
    	}
    	
    	else if (itemnumber == 2) {
    		itemName = "PokeBall";
    	}
    	
    	else if (itemnumber == 3) {
    		itemName = "Berry";
    	}
    	
    	else if (itemnumber == 4) {
    		itemName = "Bicycle";
    	}
    	return itemName;
    }
    
    public String getItemDescription() {
    	if (itemnumber == 0) {
    		itemDescription = "";
    	}
    	else if (itemnumber == 1) {
    		itemDescription = "Heal a Pokemon for 20HP.";
    	}
    	else if (itemnumber == 2) {
    		itemDescription = "Throw at a Wild Pokemon to capture it!";
    	}
    	else if (itemnumber == 3) {
    		itemDescription = "A Pokemon may Hold this item. Restores 10HP.";
    	}
    	else if (itemnumber == 4) {
    		itemDescription = "A super-fast Bike that you can ride!";
    	}
    	return itemDescription;
    }
    
    public int getItemEffect() {
    	if (itemnumber == 0) {
    		itemEffect = 0;
    	}
    	else if (itemnumber == 1) {
    		itemEffect = 1;
    	}
    	else if (itemnumber == 2) {
    		itemEffect = 2;
    	}
    	else if (itemnumber == 3) {
    		itemEffect = 1;
    	}
    	else if (itemnumber == 4) {
    		itemEffect = 3;
    	}
    	return itemEffect;
    }
    
    public int getItemType() {
    	if (itemnumber == 0) {
    		itemType = 0;
    	}
    	else if (itemnumber == 1) {
    		itemType = 1;
    	}
    	else if (itemnumber == 2) {
    		itemType = 2;
    	}
    	else if (itemnumber == 3) {
    		itemType = 1;
    	}
    	else if (itemnumber == 4) {
    		itemType = 3;
    	}
    	return itemType;
    }
    
    public boolean isItemUsableInBattle() {
    	if (itemnumber == 0) {
    		inBattle = false;
    	}
    	else if (itemnumber == 1) {
    		inBattle = true;
    	}
    	else if (itemnumber == 2) {
    		inBattle = true;
    	}
    	else if (itemnumber == 3) {
    		inBattle = true;
    	}
    	else if (itemnumber == 4) {
    		inBattle = false;
    	}
    	return inBattle;
    }
}