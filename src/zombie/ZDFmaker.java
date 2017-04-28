package zombie;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ZDFmaker {
	
	int[] data;
	String outpath = "C:\\Users\\Emmett\\Desktop\\source - texture\\zimages";
	String name;
	String inpath;
	
	public static void main(String[] args) {
		new ZDFmaker();
	}
	
	public ZDFmaker(){
		save();
	}
	
	public void save(){
		BufferedImage b = null;
		try{
			b = ImageIO.read(this.getClass().getResource("/zimages/bones0.png"));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		File f = new File(outpath+"\\peen.png");
		try{
			ImageIO.write(b, "PNG", f);
		}catch(Exception ex){}
	}
}
