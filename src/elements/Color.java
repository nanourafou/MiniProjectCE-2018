package elements;

public class Color {

    private java.awt.Color _color;


    /**
     * Constructor for a color
     * @param c
     */
    public Color(java.awt.Color c ){
        _color = c;
    }

    /**
     * Constructor for a color
     * @param c
     */
    public Color(Color c){
        _color = new java.awt.Color(c._color.getRGB());
    }

    /**
     * Constructor for a color
     * @param r Red
     * @param g Green
     * @param b Blue
     */
    public Color(float r, float g, float b){
        _color = new java.awt.Color(r,g,b);
    }

    /**
     * @param c Color to add to our color.
     */
    public void add(java.awt.Color c){
        int r = _color.getRed() +c.getRed();
        int g = _color.getGreen() +c.getGreen();
        int b = _color.getBlue() +c.getBlue();

        if(r>255 || g>255 || b>255 ||r<0 || g<0 || b<0 )
            throw new IllegalArgumentException();

        _color = new java.awt.Color(r,g,b);
    }

    /**
     * @param s Mult the color by a double
     */
    public void scale(double s){
        double r = _color.getRed() *s;
        double g = _color.getGreen() *s;
        double b = _color.getBlue() *s;

        if(r>255 || g>255 || b>255 ||r<0 || g<0 || b<0 )
            throw new IllegalArgumentException();

        _color = new java.awt.Color((float) r,(float)g,(float)b);
    }

    /**
     * Reduce the color By a double
     * @param r The double operand
     */
    public void reduce(double r){
        double red = _color.getRed() -r;
        double g = _color.getGreen() -r;
        double b = _color.getBlue() -r;
        if(r>255 || g>255 || b>255 ||r<0 || g<0 || b<0 )
            throw new IllegalArgumentException();

        _color = new java.awt.Color((float) red,(float)g,(float)b);


    }


    /**
     * @return Getters the color
     */
    public java.awt.Color getColor() {
        return _color;
    }


}
