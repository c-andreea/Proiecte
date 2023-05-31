package Functii;

import Poli.Monom;
import Poli.Polinom;

public class Integrare {

    public static Polinom integrare(Polinom p) {
        Polinom rez = new Polinom();

        for (int exp : p.monom.keySet()) {
            float cof = p.monom.get(exp);
            int exps = exp + 1;
            Monom m = new Monom(cof / exps, exps);
            rez.addMon(m);
        }
        rez.addMon(new Monom(0, 0));
        return rez;
    }


}
