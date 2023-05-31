package Poli;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Polinom {
    public Map<Integer, Float> monom;

    public Polinom() {
        monom = new HashMap<>();
    }

    public Polinom(Monom m) {
        this();
        addMon(m);
    }

    public Monom getMonom(int exp) {
        Float coef = monom.get(exp);
        if (coef != null) {
            return new Monom(coef, exp);
        }
        return null;
    }
    public void addMon(Monom m) {
        int exp = m.getPutere();
        float cof = m.getCoeficient();

        if (cof == 0) {
            monom.remove(exp);
        } else {
            if (monom.containsKey(exp)) {
                float oldCof = monom.get(exp);
                monom.put(exp, cof + oldCof);
            } else {
                monom.put(exp, cof);
            }
        }

    }

    public Polinom add(Polinom p) {
        Polinom rez = new Polinom();
        for (int exp : monom.keySet()) {
            rez.addMon(new Monom(monom.get(exp), exp));
        }
        for (int exp : p.monom.keySet()) {
            rez.addMon(new Monom(p.monom.get(exp), exp));
        }
        return rez;
    }

    public float ev(float x) {
        float rez = 0;
        for (int exp : monom.keySet()) {
            rez += monom.get(exp) * (float) Math.pow(x, exp);
        }
        return rez;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        boolean first = true;
        int[] exponents = monom.keySet().stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(exponents);
        for (int i = exponents.length - 1; i >= 0; i--) {
            int exp = exponents[i];
            float cof = monom.get(exp);
            if (cof == 0) {
                continue;
            } else if (cof > 0 && !first) {
                s.append("+");
            }
            first = false;
            if (cof == 1 && exp >= 1) {
                s.append("x");
            } else if (cof == -1 && exp >= 1) {
                s.append("-x");
            } else {
                s.append(cof);
                if (exp >= 1) {
                    s.append("x");
                }
            }
            if (exp > 1) {
                s.append("^").append(exp);
            }
        }
        if (s.length() == 0) {
            s.append("0");
        }
        return s.toString();
    }
    public int getMax() {
        int mExp = -1;
        for (int exp : monom.keySet()) {
            {
                if (exp > mExp) {
                    mExp = exp;
                }
            }

        }
        return mExp;
    }

}
