package stegosaurus;

public class Runner {

	public static void main(String[] args) {
		Picture stego = new Picture("H:/Java Programming/StegoSaurus/StegoMyLegoStegosaurus/src/images/femaleLionAndHall.jpg");
		//stego.explore();
		//System.out.println(stego.stegoDecrypt(stego.Stego()));
		stego.explore();
		stego.leastSignificantBit();
		stego.explore();
		String finalAnswer = (stego.leastDecrypter());
		for(int i = 0; i < 10; i++){
			System.out.println(finalAnswer);
		}
	}

}
