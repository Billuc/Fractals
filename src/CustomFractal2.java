import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class CustomFractal2 extends Fractal {

	static boolean calculated = false;
	static CustomFractal2 cf2;
	
	public CustomFractal2() {
		points.add(new Point(100,500));
		points.add(new Point(900,500));
	}
	
	public static void draw(Graphics g, int width, int height, int iterations) {
		if (!calculated) {
			cf2 = new CustomFractal2();
			cf2.doIterations(iterations);
			calculated = true;
		}

		cf2.paint(g, width, height);
	}

	@Override
	public List<Point> generatePoints(Point p, Point nextP) {
		Point m1 = p.moveToward(nextP, 1/3d);
		Point m2 = p.moveToward(nextP, 2/3d);
		
		double[] vect = p.perpDir(nextP);
		vect[0] *= 1/4d;
		vect[1] *= 1/4d;
		
		m1.move(vect[0], vect[1]);
		m2.move(-vect[0], -vect[1]);
		
		List<Point> newPoints = new ArrayList<>(4);
		newPoints.add(m1);
		newPoints.add(m2);
		
		return newPoints;
	}

}
