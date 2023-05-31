import Functii.Derivare;
import Poli.Monom;
import Poli.Polinom;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testDerivare {
    @Test
    public void testDerivare() {
        Polinom p1 = new Polinom(new Monom(3, 2));
        p1.addMon(new Monom(2, 1));
        p1.addMon(new Monom(5, 0));

        Polinom rez = Derivare.deriv(p1);
        assertEquals(rez.monom.size(), 2);

        assertEquals(rez.monom.get(1), 6.0, 0.0001);
        assertEquals(rez.monom.get(0), 2.0, 0.0001);


    }
}
