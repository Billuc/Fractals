import java.util.ArrayList;
import java.util.List;

public class CustomFractal5 extends Fractal {
	
	public CustomFractal5() {
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

	@Override
	public List<Point> generatePoints(Point p1, Point p2) {
		List<Point> newPoints = new ArrayList<Fractal.Point>(3);
		newPoints.add(p1.moveToward(p2, 1/4d));
		
		Point m1 = p1.moveToward(p2, 3/8d);
		double[] vect = p1.perpDir(p2);
		vect[0] *= Math.sqrt(3)/8;
		vect[1] *= Math.sqrt(3)/8;
		m1.move(vect[0], vect[1]);
		newPoints.add(m1);
		
		newPoints.add(p1.moveToward(p2, 1/2d));
		
		Point m2 = p1.moveToward(p2, 5/8d);
		m2.move(vect[0], vect[1]);
		newPoints.add(m2);

		newPoints.add(p1.moveToward(p2, 3/4d));
		
		return newPoints;
	}

}
