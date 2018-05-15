package elements;

import primitives.Point3D;
import primitives.Vector;

public interface LightSource {

    Color getIntensity(Point3D p);
    Vector getL(Point3D p);
    Vector getD(Point3D p);

}
