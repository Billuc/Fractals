import java.util.ArrayList;
import java.util.List;

public class Sierpensky_Triangle extends Fractal {

	public Sierpensky_Triangle() {
		points.add(new Point(100, 800));
		points.add(new Point(900, 800));
	}

	@Override
	public List<Point> generatePoints(Point p1, Point p2) {
		List<Point> newPoints = new ArrayList<Fractal.Point>();
		
		Point m1 = p1.moveToward(p2, 1/2d);
		newPoints.add(m1);
		
		double[] vect = p1.perpDir(p2);
		vect[0] *= Math.sqrt(3)/4;
		vect[1] *= Math.sqrt(3)/4;
		
		Point m2 = p1.moveToward(p2, 1/4d);
		m2.move(vect[0], vect[1]);
		newPoints.add(m2);
		
		Point m3 = p1.moveToward(p2, 3/4d);
		m3.move(vect[0], vect[1]);
		newPoints.add(m3);
		
		Point m4 = p1.moveToward(p2, 1/2d);
		newPoints.add(m4);
		
		return newPoints;
	}

}
