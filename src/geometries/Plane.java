package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * The class describing a Plane in space.
 */
public class Plane extends Geometry {

    private Point3D _p;
    private Vector  _vector1;
    private Vector  _vector2;

    // ***************** Constructors ********************** //

    /**
     * Constructor for a plane.
     * @param vector The vector director (vector normal deduced).
     * @param point The point who will be on the plane.
     */
    public Plane(Vector vector, Point3D point){
        this._vector1 = new Vector(vector);
        this._vector2 = _vector1.getNormalVector();
        this._p = new Point3D(point);
    }

    /**
     * Constructor for a plane.
     * @param v1 The vector director (vector normal deduced).
     * @param v2 The vector director (need to not be colinear with v1).
     * @param p The point who will be on the plane.
     */
    public Plane(Vector v1, Vector v2, Point3D p){
        //if(Vector.crossProdcut(v1,v2).size()==0)
            //throw new Exception("Can't define a plan with two colinear vectors");
        if(!v1.isColinear(v2)) // if n
        {
            _vector1 = new Vector(v1);
            _vector2 = new Vector(v2);
            _p = new Point3D(p);
        }
    }

    public Plane(Point3D x, Point3D y, Point3D z){
        Ray xy = new Ray(x,y);
        Ray xz = new Ray(x,z);
        Ray yz = new Ray(y,z);
        _vector1=xy.getDirection();
        _vector2=xz.getDirection();
        
        if(_vector1.isColinear(_vector2)){
            if (yz.getDirection().isColinear(_vector2))
                throw new Exception("Can't create a colinear plane");
        }



    }

    /**
     * Copy Constructor
     */
    public Plane(Plane plane){
        this._vector1 = new Vector(plane._vector1);
        this._vector2 = new Vector(plane._vector2);
        this._p = new Point3D(plane._p);
    }


    // ***************** Administration  ******************** //
    @Override
    public String toString() {
        return "P = { "+_p.toString()+" k*"+_vector1+" s*"+_vector2+" | k,s ∈ ℝ }";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if(!(obj instanceof Plane))
            return false;
        Plane toCompare = (Plane)obj;

        if(_vector1.equals(toCompare._vector2))//Multiple values checking
            return this._p.equals(toCompare._p)&&this._vector2.equals(toCompare._vector1);
        if(this._vector2.equals(toCompare._vector1))
            return this._p.equals(toCompare._p)&&this._vector1.equals(toCompare._vector2);

        return _vector1.equals(toCompare._vector1)&&_vector2.equals(toCompare._vector2)&& _p.equals(toCompare._p);

    }

    // ***************** Getters/Setters ********************** //

    /**
     * @return The first director vector.
     */
    public Vector getVector1() {
        return _vector1;
    }

    /**
     * @return The second vector.
     */
    public Vector getVector2() {
        return _vector2;
    }

    /**
     * @return The point on the plane.
     */
    public Point3D getPoint() {
        return _p;
    }

    // ***************** Operations ******************** //

    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }
}
