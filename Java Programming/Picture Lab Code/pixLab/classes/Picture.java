import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

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

}
