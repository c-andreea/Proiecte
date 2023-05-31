import Functii.Adunare;
import Poli.Monom;
import Poli.Polinom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testAdunare {
    @Test
    public void testSuma() {
        Polinom p1 = new Polinom(new Monom(3, 2));
        p1.addMon(new Monom(2, 1));
        p1.addMon(new Monom(5, 0));

        Polinom p2 = new Polinom(new Monom(-2, 2));
        p2.addMon(new Monom(4, 1));
        p2.addMon(new Monom(1, 0));

        Polinom rez = Adunare.suma(p1, p2);

        assertEquals(rez.monom.size(), 3);
        assertEquals(rez.monom.get(2), 1.0, 0.0001);
        assertEquals(rez.monom.get(1), 6.0, 0.0001);
        assertEquals(rez.monom.get(0), 6.0, 0.0001);
    }
}
