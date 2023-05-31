package Functii;

import Poli.Monom;
import Poli.Polinom;

public class Adunare {

    public static Polinom suma(Poli.Polinom p1, Polinom p2) {
        Polinom rez = new Polinom();

        for (int exp : p1.monom.keySet()) {
            Monom m1 = new Monom(p1.monom.get(exp), exp);
            rez.addMon(m1);
        }

        for (int exp : p2.monom.keySet()) {
            Monom m2 = new Monom(p2.monom.get(exp), exp);
            rez.addMon(m2);

        }
        return rez;
    }


}
