package elements;

import geometries.Geometries;
import geometries.Geometry;
import primitives.Point3D;
import primitives.Vector;

public class Scene {

    /**
     * The name of the scene
     */
    private String _name;

    /**
     * The background of the scene
     */
    private Color _background;


    /**
     * The Composite manager for geometry
     */
    private Geometries _geometriesManager; //Composite

    /**
     * The camera of the scene
     */
    private Camera _camera;

    /**
     * The distance between the camera and the scene
     */
    private double _cameraDistance;


    private AmbientLight _ambientLight;

    /**
     * Default constructor for the scene
     */
    public Scene(){
        this._name="Scene 1";
        this._background = new Color(java.awt.Color.GRAY);
        this._camera = new Camera(new Point3D(0,0,0),new Vector(0,0,-1), new Vector(0,-1,0));
        this._cameraDistance = 100;
        this._ambientLight = new AmbientLight(java.awt.Color.GRAY,1);
        this._geometriesManager = new Geometries();
    }

    /**
     * Constructor for the Scene
     * @param name The name of the scene
     * @param background The background of the scene
     * @param c The camera of the scene
     * @param screenDistance The distance between the camera and the scene
     */
    public Scene(String name,Color background, Camera c, AmbientLight amb, double screenDistance){
        this._name= name;
        this._background = background;
        this._camera = c;
        this._cameraDistance = screenDistance;
        this._ambientLight = amb;
        this._geometriesManager = new Geometries();
    }

    /**
     * Add a geometry in the scence.
     * @param g The geometry to add
     */
    public void addGeometry(Geometry g){
        _geometriesManager.addGeometry(g);
    }

    /**
     * @return Getter for the background
     */
    public Color getBackground(){
        return _background;
    }

    /**
     * @return Getter for the camera
     */
    public Camera getCamera() {
        return _camera;
    }

    /**
     * @return Getter for the camera and screen distance
     */
    public double getCameraDistance() {
        return _cameraDistance;
    }

    public Geometries getGeometriesManager() {
        return _geometriesManager;
    }

    /**
     * @return Getters for the Ambient Light
     */
    public AmbientLight getAmbientLight() {
        return _ambientLight;
    }

    /**
     * @return Getter for the name of the scene
     */
    public String getName() {
        return _name;
    }

    /**
     * @param _background Setter of the background
     */
    public void setBackground(Color _background) {
        this._background = _background;
    }

    /**
     * @param _cameraDistance Setter of the Camera Distance
     */
    public void setCameraDistance(double _cameraDistance) {
        this._cameraDistance = _cameraDistance;
    }

    /**
     * @param _camera Setter of the Camera
     */
    public void setCamera(Camera _camera){
        this._camera = _camera;
    }

    /**
     * @param ambientLight Setter for the ambient light
     */
    public void setAmbientLight(AmbientLight ambientLight) {
        this._ambientLight = ambientLight;
    }
}
