import Functii.Integrare;
import Poli.Monom;
import Poli.Polinom;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testIntegrare {
    @Test
    public void testIntegrare() {
        Polinom p1 = new Polinom(new Monom(3, 2));
        p1.addMon(new Monom(2, 1));
        p1.addMon(new Monom(5, 0));

        Polinom rez = Integrare.integrare(p1);
        assertEquals(rez.monom.size(), 3);
        assertEquals(rez.monom.get(3), 1.0, 0.0001);
        assertEquals(rez.monom.get(2), 1.0, 0.0001);
        assertEquals(rez.monom.get(1), 5.0, 0.0001);

    }
}
