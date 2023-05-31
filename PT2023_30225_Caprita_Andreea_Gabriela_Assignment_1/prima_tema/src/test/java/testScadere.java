import Functii.Scadere;
import Poli.Monom;
import Poli.Polinom;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testScadere {
    @Test
    public void testScadere() {
        Polinom p1 = new Polinom(new Monom(3, 2));
        p1.addMon(new Monom(2, 1));
        p1.addMon(new Monom(5, 0));

        Polinom p2 = new Polinom(new Monom(-2, 2));
        p2.addMon(new Monom(4, 1));
        p2.addMon(new Monom(1, 0));


        Polinom rez = Scadere.dif(p1, p2);
        assertEquals(rez.monom.size(), 3);
        assertEquals(rez.monom.get(2), 5.0, 0.0001);
        assertEquals(rez.monom.get(1), -2.0, 0.0001);
        assertEquals(rez.monom.get(0), 4.0, 0.0001);

    }
}
