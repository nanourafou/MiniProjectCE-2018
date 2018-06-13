package primitives;

public class Material {

    private double _Kd, _Ks, _Kr, _Kt;
    private int _nShininess;


    public Material(double kd, double ks, int nShininess, double kr, double kt) {
        this._Kd = kd;
        this._Ks = ks;
        this._Kr = kr;
        this._Kt = kt;
        this._nShininess = nShininess;
    }

    /**
     * Copy Ctor
     *
     * @param m The material to copy
     */
    public Material(Material m) {
        this._Kd = m._Kd;
        this._Ks = m._Ks;
        this._Kr = m._Kr;
        this._Kt = m._Kt;
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

    public double getKr() {
        return _Kr;
    }

    public double getKt() {
        return _Kt;
    }

    public int getNShininess() {
        return _nShininess;
    }
}
