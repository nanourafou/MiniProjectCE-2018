package elements;

import primitives.Point3D;
import primitives.Vector;

public class PointLight extends Light implements LightSource {

    protected Point3D _position;
    protected double _Kc, _Kl, _Kq;


    /**
     * The constructor
     *
     * @param c  The Color of the light
     * @param p  The position of the light
     * @param kc
     * @param kl
     * @param kq
     */
    public PointLight(Color c, Point3D p, double kc, double kl, double kq) {
        super(c);
        this._position = new Point3D(p);
        this._Kc = kc;
        this._Kl = kl;
        this._Kq = kq;
    }

    /**
     * Copy Ctor
     *
     * @param p The PointLight to copy
     */
    public PointLight(PointLight p) {
        super(p);
        this._position = new Point3D(p._position);
        this._Kc = p._Kc;
        this._Kl = p._Kl;
        this._Kq = p._Kq;
    }

    /**
     * @return kc
     */
    public double getKc() {
        return _Kc;
    }

    /**
     * @return kl
     */
    public double getKl() {
        return _Kl;
    }

    /**
     * @return kq
     */
    public double getKq() {
        return _Kq;
    }

    /**
     * @return position
     */
    public Point3D getPosition() {
        return _position;
    }

    @Override
    public Color getIntensity(Point3D p) {
        Color c = new Color(getIntensity());
        double d = _position.distance(p);
        double k = 1 / (_Kc + _Kl * d + _Kq * d * d);
        return c.scale(k);
    }


    @Override
    public Vector getD(Point3D p) {
        return getL(p);
    }

    @Override
    public Vector getL(Point3D p) {
        return p.subVector(_position).normalize();
    }
}