package stegosaurus;

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.text.*;
import java.util.*;
import java.util.List;

import javax.xml.stream.events.Characters;

import java.lang.Byte;

/**
 * A class that represents a picture. This class inherits from SimplePicture and
 * allows the student to add functionality to the Picture class.
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture {
	///////////////////// constructors //////////////////////////////////

	/**
	 * Constructor that takes no arguments
	 */
	public Picture() {
		/*
		 * not needed but use it to show students the implicit call to super()
		 * child constructors always call a parent constructor
		 */
		super();
	}

	/**
	 * Constructor that takes a file name and creates the picture
	 * 
	 * @param fileName
	 *            the name of the file to create the picture from
	 */
	public Picture(String fileName) {
		// let the parent class handle this fileName
		super(fileName);
	}

	/**
	 * Constructor that takes the width and height
	 * 
	 * @param height
	 *            the height of the desired picture
	 * @param width
	 *            the width of the desired picture
	 */
	public Picture(int height, int width) {
		// let the parent class handle this width and height
		super(width, height);
	}

	/**
	 * Constructor that takes a picture and creates a copy of that picture
	 * 
	 * @param copyPicture
	 *            the picture to copy
	 */
	public Picture(Picture copyPicture) {
		// let the parent class do the copy
		super(copyPicture);
	}

	/**
	 * Constructor that takes a buffered image
	 * 
	 * @param image
	 *            the buffered image to use
	 */
	public Picture(BufferedImage image) {
		super(image);
	}

	////////////////////// methods ///////////////////////////////////////

	/**
	 * Method to return a string with information about this picture.
	 * 
	 * @return a string with information about the picture such as fileName,
	 *         height and width.
	 */
	public String toString() {
		String output = "Picture, filename " + getFileName() + " height " + getHeight() + " width " + getWidth();
		return output;

	}

	/** Method to set the blue to 0 */
	public void zeroBlue() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setBlue(0);
			}
		}
	}

	/**
	 * keeps blue and makes red and green 0
	 */
	public void keepOnlyBlue() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setRed(0);
				pixelObj.setGreen(0);
			}
		}
	}

	/**
	 * negates all pixels in the image
	 */
	public void negate() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setRed(255 - pixelObj.getRed());
				pixelObj.setGreen(255 - pixelObj.getGreen());
				pixelObj.setBlue(255 - pixelObj.getBlue());
			}
		}
	}

	public void grayscale() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				int average = (pixelObj.getRed() + pixelObj.getBlue() + pixelObj.getGreen()) / 3;
				pixelObj.setRed(average);
				pixelObj.setGreen(average);
				pixelObj.setBlue(average);
			}
		}
	}

	public void fixUnderWater() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {

				pixelObj.setRed(pixelObj.getBlue());
				pixelObj.setGreen(pixelObj.getGreen());
				pixelObj.setBlue(pixelObj.getBlue());
			}
		}
	}

	/**
	 * Method that mirrors the picture around a vertical mirror in the center of
	 * the picture from left to right
	 */
	public void mirrorVertical() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int width = pixels[0].length;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < width / 2; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][width - 1 - col];
				rightPixel.setColor(leftPixel.getColor());
			}
		}
	}

	public void mirrorVerticalRightToLeft() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int width = pixels[0].length;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < width / 2; col++) {
				rightPixel = pixels[row][col];
				leftPixel = pixels[row][width - 1 - col];
				leftPixel.setColor(rightPixel.getColor());
			}
		}
	}

	public void mirrorHorizontal() {

		Pixel[][] pixels = this.getPixels2D();
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int width = pixels[0].length;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < width; col++) {
				rightPixel = pixels[row][col];
				leftPixel = pixels[pixels.length - 1 - row][col];
				leftPixel.setColor(rightPixel.getColor());
			}
		}

	}
