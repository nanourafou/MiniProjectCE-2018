package primitives;

public class Material {

    private double _Kd, _Ks;
    private int _nShininess;


    public Material(double kd, double ks, int nShininess){
        this._Kd = kd;
        this._Ks = ks;
        this._nShininess = nShininess;
    }

    /**
     * Copy Ctor
     * @param m The material to copy
     */
    public Material(Material m){
        this._Kd = m._Kd;
        this._Ks = m._Ks;
        this._nShininess = m._nShininess;
    }



    /**
     * @return
     */
    public double getKd() {
        return _Kd;
    }

    public double getKs() {
        return _Ks;
    }

    public int getNShininess() {
        return _nShininess;
    }
}
