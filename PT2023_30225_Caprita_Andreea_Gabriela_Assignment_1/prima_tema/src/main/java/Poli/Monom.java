//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Poli;

public class Monom {
    private float coeficient;
    private int putere;

    public Monom(float nrDinFata, int putere) {
        this.coeficient = nrDinFata;
        this.putere = putere;
    }


    public float getCoeficient() {
        return this.coeficient;
    }

    public void setCoeficient(float coeficient) {
        this.coeficient = coeficient;
    }

    public int getPutere() {
        return this.putere;
    }

    public void setPutere(int putere) {
        this.putere = putere;
    }

    public Monom create(Monom x) {
        return new Monom(coeficient * x.getCoeficient(), putere + x.getPutere());
    }

    public String toString() {
        if (coeficient == 0) {
            return "";
        }
        if (putere == 0) {
            return String.valueOf(coeficient);
        }
        if ((coeficient == 1 && putere > 1)) {
            return "x^" + putere;
        }
        if (coeficient == -1 && putere > 1) {
            return "-x^" + putere;
        }
        if (putere == 1) {
            return coeficient + "x";
        }
        return coeficient + "x^" + putere;

    }
}
