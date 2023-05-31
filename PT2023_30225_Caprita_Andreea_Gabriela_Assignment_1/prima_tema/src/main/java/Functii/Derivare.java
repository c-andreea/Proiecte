package Functii;

import Poli.Monom;
import Poli.Polinom;

public class Derivare {

    public static Polinom deriv(Polinom p) {
        Polinom rez = new Polinom();
        for (int exp : p.monom.keySet()) {
            if (exp != 0) {
                float coef = p.monom.get(exp) * exp;
                int exps = exp - 1;

                Monom m = new Monom(coef, exps);
                rez.addMon(m);

            }
        }
        return rez;
    }


}
