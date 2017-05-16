package main;

import java.awt.Rectangle;

public class GUI {
	
	int mode;
	Button[][] buttons= {
			{}
	};
	
	public GUI(){
		
	}
	
	public void setmode(int m){
		mode = m;
	}
	
	public class Button{
		int id;
		String name;
		ImgSheet img;
		int width = 16;
		int height = 16;
		Rectangle bounds;
		
		public Button(String bname, int triggerid, ImgSheet images){
			name = bname;
			id = triggerid;
			img = images;
			bounds = new Rectangle(0,0,16,16);
		}
	}
	
}
