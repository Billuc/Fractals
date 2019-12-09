import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public abstract class Fractal {

	List<Point> points = new ArrayList<Point>();

	class Point{
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public Point moveToward(Point p, double factor) {
            double vectX = p.x - this.x;
            double vectY = p.y - this.y;
            vectX *= factor;
            vectY *= factor;
            return new Point(this.x+vectX, this.y+vectY);
        }

        public void move(double dx, double dy) {
            x += dx;
            y += dy;
        }

        public Point createAndMove(double dx, double dy) {
            Point p = new Point(x, y);
            p.move(dx, dy);
            return p;
        }

        public double[] perpDir(Point p) {
            double vectX = -(this.y - p.y);
            double vectY = -(p.x - this.x);

            return new double[] {vectX, vectY};
        }
    }

	public Fractal() {}

	public void doIterations(int nbIter) {
		for (int i = 0; i < nbIter; i++) {
			this.iterate();
		}
	}

	public final void iterate() {
		List<Point> newPoints = new ArrayList<>();

		Point p = points.get(0);
		Point nextP = null;

		for (int i = 0; i < points.size()-1; i++) {
			nextP = points.get(i+1);

			newPoints.add(p);

			newPoints.addAll(generatePoints(p, nextP));

			p = nextP;
		}

		newPoints.add(nextP);

		points = newPoints;
	}



	public void paint(Graphics g, int width, int height) {
		Point p = this.points.get(0);
		Point nextP = null;

		int mindim = Math.min(width, height);
		int left = (width-mindim)/2;
		int top = (height-mindim)/2;

		for(int i = 0; i < this.points.size()-1; i++) {
			nextP = this.points.get(i+1);

			g.drawLine(left + (int)(mindim*p.x/1000), top + (int)(mindim * p.y/1000), left + (int)(mindim * nextP.x/1000), top + (int)(mindim * nextP.y/1000));

			p = nextP;
		}
	}

	public abstract List<Point> generatePoints(Point p1, Point p2);
}
