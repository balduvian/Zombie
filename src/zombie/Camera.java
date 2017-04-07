package zombie;

public class Camera {
	
	double cx;
	double cy;
	Entity fol;
	int suit;
	boolean mov = false;
	double[] destin;
	double tleft;
	
	public Camera(int f){
		
	}
	
	public Camera(double nx, double ny){
		
	}
	
	public void tick(){
		if(mov){
			if(cx != destin[1] && cy != destin[0]){
				
			}
		}
	}
	
	public void travel(double y, double x, double tim){
		mov = false;
		destin = new double[]{y,x};
		tleft = tim;
	}
}
