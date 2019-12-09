import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Von_Koch_Snowflake extends Fractal {
	
	static boolean calculated = false;
	static Von_Koch_Snowflake vks;

	public Von_Koch_Snowflake() {
		double width = 1000;
		double height = 1000;
		
		double c = 8*width/10;
		double h1 = (1/2.) * (height - (Math.sqrt(3)/3)*c);
		double h2 = (1/2.) * (height + (2*Math.sqrt(3)/3)*c);
		
		points.add(new Point(width/10, h1));
		points.add(new Point(9*width/10, h1));
		points.add(new Point(width/2, h2));
		points.add(new Point(width/10, h1));
	}
	
	public static void draw(Graphics g, int width, int height, int iterations) {
		if (!calculated) {
			vks = new Von_Koch_Snowflake();
			vks.doIterations(iterations);
			calculated = true;
		}

		vks.paint(g, width, height);
	}
	
	public List<Point> generatePoints(Point p1, Point p2) {
		List<Point> newPoints = new ArrayList<Fractal.Point>(3);
		newPoints.add(p1.moveToward(p2, 1/3d));
		
		Point m = p1.moveToward(p2, 1/2d);
		double[] vect = p1.perpDir(p2);
		vect[0] *= Math.sqrt(3)/6;
		vect[1] *= Math.sqrt(3)/6;
		m.move(vect[0], vect[1]);
		newPoints.add(m);

		newPoints.add(p1.moveToward(p2, 2/3d));
		
		return newPoints;
	}
}
