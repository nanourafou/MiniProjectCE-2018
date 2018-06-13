package elements;

public class Color {

    private java.awt.Color _color;


    /**
     * Constructor for a color
     *
     * @param c
     */
    public Color(java.awt.Color c) {
        _color = new java.awt.Color(c.getRGB());
    }

    /**
     * Constructor for a color
     *
     * @param c
     */
    public Color(Color c) {
        _color = new java.awt.Color(c._color.getRGB());
    }

    /**
     * Constructor for a color
     *
     * @param r Red
     * @param g Green
     * @param b Blue
     */
    public Color(double r, double g, double b) {
        if (r < 0)
            r = 0;
        if (r > 255)
            r = 255;
        if (g < 0)
            g = 0;
        if (g > 255)
            g = 255;
        if (b < 0)
            b = 0;
        if (b > 255)
            b = 255;

        _color = new java.awt.Color((int) r, (int) g, (int) b);
    }

    /**
     * @param colors Color to add to our color.
     */
    public Color add(Color... colors) {
        int r = _color.getRed();
        int g = _color.getGreen();
        int b = _color.getBlue();
        for (Color c : colors) {
            if (c == null)
                continue;
            if (c._color.getRed() < 0 || c._color.getGreen() < 0 || c._color.getBlue() < 0) {
                double bb = 0.11;
            }
            r += c._color.getRed();
            g += c._color.getGreen();
            b += c._color.getBlue();
        }
        if (b < 0)
            b = 0;
        if (b > 255)
            b = 255;
        if (g < 0)
            g = 0;
        if (g > 255)
            g = 255;
        if (r < 0)
            r = 0;
        if (r > 255)
            r = 255;
        _color = new java.awt.Color((int) r, (int) g, (int) b);

        return this;
    }

    /**
     * @param s Mult the color by a double
     */
    public Color scale(double s) {
        double r = _color.getRed() * s;
        double g = _color.getGreen() * s;
        double b = _color.getBlue() * s;

        if (r < 0)
            r = 0;
        if (r > 255)
            r = 255;
        if (g < 0)
            g = 0;
        if (g > 255)
            g = 255;
        if (b < 0)
            b = 0;
        if (b > 255)
            b = 255;

        _color = new java.awt.Color((int) r, (int) g, (int) b);

        return this;
    }

    /**
     * Reduce the color By a double
     *
     * @param r The double operand
     */
    public Color reduce(double r) {
        double red = _color.getRed() - r;
        double g = _color.getGreen() - r;
        double b = _color.getBlue() - r;
        if (red < 0)
            r = 0;
        if (red > 255)
            r = 255;
        if (g < 0)
            g = 0;
        if (g > 255)
            g = 255;
        if (b < 0)
            b = 0;
        if (b > 255)
            b = 255;

        _color = new java.awt.Color((int) red, (int) g, (int) b);

        return this;
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Color) && obj == null)
            return false;
        Color c = (Color) obj;
        return _color.getBlue() == c._color.getBlue() && _color.getGreen() == c._color.getGreen() && _color.getRed() == c._color.getRed();
    }

    /**
     * @return Getters the color
     */
    public java.awt.Color getColor() {
        return _color;
    }


}