//480  by 640
	public void mirrorDiagonal() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int width = pixels[0].length;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < width; col++) {
				if(row <= 639 && col <= 479){
					rightPixel = pixels[col][row];
				}
				else{
					rightPixel = pixels[row][col];
				}
				leftPixel = pixels[row][col];
				
				rightPixel.setColor(leftPixel.getColor());
			}
		}
	}

	public void mirrorHorizontalBotToTop() {

		Pixel[][] pixels = this.getPixels2D();
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int width = pixels[0].length;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < width; col++) {
				rightPixel = pixels[pixels.length - 1 - row][col];
				leftPixel = pixels[row][col];
				leftPixel.setColor(rightPixel.getColor());
			}
		}

	}

	/** Mirror just part of a picture of a temple */
	public void mirrorTemple() {
		int mirrorPoint = 276;
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int count = 0;
		Pixel[][] pixels = this.getPixels2D();

		// loop through the rows
		for (int row = 27; row < 97; row++) {
			// loop from 13 to just before the mirror point
			for (int col = 13; col < mirrorPoint; col++) {
				count++;
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
				rightPixel.setColor(leftPixel.getColor());
			}
		}
		System.out.println(count);
	}

	public void mirrorArms() {
		int mirrorPoint = 206;
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		Pixel[][] pixels = this.getPixels2D();

		// loop through the rows
		for (int row = 156; row < 193; row++) {
			// loop from 13 to just before the mirror point
			for (int col = 103; col < mirrorPoint; col++) {

				leftPixel = pixels[row][col];
				rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
				Pixel oldRightPixel = rightPixel;
				rightPixel.setColor(leftPixel.getColor());
				Pixel overload = pixels[row + 55][col - 5];
				overload.setColor(oldRightPixel.getColor());
				Pixel newleftPixel = pixels[row + 55][col - 5];
				Pixel newrightPixel = pixels[row + 55][mirrorPoint - col + mirrorPoint - 5];
				newrightPixel.setColor(newleftPixel.getColor());
			}
		}
	}

	public void mirrorGull() {
		int mirrorPoint = 347;
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		Pixel[][] pixels = this.getPixels2D();

		// loop through the rows
		for (int row = 232; row < 323; row++) {
			// loop from 13 to just before the mirror point
			for (int col = 237; col < mirrorPoint; col++) {

				leftPixel = pixels[row][col];
				rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
				rightPixel.setColor(leftPixel.getColor());

			}
		}
	}

	/**
	 * copy from the passed fromPic to the specified startRow and startCol in
	 * the current picture
	 * 
	 * @param fromPic
	 *            the picture to copy from
	 * @param startRow
	 *            the start row to copy to
	 * @param startCol
	 *            the start col to copy to
	 */
	public void copy(Picture fromPic, int startRow, int startCol) {
		Pixel fromPixel = null;
		Pixel toPixel = null;
		Pixel[][] toPixels = this.getPixels2D();
		Pixel[][] fromPixels = fromPic.getPixels2D();
		for (int fromRow = 0, toRow = startRow; fromRow < fromPixels.length
				&& toRow < toPixels.length; fromRow++, toRow++) {
			for (int fromCol = 0, toCol = startCol; fromCol < fromPixels[0].length
					&& toCol < toPixels[0].length; fromCol++, toCol++) {
				fromPixel = fromPixels[fromRow][fromCol];
				toPixel = toPixels[toRow][toCol];
				toPixel.setColor(fromPixel.getColor());
			}
		}
	}

	public void copy2(Picture fromPic, int startRow, int startCol, int startSecondcol, int startSecondrow, int endrow,
			int endcol) {
		Pixel fromPixel = null;
		Pixel toPixel = null;
		Pixel[][] toPixels = this.getPixels2D();
		Pixel[][] fromPixels = fromPic.getPixels2D();
		for (int fromRow = startSecondrow, toRow = startRow; fromRow < endrow
				&& toRow < toPixels.length; fromRow++, toRow++) {
			for (int fromCol = startSecondcol, toCol = startCol; fromCol < endcol
					&& toCol < toPixels[0].length; fromCol++, toCol++) {
				fromPixel = fromPixels[fromRow][fromCol];
				toPixel = toPixels[toRow][toCol];
				toPixel.setColor(fromPixel.getColor());
			}
		}
	}

	/** Method to create a collage of several pictures */
	public void createCollage() {
		Picture flower1 = new Picture("flower1.jpg");
		Picture flower2 = new Picture("flower2.jpg");
		this.copy(flower1, 0, 0);
		this.copy(flower2, 100, 0);
		this.copy(flower1, 200, 0);
		Picture flowerNoBlue = new Picture(flower2);
		flowerNoBlue.zeroBlue();
		this.copy(flowerNoBlue, 300, 0);
		this.copy(flower1, 400, 0);
		this.copy(flower2, 500, 0);
		this.mirrorVertical();
		this.write("collage.jpg");
	}

	public void myCollage() {
		Picture flower1 = new Picture("flower1.jpg");
		Picture flower2 = new Picture("flower2.jpg");
		Picture flower3 = new Picture("beach.jpg");
		flower3.fixUnderWater();
		flower1.mirrorHorizontalBotToTop();
		this.copy(flower1, 0, 0);
		this.copy(flower2, 100, 0);
		this.copy(flower1, 200, 0);
		this.copy(flower3, 200, 0);
		Picture flowerNoBlue = new Picture(flower2);
		flowerNoBlue.zeroBlue();
		this.copy(flowerNoBlue, 300, 0);
		this.copy(flower1, 400, 0);
		this.copy(flower2, 500, 0);
		this.mirrorVertical();
		this.write("collage.jpg");
	}

	/**
	 * Method to show large changes in color
	 * 
	 * @param edgeDist
	 *            the distance for finding edges
	 */
	public void edgeDetection(int edgeDist) {
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		Pixel[][] pixels = this.getPixels2D();
		Color rightColor = null;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[0].length - 1; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][col + 1];
				rightColor = rightPixel.getColor();
				if (leftPixel.colorDistance(rightColor) > edgeDist)
					leftPixel.setColor(Color.BLACK);
				else
					leftPixel.setColor(Color.WHITE);
			}
		}

	}

	public void edgeDetection2(int edgeDist) {
		ArrayList<Pixel> changed = new ArrayList<>();
		int count = 0;
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		Pixel[][] pixels = this.getPixels2D();
		Color rightColor = null;
		
		float brightenFactor = 2.3f;

		BufferedImage image = this.getBufferedImage();
		RescaleOp op = new RescaleOp(brightenFactor, 0, null);
		image = op.filter(image, image);
		
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[0].length - 1; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][col + 1];
				rightColor = rightPixel.getColor();
				if (leftPixel.colorDistance(rightColor) > edgeDist)
					leftPixel.setColor(Color.BLACK);
				else
					leftPixel.setColor(Color.WHITE);
			}
		}
	}

	/*
	 * Main method for testing - each class in Java can have a main method
	 */
	public static void main(String[] args) {
		SimplePicture p = new Picture();
		Picture beach = new Picture("beach.jpg");
		beach.explore();
		beach.zeroBlue();
		beach.explore();
	}
	
	public void Chromakey(){
		int greenNeg = 25;
		int Red;
		int Blue;
		int Green;
		Pixel[][] pixels = this.getPixels2D();
		Pixel[][] greenPixels = new Pixel[750][1000];
		int middle = pixels[0].length / 2;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[0].length - 1; col++) {
				Blue = pixels[row][col].getBlue();
				Red = pixels[row][col].getRed();
				Green = pixels[row][col].getGreen();
				if(Blue < Green - greenNeg && Red < Green - greenNeg){
					greenPixels[row][col] = pixels[row][col];
				}
				
			}
		}
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[0].length - 1; col++) {
				try{
				
					greenPixels[row][col].setColor(Color.RED);
				} catch(NullPointerException e){
					
				}
			}
		}
	}
	public void Chromakey(Picture temple){
		int redNeg = 25;
		int Red;
		int Blue;
		int Green;
		int count = 1;
		Pixel[][] pixels = this.getPixels2D();
		Pixel[][] templePixel = temple.getPixels2D();
		Pixel[][] redPixels = new Pixel[750][1000];
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[0].length - 1; col++) {
				Blue = pixels[row][col].getBlue();
				Red = pixels[row][col].getRed();
				Green = pixels[row][col].getGreen();
				if(Blue < Red - redNeg && Green < Red - redNeg){
					redPixels[row][col] = pixels[row][col];
				}
				System.out.println(row + " row " + col + " col ");
			}
		}
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[0].length - 1; col++) {
				try{
					System.out.println(row + " row " + col + " col ");
					if(col < 568){
						if(row <= 425){
							redPixels[row][col].setColor(templePixel[row][col].getColor());
						}
					}
					else{
						if(row >= ((count+1) * 426) || col >= ((count+1) * 568)){
							count++;
						}
						System.out.println("count has increased by 1, it is now " + count);
						if(col >= 568 && row >= 426){
							redPixels[row][col].setColor(templePixel[row - (count * 426)][col - (count * 568)].getColor());
						}
						else if(col >= 568)
							redPixels[row][col].setColor(templePixel[row][col - (count * 568)].getColor());
						else if(row >= 426)
							redPixels[row][col].setColor(templePixel[row - (count * 426)][col].getColor());
					}
				}catch(NullPointerException e){
					
				}
			}
		}	
	}
	public void Chromakey(Picture beach, Picture temple) {
		int greenNeg = 25;
		int Red;
		int Blue;
		int Green;
		Pixel[][] pixels = this.getPixels2D();
		Pixel[][] beachPixel = beach.getPixels2D();
		Pixel[][] templePixel = temple.getPixels2D();
		Pixel[][] greenPixels = new Pixel[750][1000];
		int middle = pixels[0].length / 2;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[0].length - 1; col++) {
				Blue = pixels[row][col].getBlue();
				Red = pixels[row][col].getRed();
				Green = pixels[row][col].getGreen();
				if(Blue < Green - greenNeg && Red < Green - greenNeg){
					greenPixels[row][col] = pixels[row][col];
				}
				try{
					if(col <= middle){
						if(row >= 480){
							greenPixels[row][col].setColor(beachPixel[row-480][col].getColor());
						}
						else{
							greenPixels[row][col].setColor(beachPixel[row][col].getColor());
						}
						
					}
					else{
						if(row <= 425){
							greenPixels[row][col].setColor(templePixel[row][col-middle].getColor());
						}
						else{
							greenPixels[row][col].setColor(templePixel[row -426][col-middle].getColor());
						}
					}
				}catch(NullPointerException e){
					
				}
			}
		}
	}
	
	//Stego is bleow hererererererereresaughsdiknvkjfdngreiovjrdisnvijkranvjnakjgnfkjdanvkjfangfkjdnsakjgfndskjgnjfdsgnfkjdsngjkfdsngkjfdsnkjgnfjdksngjfdsn
	//fndsiajnfkdjsangfjnkjvnfdhjkn fdkjn ngdsk mvkorwaj o9freajou rewaougjhr eagre+6s62g
	//3 reas5g.rdsl ngkrdsnb girebai gnfdsaij gnjidesa gkjetnjk gnrekjn jehgr eajo nrea4g2r e165g4re 61gdr akhgrsak nrskan fwirang7894yq9776597247563245wkj  
	//gnkfsb fdsg 4r4d65 g4rdan grsa4g rea6 gr,dsag 4rfdamg6fdsag nfd5agm fda4gmnjrf d4ag jfead5 gjfda gfram gf4dsajgm fd1ag nfda4 gfkda 4
	//finds red pixels, then makes their red value (0 - 255) the same as the ascii value for the letter in the message
	public Pixel[][] Stego() {
		int redNeg = 25;
		int Red;
		int Blue;
		int Green;
		int[] count;
		int count2;
		int check13;
		Pixel[][] pixels = this.getPixels2D();
		Pixel[][] redPixels = new Pixel[750][1000];
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[0].length - 1; col++) {
				Blue = pixels[row][col].getBlue();
				Red = pixels[row][col].getRed();
				Green = pixels[row][col].getGreen();
				if(Blue < Red - redNeg && Green < Red - redNeg){
					redPixels[row][col] = pixels[row][col];
				}
			}
		}
		Scanner scan = new Scanner(System.in);
		System.out.println("What would you like to encrypt?");
		String message = scan.nextLine();
		check13 = 0;
		count2 = 0;
		int countEnd = 0;
		count = toAscii(message);
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[0].length - 1; col++) {
					if(redPixels[row][col] != null){
						check13++;
					}
					if(check13 == 13 && countEnd <= message.length()){
						countEnd++;
						redPixels[row][col].setRed(count[count2]);
						check13 = 0;
						count2++;
						if(count2 >= count.length){
							count2 = 0;
						}
					}
			}
			// returns string for all red chars so use a charset to gett rid of the non a-z chars http://stackoverflow.com/questions/11497902/how-to-check-the-charset-of-string-in-java
		}
		return redPixels;
		
	}
	public int[] toAscii(String s){
        int[] sb = new int[s.length()];
        for (int i = 0; i < s.length(); i++){
            sb[i] = (int)s.charAt(i);
            System.out.println(sb[i] + " this is sb[i] from to Ascii");
        }
       
        return sb;
    }
	
	public String fromAscii(ArrayList<Integer> x){
		String newString = "";
		for(int i = 0; i < x.size(); i++){
			newString += ((char)(x.get(i).intValue()));
			if(i == x.size() - 1){
				newString += " end of String ";
			}
		}
		return newString;
	}
	
	public String stegoDecrypt(Pixel[][] redPixels){
		int count = 0;
		ArrayList<Integer> toString = new ArrayList<>();
		Pixel[][] pixels = this.getPixels2D();
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[0].length - 1; col++) {
				if(redPixels[row][col] != null){
					count++;
				}
				if(count == 13){
					toString.add(redPixels[row][col].getRed());
					count = 0;
				}
			}
		}
		char tester = 'Q';
		int count3 = 0;
		String fin = fromAscii(toString);
		for(int i = 0; i < fin.length()-1; i++){
			if(fin.charAt(i) == tester){
				for(int z = 0; z < 5; z++){
					if(fin.charAt(i + z) == tester){
						count3++;
					}
					if(count3 == 5){
						fin = fin.substring(0, i);
					}
				}
			}
		}
		return fin;
	}
	
	//above it stego 13th red
	//below is least significant bit
	/**
	 * makes the last byte in the thing change to the value we send it
	 * @param byt
	 * @return a byte
	 */
	
	public int changeByte(byte byt, char digit)
	{
		//for some reason our newString has a shit laod of 1's appended to the beginning so im genna use stringbuilder to remove them and make sure our length in 8
		StringBuilder sb = new StringBuilder();
		sb.append(Integer.toBinaryString(byt));
		System.out.println(Integer.toBinaryString(byt));
		if(sb.length() > 8){
			sb.delete(0, sb.length()-8);
		}
		else if(sb.length() < 8){
			int count1 = sb.length();
			while(count1 < 8){
				sb.insert(0, "0");
				count1++;
			}
		}
		/*sb1.append(Integer.toBinaryString(changedTo));
		if(sb1.length() > 8){
			sb1.delete(0, sb1.length()-8);
		}
		else if(sb1.length() < 8){
			int count1 = sb1.length();
			while(count1 < 8){
				sb1.insert(0, "0");
				count1++;
			}
		}*/
		System.out.println(sb + "    " + digit);
		System.out.println(sb + " this is sb");
		StringBuilder sbFinal = new StringBuilder();
		System.out.println(digit + " this is sb1");
		System.out.println(sb.substring(0, 7) + "     " + digit + "this is just before final");
		sbFinal.append(sb.substring(0, 7) + digit);
		System.out.println(sbFinal + " tgus is final");
		System.out.println(sbFinal.toString());
		
		int finalbyte = Integer.parseInt(sbFinal.toString(), 2); // now trun into a byte;
				//Byte.parseByte(sbFinal.toString(),2);
		System.out.println(finalbyte + " this is a byte from finalbyte");
		System.out.println(Integer.parseInt(sbFinal.toString(), 2) + " this is from finalbyte but an int");
		
		return finalbyte;
	}
	
	
	// 486x648
	public void leastSignificantBit(){
		int count = 0;
		Pixel[][] pixels = this.getPixels2D();
		Scanner scan = new Scanner(System.in);
		System.out.println("What would you like to encrypt");
		String encrpyt = scan.nextLine();
		int[] encInts = toAscii(encrpyt);
		byte[] encBytes = new byte[encInts.length];
		for(int i = 0; i < encInts.length; i++){
			encBytes[i] = (byte) encInts[i];
			//good
		}//1101000
		int count1 = 0;
		StringBuilder sb1 = new StringBuilder();
		OuterForLoop:
		for(int col = 0; col < pixels[0].length -1; col++){
			for(int row = 0; row < pixels.length; row++){
		
	
				sb1.append(0 + Integer.toBinaryString(encBytes[count]));
				String newsb1 = sb1.toString();
				char [] chars = newsb1.toCharArray();
			
				int byteNew = changeByte((byte) pixels[row][col].getBlue(), chars[count1]);
		
				pixels[row][col].setBlue(byteNew);
				count1++;
				sb1.delete(0, sb1.length());
				//clears our string builder so we can add to it without pilling up
				
				if(count1 > chars.length-1){
					count1 = 0;
					if(count >= encBytes.length-1){
						break OuterForLoop;
					}
					else{
						count++;
					}
				}
			}
		}
	}
	
	public String getLastBit(byte x){
		StringBuilder sb = new StringBuilder();
		sb.append(Integer.toBinaryString(x));
		System.out.println(sb);
		sb.delete(0, sb.length()-1);
		System.out.println(sb);
		System.out.println("This is sb: " + sb);
		return sb.toString();
		
	}
	public String leastDecrypter(){
		Pixel[][] pixels = this.getPixels2D();
		//the message in binary
		ArrayList<String> message = new ArrayList<>(); 
		//get bytes of blue then get thier last value and reconstruct message
		ArrayList<String> finalmessage = new ArrayList<>();
		ColFor:
		for(int col = 0; col < pixels[0].length -1; col++){
			for(int row = 0; row < pixels.length; row++){
				byte messageContainer = (byte) pixels[row][col].getBlue();
				message.add(getLastBit(messageContainer));
				//adds the binary numbers to a string list
			}
		}
		int count = 0;
		String done = null;
		for(int i = 0; i < message.size(); i++){
			//string to byte to int
			String letter = "";
			
			if(message.size() - i <= 7){
				System.out.println("we got to the end of the image");
			}
			else if(i == 0){
				for(int z = 0; z <= 7; z++){
					letter = letter + message.get(z);
					
					//so just run it and look at the values form that message for looop and the actual letter it representrs
				}
				for(int c = 0; c < 16; c++){
					System.out.print(message.get(c));
				}
				System.out.println();
				System.out.println(letter + " this is letter");
				int Ascii = Integer.parseInt(letter, 2);
				System.out.println((char)Ascii + " this is ascii");
				char actualLetter = (char) Ascii;
				finalmessage.add(Character.toString(actualLetter));
				count = -1;
				System.out.println(actualLetter + " this is actual letter");
			}
			else if(count == 7){
				for(int z = 0; z <= 7; z++){
					letter = letter + message.get(i + z);
					
					//so just run it and look at the values form that message for looop and the actual letter it representrs
				}
				for(int c = 0; c < 40; c++){
					System.out.print(message.get(c));
				}
				System.out.println();
				System.out.println(letter + " this is letter");
				int Ascii = Integer.parseInt(letter, 2);
				System.out.println((char)Ascii + " this is ascii");
				char actualLetter = (char) Ascii;
				finalmessage.add(Character.toString(actualLetter));
				count = -1;
				System.out.println(actualLetter + " this is actual letter");
				System.out.println();
			}	
			if(i > 40){
				break;
			}
			else{
				count++;
				System.out.println(count + " this is count" + " this is i " + i);
			}
		}
		System.out.println(" this is above done");
		
		done = "";
		for(int c = 0; c < finalmessage.size(); c++){
			if(c < 10){
				done = done + finalmessage.get(c);
			}
		}
		System.out.println(done + " This is done");
		System.out.println(finalmessage.get(0) + finalmessage.get(1) + finalmessage.get(2) + finalmessage.get(3) + finalmessage.get(4)+ " This is finalmessage");
		return done;
	}
	
	
	
}
