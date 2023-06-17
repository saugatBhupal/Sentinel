package utils.ui.graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedLabel extends JLabel {
    private final int radius;
    private final Color shadowColor;
    private final int shadowSize;

    public RoundedLabel(String text, int radius, Color shadowColor, int shadowSize) {
        super(text);
        this.radius = radius;
        this.shadowColor = shadowColor;
        this.shadowSize = shadowSize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int shadowX = shadowSize;
        int shadowY = shadowSize;
        int shadowWidth = width - 2 + shadowSize;
        int shadowHeight = height - 2 + shadowSize;

        Shape shadowShape = new RoundRectangle2D.Double(shadowX, shadowY, shadowWidth, shadowHeight, radius, radius);

        Color shadowStartColor = new Color(shadowColor.getRed(), shadowColor.getGreen(), shadowColor.getBlue(), 100);
        Color shadowEndColor = new Color(shadowColor.getRed(), shadowColor.getGreen(), shadowColor.getBlue(), 0);
        RadialGradientPaint shadowGradient = new RadialGradientPaint(
                new Point(shadowX + shadowWidth / 2, shadowY + shadowHeight / 2),
                Math.max(shadowWidth, shadowHeight) / 2f,
                new float[]{0.0f, 1.0f},
                new Color[]{shadowStartColor, shadowEndColor}
        );

        g2d.setPaint(shadowGradient);
        g2d.fill(shadowShape);

        Shape roundedRect = new RoundRectangle2D.Double(0, 0, width - 1, height - 1, radius, radius);

        g2d.clip(roundedRect);

        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, width, height);

        super.paintComponent(g2d);

        g2d.dispose();
    }
}
