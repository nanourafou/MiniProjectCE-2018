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
     *
     * @param Nx             Total number of pixels x
     * @param Ny             Total Number of pixels y
     * @param i              The number of the pixel i
     * @param j              The number of the pixel i
     * @param screenDistance The distance between the matrix and the camera
     * @param width          The witdh of the image
     * @param height         The height of the image
     * @return A new ray for the pixel
     */
    public Ray constructRayThoughPixel(int Nx, int Ny, int i, int j, double screenDistance, double width, double height) {
        Point3D Pc = _p0.addVector(_vToward.mult(screenDistance));
        double Ry = height / Ny;
        double Rx = width / Nx;
        Point3D Pij = Pc.addVector(new Vector(_vRight.mult((i - (Nx + 1) / 2) * Rx))
                .sub(_vUp.mult((j - (Ny + 1) / 2) * Ry)));
        Vector Vij = (Pij.subVector(_p0)).normalize();
        return new Ray(_p0, Vij);
    }


    /**
     * Construcor of a camera
     *
     * @param centerPosition The position 0.
     * @param towardVector   The toward vector.
     * @param upVector       The up vector
     * @throws Exception If they are not orthogonals
     */
    public Camera(Point3D centerPosition, Vector towardVector, Vector upVector) {

        if (towardVector.isColinear(upVector))
            throw new IllegalArgumentException("Not Orthogonals vectors");

        _vToward = towardVector.normalize();
        _vUp = upVector.normalize();
        _vRight = upVector.crossProdcut(towardVector).normalize();
        _p0 = new Point3D(centerPosition);

    }

    /**
     * @return Origin Point
     */
    public Point3D getP0() {
        return _p0;
    }

    /**
     * @return Vector Right
     */
    public Vector getVRight() {
        return _vRight;
    }

    /**
     * @return Vector Toward
     */
    public Vector getVToward() {
        return _vToward;
    }

    /**
     * @return Vector Up
     */
    public Vector getVUp() {
        return _vUp;
    }
}
