package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Camera {

    private Point3D _p0;
    private Vector _vToward;
    private Vector _vUp;
    private Vector _vRight;


    /**
     * Construct a ray throuht a pixel
     * @param Nx Total number of pixels x
     * @param Ny Total Number of pixels y
     * @param i The number of the pixel i
     * @param j The number of the pixel i
     * @param screenDistance The distance between the matrix and the camera
     * @param width The witdh of the image
     * @param height The height of the image
     * @return A new ray for the pixel
     */
    public Ray constructRayThoughPixel(int Nx,int Ny,int i,int j,double screenDistance,double width,double height){

        Point3D pc = _p0.addVector(_vToward.mult(screenDistance));

        double Rx = width/Nx;
        double Ry = height/Ny;

        //double  x = (i-(Nx/2))*Rx-Rx/2;
        //double  y = (j-(Ny/2))*Ry-Ry/2;

        double  x = (i-((Nx+1)/2))*Rx;
        double  y = (j-((Ny+1)/2))*Ry;

        Vector v1 = (_vRight.mult(x));
        Vector v2 = _vUp.mult(y);
        Vector v3 = v1.sub(v2);
        //Point3D p = pc.addVector((_vRight.mult(x)).sub(_vUp.mult(y)));

        Point3D p = pc.addVector(v3);

        Vector v = p.subVector(_p0);


        return new Ray(_p0,v.normalize());
    }


    /**
     * Construcor of a camera
     * @param centerPosition The position 0.
     * @param towardVector The toward vector.
     * @param upVector The up vector
     * @throws Exception If they are not orthogonals
     */
    public Camera(Point3D centerPosition, Vector towardVector, Vector upVector) {

        if(towardVector.isColinear(upVector))
            throw new IllegalArgumentException("Not Orthogonals vectors");

        _vToward = towardVector.normalize();
        _vUp = upVector.normalize();
        _vRight = towardVector.crossProdcut(upVector);

        _p0 = new Point3D(centerPosition);

    }


}
