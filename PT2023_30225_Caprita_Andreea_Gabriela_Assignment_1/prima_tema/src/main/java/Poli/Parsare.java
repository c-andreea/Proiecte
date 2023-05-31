package Poli;

public class Parsare {
    public static Polinom parsePolinom(String pol) {
        Polinom polinom = new Polinom();
        String[] monoStrings = pol.trim().split("(?=[+-])");
        for (String str : monoStrings) {
            if (str.isEmpty()) {
                continue;
            }

            String[] part = str.split("x\\^?");
            float coef = 0;
            int exp = 0;
            if (part.length == 0) {
                coef = 1;
                exp = 1;
            } else {
                if (part[0].isEmpty() || part[0].equals("-")) {
                    coef = part[0].equals("-") ? -1 : 1;
                } else if (part[0].equals("+")) {
                    coef = 1;
                } else {
                    coef = Float.parseFloat(part[0]);
                }

                if (str.startsWith("-")) {
                    coef = coef;
                }
                if (part.length > 1) {
                    exp = Integer.parseInt(part[1]);
                } else if (str.contains("x")) {
                    exp = 1;
                } else {
                    exp = 0;
                }
            }


            Monom mon = new Monom(coef, exp);
            polinom.addMon(mon);
        }


        return polinom;
    }


}
