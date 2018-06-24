package renderer;

import elements.Color;
import elements.LightSource;
import elements.PointLight;
import elements.Scene;
import geometries.Geometry;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Renderer {

    private Scene _scene;
    private ImageWriter _imgWrter;
    private static int MAX_RECURSION_LEVEL = 3;
    private static int SUPER_SAMPLING = 1;


    /**
     * Print grid
     *
     * @param interval Intervale distance
     */
    public void printGrid(int interval) {
        int height = _imgWrter.getHeight();
        int width = _imgWrter.getWidth();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                if (i % interval == 0 || j % interval == 0)
                    _imgWrter.writePixel(j, i, 255, 255, 255);

            }
        }
    }

    /**
     * Constructor
     *
     * @param _scene     The scene
     * @param _imgWriter The image Writer
     */
    public Renderer(Scene _scene, ImageWriter _imgWriter) {
        this._scene = _scene;
        this._imgWrter = _imgWriter;

    }

    /**
     * Default Constructor
     */
    public Renderer() {
        this._scene = new Scene();
        this._imgWrter = new ImageWriter("Test 1", 150, 150, 3, 3);

    }

    /**
     * Generate a Image without SuperSampling
     */
    public void renderImageNotSS() {
        for (int i = 0; i < _imgWrter.getHeight(); i++) {
            for (int j = 0; j < _imgWrter.getWidth(); j++) {
                Ray ray = _scene.getCamera().constructRayThoughPixel(_imgWrter.getNx(), _imgWrter.getNy(), j, i,
                        _scene.getCameraDistance(), _imgWrter.getWidth(), _imgWrter.getHeight());
                Map<Geometry, Point3D> closestPoint = getClosestPoint(_scene.getGeometriesManager().findIntersections(ray));

                if (closestPoint == null) {
                    _imgWrter.writePixel(j, i, _scene.getBackground().getColor());
                } else {

                    Map.Entry<Geometry, Point3D> gEntry = closestPoint.entrySet().iterator().next();
                    Color temp = calcColor(new GeoPoint(gEntry.getValue(), gEntry.getKey()), ray);
                    _imgWrter.writePixel(j, i, temp.getColor());

                }
            }
        }
    }

    /**
     * Render An Image with super-sampling(5 rays)
     */
    public void renderImage() {
        for (int i = 0; i < _imgWrter.getHeight(); i++) {
            for (int j = 0; j < _imgWrter.getWidth(); j++) {
                ArrayList<Ray> rayArrayList = _scene.getCamera().constructRayThrowAPixelSuperSampling(_imgWrter.getNx(), _imgWrter.getNy(), j, i,
                        _scene.getCameraDistance(), _imgWrter.getWidth(), _imgWrter.getHeight());

                double re=0,gr=0,bl=0;
                boolean firstRay=true;
                double coefficient;
                int z = 0;
                for (Ray ray : rayArrayList) {
                    Map<Geometry, Point3D> closestPoint = getClosestPoint(_scene.getGeometriesManager().findIntersections(ray));

                    if(firstRay) {//Middle Point Ray
                        coefficient = 0.8;
                        firstRay=false;
                    } else
                        coefficient = 0.1;


                    Color temp = null;
                    if (closestPoint == null) {
                        temp = _scene.getBackground();
                    } else {
                        Map.Entry<Geometry, Point3D> gEntry = closestPoint.entrySet().iterator().next();
                        temp = calcColor(new GeoPoint(gEntry.getValue(), gEntry.getKey()), ray);

                    }

                    if (temp != null) {
                        re += temp.getColor().getRed() * coefficient;
                        gr += temp.getColor().getGreen() * coefficient;
                        bl += temp.getColor().getBlue() * coefficient;
                    }


                }

                double scaler = 1.2;
                Color c = new Color(re / scaler, gr / scaler, bl / scaler);
                _imgWrter.writePixel(j, i, c.getColor());

            }
        }
    }


    /**
     * Write to image function
     */
    public void writeToImage() {
        _imgWrter.writeToimage();
    }


    /**
     * @param p The point to analuys
     * @return The light with the good intensity
     */
    private Color calcColor(GeoPoint geopoint, Ray inRay, int level, double k) {

        if (level == 0 || Coordinate.isZero(k))
            return new Color(0, 0, 0);

        Color ip = _scene.getAmbientLight().getIntensity();
        ip = ip.add(geopoint.getGeometry().getEmission());
        Vector n = geopoint.getGeometry().getNormal(geopoint.getPoint());
        Vector v = inRay.getDirection();
        int nShininess = geopoint.getGeometry().getMaterial().getNShininess();
        double Kd = geopoint.getGeometry().getMaterial().getKd();
        double Ks = geopoint.getGeometry().getMaterial().getKs();
        double o = 1;
        for (LightSource lsource : _scene.getLights()) {
            Vector l = lsource.getL(geopoint.getPoint());
            if (l.dotProduct(n) * v.dotProduct(n) >= 0) {
                Double distance = Double.MAX_VALUE;
                if (lsource instanceof PointLight) {
                    PointLight temp = (PointLight) lsource;
                    distance = geopoint.getPoint().distance(temp.getPosition());
                }
                o = occluded(l, geopoint, distance);

                if (!(Coordinate.isZero(o * k))) {
                    Color lightIntensity = lsource.getIntensity(geopoint.getPoint()).scale(o);
                    ip = ip.add(calcDiffusive(Kd, l, n, lightIntensity),
                            calcSpecular(Ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }


        Ray reflectedRay = constructReflectedRay(n, geopoint.getPoint(), inRay);
        Map.Entry<Geometry, Point3D> reflectedEntry = findClosestIntersection(reflectedRay);
        double kr = geopoint.getGeometry().getMaterial().getKr();
        Color reflected = new Color(0, 0, 0);
        if (reflectedEntry != null) {
            reflected = calcColor(new GeoPoint(reflectedEntry.getValue(), reflectedEntry.getKey()), reflectedRay, level - 1, k * kr).scale(kr);
        }

        // Recursive call for a refracted ray
        Ray refractedRay = constructRefractedRay(n, geopoint.getPoint(), inRay);
        Map.Entry<Geometry, Point3D> refractedEntry = findClosestIntersection(refractedRay);
        double kt = geopoint.getGeometry().getMaterial().getKt();
        Color refracted = new Color(0, 0, 0);
        if (refractedEntry != null) {


            refracted = calcColor(new GeoPoint(refractedEntry.getValue(), refractedEntry.getKey()), refractedRay, level - 1, k * kt).scale(kt);
        }


        return (ip.add(reflected).add(refracted));
    }

    /**
     * This function calccolor at a recusive level
     *
     * @param geopoint
     * @param inRay
     * @return
     */
    private Color calcColor(GeoPoint geopoint, Ray inRay) {

        return calcColor(geopoint, inRay, MAX_RECURSION_LEVEL, 1);

    }

    /**
     * Get the closest point
     *
     * @param mPoints Map<Geometry, List<Point3D>> of intersections
     * @return Map<Geometry                               ,                                                               Point3D>
     */
    private Map<Geometry, Point3D> getClosestPoint(Map<Geometry, List<Point3D>> map) {

        if (map == null || map.size() == 0)
            return null;

        final Map<Geometry, Point3D> result = new HashMap<Geometry, Point3D>();
        Point3D p0 = _scene.getCamera().getP0();
        double _maxD = Double.MAX_VALUE;

        for (Map.Entry<Geometry, List<Point3D>> g : map.entrySet()) {
            for (Point3D p : g.getValue()) {
                double dist = p0.distance(p);
                if (dist < _maxD) {
                    _maxD = dist;
                    result.clear();
                    result.put(g.getKey(), p);
                }
            }
        }

        return result;
    }


    /**
     * The function receive a Ray and find the closest intersection to the geometries
     *
     * @param ray
     * @return the closest intersection
     */
    private Map.Entry<Geometry, Point3D> findClosestIntersection(Ray ray) {

        Map<Geometry, List<Point3D>> intersectionPoints = _scene.getGeometriesManager().findIntersections(ray);

        if (intersectionPoints == null || intersectionPoints.size() == 0)
            return null;

        Map<Geometry, Point3D> closestPoint = getClosestPoint(intersectionPoints);
        Map.Entry<Geometry, Point3D> entry = closestPoint.entrySet().iterator().next();
        return entry;

    }


    /**
     * This function is for calculation of the specular of the light.
     *
     * @param Ks
     * @param l              The light vector
     * @param n              The normal vector
     * @param v              The vector
     * @param shininess      The nshiness coefficient
     * @param lightIntensity The lightIntensity Color
     * @return Color
     */
    private Color calcSpecular(double Ks, Vector l, Vector n, Vector v, int shininess, Color lightIntensity) {
        lightIntensity = new Color(lightIntensity);
        l = l.normalize();
        n = n.normalize();
        Vector ls = l.sub(n.mult(l.dotProduct(n) * 2)).normalize();
        double vr = v.dotProduct(ls);
        if (vr >= 0)
            return new Color(0, 0, 0);
        double k = Ks * Math.pow(-vr, shininess);

        return lightIntensity.scale(k);
    }


    /**
     * This function is for calculation of the diffusive of the light.
     *
     * @param Kd             The coeffient of diffusive light
     * @param l              The light vector
     * @param n              The normal vector
     * @param lightIntensity The lightIntensity Color
     * @return Color
     */
    private Color calcDiffusive(double Kd, Vector l, Vector n, Color lightIntensity) {
        lightIntensity = new Color(lightIntensity);
        double k = Kd * Math.abs(n.dotProduct(l));
        return lightIntensity.scale(k);
    }


    /**
     * Function for calculation if an object if occluded by amother object.
     *
     * @param l        The light vector
     * @param geopoint The geopoint (Geomety and the point)
     * @param distance The distance
     * @return
     */
    private double occluded(Vector l, GeoPoint geopoint, double distance) {
        Vector lightDirection = new Vector(l.mult(-1)); // from point to light source
        Vector normal = geopoint.getGeometry().getNormal(geopoint.getPoint()).mult(2);
        Vector epsVector = normal.mult((normal.dotProduct(lightDirection) > 0) ? 2 : -2);
        Point3D geometryPoint = geopoint.getPoint().addVector(epsVector);
        Ray lightRay = new Ray(geometryPoint, lightDirection);
        Map<Geometry, List<Point3D>> intersectionPoints = _scene.getGeometriesManager().findIntersections(lightRay);
        //return !intersectionPoints.isEmpty();
        double shadowK = 1;
        for (Map.Entry<Geometry, List<Point3D>> entry : intersectionPoints.entrySet()) {
            for (Point3D pp : entry.getValue()) {
                if (pp.distance(geopoint.getPoint()) < distance)
                    shadowK *= entry.getKey().getMaterial().getKt();
            }
        }
        return shadowK;
    }

    /**
     * The function build the ray of the reflection to a geometry
     *
     * @param n     normal
     * @param p     point in the geometry
     * @param inRay ray from the light to the point
     * @return the ray of the reflection
     */
    private Ray constructReflectedRay(Vector n, Point3D p, Ray inRay) {
        Vector epsVector = n.mult((n.dotProduct(inRay.getDirection()) > 0 ? 2 : -2));
        double ln = inRay.getDirection().dotProduct(n) * 2;
        Vector lnn = n.mult(ln);
        Vector r = inRay.getDirection().sub(lnn).normalize(); //CHECK//
        return new Ray(p.addVector(epsVector), r);
    }


    /**
     * The function build the ray of the refraction to a geometry
     *
     * @param n     normal
     * @param p     poimt in the geometry
     * @param inRay ray from the light to the point
     * @return the ray of the reflection
     */
    private Ray constructRefractedRay(Vector n, Point3D p, Ray inRay) {
        Vector epsVector = n.mult((n.dotProduct(inRay.getDirection()) > 0 ? 2 : -2));
        return new Ray(p.addVector(epsVector), inRay.getDirection());
    }


}
