package elements;

import primitives.Point3D;
import primitives.Vector;


public interface LightSource {

    /**
     * Get intensity of the light
     *
     * @param p Point
     * @return
     */
    Color getIntensity(Point3D p);

    /**
     * Get Vector Light
     *
     * @param p Point
     * @return
     */
    Vector getL(Point3D p);

    /**
     * @param p
     * @return
     */
    Vector getD(Point3D p);

}
