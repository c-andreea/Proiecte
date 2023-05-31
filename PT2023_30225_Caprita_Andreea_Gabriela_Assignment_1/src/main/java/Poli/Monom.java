package Poli;

public abstract class Monom {

    public float getCoeficient() {
        return coeficient;
    }

    public int getPutere() {
        return putere;
    }

    public void setCoeficient(float coeficient) {
        this.coeficient = coeficient;
    }

    public void setPutere(int putere) {
        this.putere = putere;
    }

    private float coeficient;
    private int putere;

    public Monom(float nrDinFata, int putere) {
        this.coeficient = nrDinFata;
        this.putere = putere;
    }




}
