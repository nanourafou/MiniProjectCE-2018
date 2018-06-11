package elements;

import primitives.Point3D;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource{

    private Vector _direction; // Direction



    /**
     * The Ctor
     * @param c The color of the light
     * @param _direction The Direction vector
     */
    public DirectionalLight(Color c, Vector _direction){
        super(c);
        this._direction = new Vector(_direction).normalize();
    }

    /**
     * Copy Ctor
     * @param l The directionnal Light to copy
     */
    public DirectionalLight(DirectionalLight l){
        super(l);
        this._direction = new Vector(l._direction);
    }

    /**
     * @return Directionnal vector
     */
    public Vector getDirection() {
        return _direction;
    }

    @Override
    public Color getIntensity(Point3D p) {
        return new Color(super.getIntensity());
    }

    @Override
    public Vector getL(Point3D p) {
        return _direction.normalize();
    }

    @Override
    public Vector getD(Point3D p) {
        return getL(p);
    }
}
