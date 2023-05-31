package Functii;

import Poli.Monom;
import Poli.Polinom;

public class Inmultire {

    public static Polinom inmultire(Poli.Polinom p1, Polinom p2) {

        Polinom rez = new Polinom();

        for (int exp : p1.monom.keySet()) {
            for (int exp1 : p2.monom.keySet()) {
                float coef = p1.monom.get(exp) * p2.monom.get(exp1);
                int exps = exp1 + exp;
                Monom m = new Monom(coef, exps);
                rez.addMon(m);

            }

        }

        return rez;
    }


}
