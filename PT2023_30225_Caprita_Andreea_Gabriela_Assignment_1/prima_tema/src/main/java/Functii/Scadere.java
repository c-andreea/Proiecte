package Functii;

import Poli.Monom;
import Poli.Polinom;

public class Scadere {

    public static Polinom dif(Polinom x, Polinom y) {
        Polinom z = new Polinom();

        for (int exp : x.monom.keySet()) {
            Monom m1 = new Monom(x.monom.get(exp), exp);
            z.addMon(m1);
        }


        for (int exp : y.monom.keySet()) {
            Monom m2 = new Monom(-y.monom.get(exp), exp);
            z.addMon(m2);
        }


        return z;
    }


}
