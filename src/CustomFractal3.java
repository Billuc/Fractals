import java.util.ArrayList;
import java.util.List;

public class CustomFractal3 extends Fractal {

	public CustomFractal3() {
		points.add(new Point(100,500));
		points.add(new Point(900,500));
	}

	@Override
	public List<Point> generatePoints(Point p1, Point p2) {
		Point m1 = p1.moveToward(p2, 1/4d);
		Point m2 = p1.moveToward(p2, 1/2d);
		Point m3 = p1.moveToward(p2, 1/2d);
		Point m4 = p1.moveToward(p2, 3/4d);
		
		double[] vect = p1.perpDir(p2);
		vect[0] *= 1/4d;
		vect[1] *= 1/4d;
		
		m1.move(vect[0], vect[1]);
		m2.move(vect[0], vect[1]);
		m3.move(-vect[0], -vect[1]);
		m4.move(-vect[0], -vect[1]);
		
		List<Point> newPoints = new ArrayList<>(4);
		newPoints.add(p1.moveToward(p2, 1/4d));
		newPoints.add(m1);
		newPoints.add(m2);
		newPoints.add(m3);
		newPoints.add(m4);
		newPoints.add(p1.moveToward(p2, 3/4d));
		
		return newPoints;
	}

}
