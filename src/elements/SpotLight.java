package elements;

import primitives.Point3D;
import primitives.Vector;

public class SpotLight extends PointLight {

    protected Vector _direction;

    /**
     * The constructor
     * @param c The color of the vector
     * @param p The position of the light
     * @param kc
     * @param kl
     * @param kq
     * @param direction The direction vector
     */
    public SpotLight(Color c, Point3D p, double kc, double kl, double kq, Vector direction) {
        super(c, p, kc, kl, kq);
        this._direction = direction.normalize();
    }

    /**
     * Copy Ctor
     * @param p The pointLight to copy
     */
    public SpotLight(SpotLight p) {
        super(p);
        this._direction = p._direction.normalize();
    }


    /**
     * @return Direction vector
     */
    public Vector getDirection() {
        return _direction;
    }

    @Override
    public Color getIntensity(Point3D p) {
        Color c = super.getIntensity(p);
        Vector l = getL(p);
        c.scale(_direction.dotProduct(l));
        return c;
    }
}
