package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * The class describing a Triangle in space.
 */
public class Triangle extends Geometry {
    /***
     * Fields
     */
    private Point3D _p1;
    private Point3D _p2;
    private Point3D _p3;

    // ***************** Constructors ********************** //
    /***
     * The Ctor of this class.
     * @param p1 The first point of the triangle.
     * @param p2 The second point of the triangle.
     * @param p3 the third point of the triangle.
     */
    public Triangle(Point3D p1,Point3D p2,Point3D p3) {
        super(); //p1==p2==p3 still triangle  ???
        _p1 = new Point3D(p1);
        _p2 = new Point3D(p2);
        _p3 = new Point3D(p3);
    }

    /***
     *The copy constructor of this class.
     */
    public Triangle(Triangle obj){
        _p1=new Point3D(obj._p1);
        _p2=new Point3D(obj._p2);
        _p3=new Point3D(obj._p3);
    }
// ***************** Getters/Setters ********************** //
    /***
     * The function return the first point of the triangle.
     */
    public Point3D getPoint1(){
        return _p1;
    }

    /***
     *The function return the second point of the triangle.
     */
    public Point3D getPoint2(){
        return _p2;
    }

    // ***************** Administration  ******************** //
    /***
     *The function return the third point of the triangle.
     */
    public Point3D getPoint3(){
        return _p3;
    }
    @Override
    public String toString (){
        return "Triangle: \n "+"1: "+ _p1+","+"\n 2: "+ _p2+","+"\n 3: "+ _p3;
    }

    /**
     * Helper function to check equality with a multiple value in the triangle.
     * @param x The 3d point to verify.
     * @param valueToVerify Array of all the values to verify.
     * @param valueChecked Vector contains all values checked.
     * @return True: if value was equals in a value in the array valueToverify.
     */
    private boolean testMultiplevalues(Point3D x, Point3D[] valueToVerify, java.util.Vector valueChecked){

        for (int i=0;i<3;i++)
        {
            if(!valueChecked.contains(i)) //if the value was checked don't check
            {
                if(x.equals(valueToVerify[i])) //If equals
                {
                    valueChecked.add(i); //Add the index
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Triangle))
            return false;
        if (this == obj) return true;
        if (obj == null) return false;

        Point3D [] arrayToCheck = new Point3D[]{((Triangle) obj)._p1,((Triangle) obj)._p2,((Triangle) obj)._p3}; //Array of all values in the triangles to compare
        java.util.Vector indexChecked = new java.util.Vector();
        return testMultiplevalues(_p1,arrayToCheck, indexChecked)&&
                testMultiplevalues(_p2,arrayToCheck, indexChecked)&&
                testMultiplevalues(_p3,arrayToCheck, indexChecked);
    }

    /***
     *
     * @param p the point of the triangle
     * @return the vector normal
     */
    public  Vector getNormal(Point3D p){
        return null;
    }

}
