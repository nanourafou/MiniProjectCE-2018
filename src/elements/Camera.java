package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Camera {

    Point3D _position;
    Ray _toward;
    Ray _up;
    Ray _right;

    public Camera(Point3D centerPosition, Vector towardVector, Vector rightVector) throws Exception{

        if(towardVector.isColinear(rightVector))
            throw new Exception("Not Orthogonals vectors");

        _toward = new Ray(centerPosition,towardVector);
        _right = new Ray(centerPosition, rightVector);
        _up = new Ray(centerPosition, Vector.crossProdcut(towardVector,rightVector));

    }
}
