import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class CustomFractal1 extends Fractal {
	
	static boolean calculated = false;
	static CustomFractal1 cf1;

	public CustomFractal1() {
		points.add(new Point(334,334));
		points.add(new Point(666,334));
		points.add(new Point(666,666));
		points.add(new Point(334,666));
		points.add(new Point(334,334));
	}
	
	public static void draw(Graphics g, int width, int height, int iterations) {
		if (!calculated) {
			cf1 = new CustomFractal1();
			cf1.doIterations(iterations);
			calculated = true;
		}

		cf1.paint(g, width, height);
	}

	public List<Point> generatePoints(Point p, Point nextP) {
		Point m1 = p.moveToward(nextP, 1/3d);
		Point m2 = p.moveToward(nextP, 2/3d);
		
		double[] vect = p.perpDir(nextP);
		vect[0] *= 1/3d;
		vect[1] *= 1/3d;
		
		m1.move(vect[0], vect[1]);
		m2.move(vect[0], vect[1]);
		
		List<Point> newPoints = new ArrayList<>(4);
		newPoints.add(p.moveToward(nextP, 1/3d));
		newPoints.add(m1);
		newPoints.add(m2);
		newPoints.add(p.moveToward(nextP, 2/3d));
		
		return newPoints;
	}

	
}
