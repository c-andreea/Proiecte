import Functii.Inmultire;
import Poli.Monom;
import Poli.Polinom;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testInmultire {
    @Test
    public void testInmultire() {
        Polinom p1 = new Polinom(new Monom(3, 2));
        p1.addMon(new Monom(2, 1));
        p1.addMon(new Monom(5, 0));

        Polinom p2 = new Polinom(new Monom(-2, 2));
        p2.addMon(new Monom(4, 1));
        p2.addMon(new Monom(1, 0));

        Polinom rez = Inmultire.inmultire(p1, p2);

        assertEquals(rez.monom.size(), 5);
        assertEquals(rez.monom.get(2), 1.0, 0.0001);
        assertEquals(rez.monom.get(1), 22.0, 0.0001);
        assertEquals(rez.monom.get(0), 5.0, 0.0001);
        assertEquals(rez.monom.get(3), 8.0, 0.0001);
        assertEquals(rez.monom.get(4), -6.0, 0.0001);


    }
}
