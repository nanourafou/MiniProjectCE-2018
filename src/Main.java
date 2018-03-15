
import geometries.Cylinder;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Vector;
import primitives.Ray;

/**
 * ************************* *
 *          Targuil 1        *
 *                           *
 *     Netanel COHEN SOLAL   *
 *  netanelcohensolal@me.com *
 *                           *
 *          1444669          *
 *                           *
 *       Yaaccov Chelli      *
 *   yaacovchelly@hotmail.fr *
 *                           *
 *          1382007          *
 * ***************************
 */

public class Main {

    //Color constantes
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public static void main(String[] args) {



    }

    public static String coloredText(Object o){
        return ANSI_BLUE+o.toString()+ANSI_RESET;
    }
}
