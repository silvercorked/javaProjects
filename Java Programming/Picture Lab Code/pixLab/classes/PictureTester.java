/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVertical();
    caterpillar.explore();
  }
  
  public static void testMirrorVerticalRightToLeft()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVerticalRightToLeft();
    caterpillar.explore();
  }
  public static void testMirrorHorizontal(){
	  Picture caterpillar = new Picture("caterpillar.jpg");
	   caterpillar.explore();
	   caterpillar.mirrorHorizontal();
	   caterpillar.explore();
  }
  public static void testMirrorHorizontalBotToTop(){
	  Picture caterpillar = new Picture("caterpillar.jpg");
	   caterpillar.explore();
	   caterpillar.mirrorHorizontalBotToTop();
	   caterpillar.explore();
  }
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("temple.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
  }
  public static void testMirrorArms()
  {
    Picture temple = new Picture("snowman.jpg");
    temple.explore();
    temple.mirrorArms();
    temple.explore();
  }
  
  public static void testMirrorGull()
  {
    Picture temple = new Picture("seagull.jpg");
    temple.explore();
    temple.mirrorGull();
    temple.explore();
  }
  /** Method to test the collage method */
  public static void testCollage()
  {
    Picture canvas = new Picture("640x480.jpg");
    canvas.myCollage();
    canvas.explore();
  }
  
  public static void testCopy(){
	  Picture canvas = new Picture("640x480.jpg");
	  Picture canvas2 = new Picture("beach.jpg");
	    canvas.copy2(canvas2,79,52,53,300,300,300);
	    canvas.explore();
  }
  /** Method to test edgeDetection */
  public static void testEdgeDetection()
  {
    Picture swan = new Picture("swan.jpg");
    swan.edgeDetection(10);
    swan.explore();
  }
  
  public static void testEdgeDetection2()
  {
    Picture swan = new Picture("swan.jpg");
    swan.explore();
    swan.edgeDetection2(20);
    swan.explore();
  }
  /**
   * method to test keeponlyblue
   */
  public static void testKeepOnlyBlue()
  {
	  Picture dank = new Picture("beach.jpg");
	  dank.keepOnlyBlue();
	  dank.explore();
  }
  /**
   * method to test negate
   */
  public static void testNegate()
  {
	  Picture dank = new Picture("beach.jpg");
	  dank.negate();
	  dank.explore();
  }
  public static void testGrayscale()
  {
	  Picture dank = new Picture("beach.jpg");
	  dank.grayscale();
	  dank.explore();
  }
  public static void testFixUnderwater()
  {
	  Picture dank = new Picture("water.jpg");
	  dank.fixUnderWater();
	  dank.explore();
  }
  public static void testChromakey()
	{
	  Picture green2 = new Picture("green2.JPG");
	  Picture greenRED = new Picture("greenRED.JPG");
	  Picture beach = new Picture("beach.jpg");
	  Picture temple = new Picture("temple.jpg");
	  green2.explore();
	  //green2.Chromakey(beach, temple);
	  //makes background part of both temple and beach
	  //green2.Chromakey();
	  //makes image background red
	  greenRED.Chromakey(temple);
	  //makes the image process witha  red background
	  greenRED.explore();
	  //green2.explore();
	  //green picture is too damn big
	}
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
    //testZeroBlue();
    //testKeepOnlyBlue();
    //testKeepOnlyRed();
    //testKeepOnlyGreen();
    //testNegate();
    //testGrayscale();
    //testFixUnderwater();
    //testMirrorVertical();
    //testMirrorVerticalRightToLeft();
	//testMirrorHorizontal();
	//testMirrorHorizontalBotToTop();
    //testMirrorTemple();
    //testMirrorArms();
    //testMirrorGull();
    //testMirrorDiagonal();
    //testCollage();
    //testCopy();
    //testEdgeDetection();
    //testEdgeDetection2();
    testChromakey();
    //testEncodeAndDecode();
    //testGetCountRedOverValue(250);
    //testSetRedToHalfValueInTopHalf();
    //testClearBlueOverValue(200);
    //testGetAverageForColumn(0);
  }

private static void testMirrorDiagonal() {
	Picture caterpillar = new Picture("beach.jpg");
    caterpillar.explore();
    caterpillar.mirrorDiagonal();
    caterpillar.explore();
	
}
	


}