package renderer;

import elements.Color;
import elements.LightSource;
import elements.Scene;
import geometries.Geometry;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Renderer {

    private Scene _scene;
    private ImageWriter _imgWrter;


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
     * Generate a Image
     */
    public void renderImage() {
        for (int i = 0; i < _imgWrter.getHeight(); i++) {
            for (int j = 0; j < _imgWrter.getWidth(); j++) {
                Ray r = _scene.getCamera().constructRayThoughPixel(_imgWrter.getNx(), _imgWrter.getNy(), i, j, _scene.getCameraDistance(), _imgWrter.getWidth(), _imgWrter.getHeight());
                Map<Geometry, List<Point3D>> lstIntersections = _scene.getGeometriesManager().findIntersections(r);

                if (lstIntersections.size() == 0)
                    _imgWrter.writePixel(j, i, _scene.getBackground().getColor());
                else {
                    Map<Geometry, Point3D> closePoint = getClosestPoint(lstIntersections);
                    for (Map.Entry<Geometry, Point3D> entry : closePoint.entrySet()) {
                        if (entry.getValue() != null) {


                            //_imgWrter.writePixel(j, i, calcColor(entry.getKey(), entry.getValue()).getColor());

                            GeoPoint geo = new GeoPoint(entry.getValue(),entry.getKey());
                            _imgWrter.writePixel(j, i, calcColor(geo).getColor());
                        } else {
                            _imgWrter.writePixel(j, i, _scene.getBackground().getColor());
                        }

                    }

                }
            }

        }

    }

    /**
     * @param p The point to analuys
     * @return The light with the good intensity
     */
    private Color calcColor(Geometry g, Point3D p) { //Why need a point ???

        Color color = new Color(_scene.getAmbientLight().getIntensity());
        color.add(g.getEmission());
        Vector n = g.getNormal(p);
        int nShininess = g.getMaterial().getNShininess();
        double kd = g.getMaterial().getKd();
        double ks = g.getMaterial().getKs();
        Vector v = p.subVector(_scene.getCamera().getP0()).normalize();

        if (_scene.getLights() != null) {
            for (LightSource lightSource : _scene.getLights()) {
                Color lightIntensity = lightSource.getIntensity(p);
                Vector l = lightSource.getL(p);
                //color.add(calcDiffusive(kd, l, n, lightIntensity), calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                if(v.dotProduct(n)*l.dotProduct(n)>0) {
                    Color diff = calcDiffusive(kd, l, n, lightIntensity);
                    Color speclar = calcSpecular(ks, l, n, v, nShininess, lightIntensity);
                    color = color.add(diff, speclar);
                }

            }
        }
        return color;
    }




    private Color calcColor(GeoPoint geopoint) {
        /*Color color = new Color(_scene.getAmbientLight().getIntensity());
        color.add(geopoint.getGeometry().getEmission());
        Vector v = geopoint.getPoint().subVector(_scene.getCamera().getP0()).normalize();
        Vector n = geopoint.getGeometry().getNormal(geopoint.getPoint());
        int nShininess = geopoint.getGeometry().getMaterial().getNShininess();
        double kd = geopoint.getGeometry().getMaterial().getKd();
        double ks = geopoint.getGeometry().getMaterial().getKs();

        for (LightSource lightSource : _scene.getLights()) {
            Vector l = lightSource.getL(geopoint.getPoint());
            if (n.dotProduct(l) * n.dotProduct(v) > 0)
                if (!occluded(l, geopoint)) {
                    Color lightIntensity = lightSource.getIntensity(geopoint.getPoint());
                    color.add(calcDiffusive(kd, l, n, lightIntensity), calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
        }
        return color;*/


        Color color = new Color(_scene.getAmbientLight().getIntensity());
        color.add(geopoint.getGeometry().getEmission());
        Vector n = geopoint.getGeometry().getNormal(geopoint.getPoint());
        int nShininess = geopoint.getGeometry().getMaterial().getNShininess();
        double kd = geopoint.getGeometry().getMaterial().getKd();
        double ks = geopoint.getGeometry().getMaterial().getKs();
        Vector v = geopoint.getPoint().subVector(_scene.getCamera().getP0()).normalize();

        if (_scene.getLights() != null) {
            for (LightSource lightSource : _scene.getLights()) {
                Vector l = lightSource.getL(geopoint.getPoint());
                if(v.dotProduct(n)*l.dotProduct(n)>0) {
                    if (!occluded(l, geopoint)){
                        Color lightIntensity = lightSource.getIntensity(geopoint.getPoint());
                        Color diff = calcDiffusive(kd, l, n, lightIntensity);
                        Color speclar = calcSpecular(ks, l, n, v, nShininess, lightIntensity);
                        color = color.add(diff, speclar);
                    }

                }

            }
        }
        return color;
    }


    /**
     * Get the closest point
     *
     * @param mPoints Map<Geometry, List<Point3D>> of intersections
     * @return Map<Geometry   ,       Point3D>
     */
    private Map<Geometry, Point3D> getClosestPoint(Map<Geometry, List<Point3D>> mPoints) {
        double distance = Double.MAX_VALUE;
        Point3D p0 = _scene.getCamera().getP0();
        Point3D minDistancePoint = null;
        Geometry key = null;


        for (Map.Entry<Geometry, List<Point3D>> entry : mPoints.entrySet()) {
            key = entry.getKey();
            List<Point3D> points = entry.getValue();

            for (Point3D pt : points) {
                if (p0.distance(pt) < distance) {
                    minDistancePoint = new Point3D(pt);
                    distance = p0.distance(pt);
                }
            }

        }
        if (key == null)
            return null;
        Map<Geometry, Point3D> m1 = new HashMap<>();
        m1.put(key, minDistancePoint);

        return m1;
    }

    /**
     * Write to image function
     */
    public void writeToImage() {
        _imgWrter.writeToimage();
    }


    /**
     * Calcul Specular
     *
     * @param ks
     * @param v
     * @param normal
     * @param l
     * @param shininess
     * @param lightIntensity
     * @return
     */
    private Color calcSpecular(double Ks, Vector l, Vector n, Vector v, int shininess, Color lightIntensity) {
        lightIntensity = new Color(lightIntensity);
        l  = l.normalize();
        n = n.normalize();
        Vector ls = l.sub(n.mult(l.dotProduct(n) * 2)).normalize();
        double vr = v.dotProduct(ls);
        if (vr >= 0)
            return new Color(0, 0, 0);
        double k = Ks * Math.pow(-vr, shininess);

        return lightIntensity.scale(k);
    }


    /**
     * Calul Diffusion
     *
     * @param kd
     * @param normal
     * @param l
     * @param lightIntensity
     * @return
     */

    private Color calcDiffusive(double Kd, Vector l, Vector n, Color lightIntensity) {
        lightIntensity = new Color(lightIntensity);
        double k = Kd * Math.abs(n.dotProduct(l));
        return lightIntensity.scale(k);
    }


    /**
     * Occuled function of shadow
     *
     * @param l
     * @param geopoint Geopoint
     * @return
     */
    private boolean occluded(Vector l, GeoPoint geopoint) {
        Vector lightDirection = l.mult(-1); // from point to light source
        Vector normal = geopoint.getGeometry().getNormal(geopoint.getPoint()).mult(2);
        Vector epsVector = normal.mult((normal.dotProduct(lightDirection) > 0) ? 2 : -2);
        Point3D geometryPoint = geopoint.getPoint().addVector(epsVector);
        Ray lightRay = new Ray(geometryPoint, lightDirection);
        Map<Geometry, List<Point3D>> intersectionPoints = _scene.getGeometriesManager().findIntersections(lightRay);
        return !intersectionPoints.isEmpty();
    }
}
