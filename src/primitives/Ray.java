package primitives;


/**
 * Describe a Ray form in space.
 */
public class Ray {

    private Point3D _origin;
    private Vector _direction;

    // ***************** Constructors ********************** //

    /**
     * Construct a Ray object with a 3D point and Vector.
     */
    public Ray(Point3D origin, Vector direction) {
        _origin = new Point3D(origin);
        _direction = new Vector(direction).normalize();
    }

    /**
     * Construct a Ray object with two 3D points.
     */
    public Ray(Point3D origin, Point3D headVector) {
        _origin = new Point3D(origin);
        _direction = new Vector(headVector).normalize();
    }


    /**
     * Copy Constructor
     */
    public Ray(Ray rayObj) {
        _origin = new Point3D(rayObj._origin);
        _direction = new Vector(rayObj._direction);
    }

    // ***************** Getters/Setters ********************** //

    /**
     * @return The origin point.
     */
    public Point3D getOrigin() {
        return _origin;
    }

    /**
     * @return The direction vector.
     */
    public Vector getDirection() {
        return _direction.normalize();
    }

    // ***************** Administration  ******************** //
    @Override
    public String toString() {
        return "Origin " + _origin + " " + _direction;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray))
            return false;
        Ray objToCompare = (Ray) obj;
        return objToCompare._origin.equals(this._origin) && objToCompare._direction.equals(this._direction);

    }

}
