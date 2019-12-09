import java.util.ArrayList;
import java.util.List;

public class CustomFractal4 extends Fractal {

	public CustomFractal4() {
//		double width = 1000;
//		double height = 1000;
//		
//		double c = 4*width/10;
//		double h1 = (1/2.) * (height - (Math.sqrt(3)/3)*c);
//		double h2 = (1/2.) * (height + (2*Math.sqrt(3)/3)*c);
//		
//		points.add(new Point(3*width/10, h1));
//		points.add(new Point(7*width/10, h1));
//		points.add(new Point(width/2, h2));
//		points.add(new Point(3*width/10, h1));
		
		
		int h1 = 334;
		int h2 = 500;
		int h3 = 666;
		
		int demiC = (int)(1000*Math.sqrt(3)/18);
		
		points.add(new Point(500-demiC, h1));
		points.add(new Point(500+demiC, h1));
		points.add(new Point(500+2*demiC, h2));
		points.add(new Point(500+demiC, h3));
		points.add(new Point(500-demiC, h3));
		points.add(new Point(500-2*demiC, h2));
		points.add(new Point(500-demiC, h1));
	}

	@Override
	public List<Point> generatePoints(Point p1, Point p2) {
		List<Point> newPoints = new ArrayList<Fractal.Point>();
		
		Point np1 = p1.moveToward(p2, 1/3d);
		newPoints.add(np1);
		
		Point m = p1.moveToward(p2, 1/6d);
		double[] vect = p1.perpDir(p2);
		vect[0] *= Math.sqrt(3)/6;
		vect[1] *= Math.sqrt(3)/6;
		
		newPoints.add(m.createAndMove(vect[0], vect[1]));
		newPoints.add(np1.createAndMove(2*vect[0], 2*vect[1]));

		Point np2 = p1.moveToward(p2, 2/3d);
		newPoints.add(np2.createAndMove(2*vect[0], 2*vect[1]));
		
		Point n = p1.moveToward(p2, 5/6d);
		newPoints.add(n.createAndMove(vect[0], vect[1]));
		
		newPoints.add(np2);
		
		return newPoints;
	}

}
