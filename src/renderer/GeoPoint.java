package renderer;

import geometries.Geometry;
import primitives.Point3D;

public class GeoPoint {

    private Point3D _point;
    private Geometry _geometry;

    public GeoPoint(Point3D p, Geometry g){
        this._geometry = g;
        this._point = new Point3D(p);
    }

    public GeoPoint(GeoPoint geo){
        this._point = new Point3D(geo._point);
        this._geometry = geo._geometry;
    }

    public Geometry getGeometry() {
        return _geometry;
    }

    public Point3D getPoint() {
        return _point;
    }
}
