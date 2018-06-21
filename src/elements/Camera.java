package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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



        /*
        double randomNumRy = ThreadLocalRandom.current().nextDouble(-Ry/2f,Ry/2f );
        double randomNumRx = ThreadLocalRandom.current().nextDouble(-Rx/2f,Rx/2f );

        Vector vSuper = new Vector(0,0,0); // new Vector(0,randomNumRy,randomNumRx);
        vSuper = vSuper.add(_vUp);
        vSuper = vSuper.mult(randomNumRy);
        vSuper = vSuper.add(_vRight);
        vSuper = vSuper.mult(randomNumRx);

        Pij = Pij.addVector(vSuper);*/


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


    //��� 10 �����
    public ArrayList<Ray> constructRayThrowAPixel2(int Nx, int Ny, double i, double j, double screenDist, double width, double height) {
        ArrayList<Ray> rays = new ArrayList<>();
        //������ �������
        double Rx = width / Nx;//���� ���� ��� ���� �������� �����
        double Ry = height / Ny; //���� ���� ���� ���� �������� �����

        double multVRight = 0;
        double multVUp = 0;

        for (int z = 0; z < 5; z++) {
            switch (z) {
                case 0:
                    //������ �������
                    multVUp = (j - (Ny / 2.0) * Ry) + Ry / 2;
                    multVRight = (i - (Nx / 2.0) * Rx) + Rx / 2;
                    break;
                case 1:
                    //����� ���� �����
                    multVRight = (i - (Nx / 2.0) * Rx);
                    multVUp = (j - (Ny / 2.0) * Ry);
                    break;
                case 3:
                    //����� ���� ����
                    multVRight = (i - (Nx / 2.0) * Rx);
                    multVUp = (j - (Ny / 2.0) * Ry + Ry);
                    break;
                case 2:
                    //����� ���� �����
                    multVRight = (i - (Nx / 2.0) * Rx + Rx);
                    multVUp = (j - (Ny / 2.0) * Ry);
                    break;
                case 4:
                    //����� ���� ����
                    multVRight = (i - (Nx / 2.0) * Rx + Rx);
                    multVUp = (j - (Ny / 2.0) * Ry + Ry);
                    break;

            }
            Point3D Pc = _p0.addVector(_vToward.mult(screenDist));//����� ������ ��� ���� ������ ���� ����� ������
            Point3D pij = Pc.addVector((_vRight.mult(multVRight).sub(_vUp.mult(multVUp))));
            Vector rayVector = (pij.subVector(_p0)).normalize();//����� ������
            rays.add(new Ray(new Point3D(_p0), rayVector));
        }

        return rays;
    }
}
