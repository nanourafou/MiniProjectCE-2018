package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Camera {

    Point3D _p0;
    Vector _vToward;
    Vector _vUp;
    Vector _vRight;


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

        Point3D pc = Point3D.addVector(_p0,Vector.mult(_vToward,screenDistance)); // Pc = P0 + d*_vToward

        double Rx = width/Nx;
        double Ry = height/Ny;

        //double  x = (i-(Nx/2))*Rx-Rx/2;
        //double  y = (j-(Ny/2))*Ry-Ry/2;

        double  x = (i-(Nx+1/2))*Rx;
        double  y = (j-(Ny+1/2))*Ry;

        Point3D p =
                Point3D.addVector(pc,
                    Vector.sub(
                        Vector.mult(_vRight,x),
                        Vector.mult(_vUp,y)));

        Vector v = p.subVector(_p0);


        return new Ray(_p0,v);
    }


    /**
     * Construcor of a camera
     * @param centerPosition The position 0.
     * @param towardVector The toward vector.
     * @param upVector The up vector
     * @throws Exception If they are not orthogonals
     */
    public Camera(Point3D centerPosition, Vector towardVector, Vector upVector) throws Exception{

        if(towardVector.isColinear(upVector))
            throw new Exception("Not Orthogonals vectors");

        _vToward = Vector.normalize(towardVector);
        _vUp = Vector.normalize(upVector);
        _vRight = new Vector(Vector.crossProdcut(towardVector,upVector));

        _p0 = new Point3D(centerPosition);

    }


}
