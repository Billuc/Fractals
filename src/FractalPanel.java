import javax.swing.*;
import java.awt.*;

public class FractalPanel extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public Fractal f;

    public FractalPanel(Fractal theFractal) {
        super();
        f = theFractal;
    }

    @Override
    protected void paintComponent(Graphics g) {
        f.paint(g, this.getWidth(), this.getHeight());
    }
}
