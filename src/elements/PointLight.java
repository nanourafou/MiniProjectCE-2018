package elements;

import primitives.Point3D;
import primitives.Vector;

public class PointLight extends Light  implements LightSource{

    protected Point3D _position;
    protected double _Kc,_Kl,_Kq;


    /**
     * The constructor
     * @param c The Color of the light
     * @param p The position of the light
     * @param kc
     * @param kl
     * @param kq
     */
    public PointLight(Color c, Point3D p, double kc, double kl, double kq){
        super(c);
        this._position = new Point3D(p);
        this._Kc = kc;
        this._Kl = kl;
        this._Kq = kq;
    }

    /**
     * Copy Ctor
     * @param p The PointLight to copy
     */
    public PointLight(PointLight p){
        super(p);
        this._position = new Point3D(p._position);
        this._Kc = p._Kc;
        this._Kl = p._Kl;
        this._Kq = p._Kq;
    }

    public double getKc() {
        return _Kc;
    }

    public double getKl() {
        return _Kl;
    }

    public double getKq() {
        return _Kq;
    }

    public Point3D getPosition() {
        return _position;
    }

    @Override
    public Color getIntensity() {
        return null;
    }

    @Override
    public Color getIntensity(Point3D p) {
        return null;
    }

    @Override
    public Vector getL(Point3D p) {
        return null;
    }

    @Override
    public Vector getD(Point3D p) {
        return null;
    }
}