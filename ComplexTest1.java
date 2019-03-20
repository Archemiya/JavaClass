import junit.framework.TestCase;

public class ComplexTest extends TestCase {
    Complex c1 = new Complex(1.0,2.0);
    Complex c2 = new Complex(2.0,2.0);
    Complex c3 = new Complex(2.0,0.0);
    public void testGetRealPart() throws Exception {
        assertEquals(-1.1, Complex.getRealPart(-1.1));
        assertEquals(6.0, Complex.getRealPart(6.0));
        assertEquals(0.0, Complex.getRealPart(0.0));
    }
    public void testGetImagePart() throws Exception {
        assertEquals(-1.1, Complex.getImagePart(-1.1));
        assertEquals(6.0, Complex.getImagePart(6.0));
        assertEquals(0.0, Complex.getImagePart(0.0));
    }
    public void testComplexAdd() throws Exception {
        assertEquals("3.0+4.0i", c1.ComplexAdd(c2).toString());
        assertEquals("3.0+2.0i", c1.ComplexAdd(c3).toString());
        assertEquals("4.0+2.0i", c2.ComplexAdd(c3).toString());
    }
    public void testComplexSub() throws Exception {
        assertEquals("-1.0", c1.ComplexSub(c2).toString());
        assertEquals("-1.0+2.0i", c1.ComplexSub(c3).toString());
        assertEquals("0.0+2.0i", c2.ComplexSub(c3).toString());
    }
    public void testComplexMulti() throws Exception {
        assertEquals("-2.0+6.0i", c1.ComplexMulti(c2).toString());
        assertEquals("2.0+4.0i", c1.ComplexMulti(c3).toString());
        assertEquals("4.0+4.0i", c2.ComplexMulti(c3).toString());
    }
    public void testComplexComplexDiv() throws Exception {
        assertEquals("0.75+0.75i", c1.ComplexDiv(c2).toString());
        assertEquals("1.0+0.5i", c1.ComplexDiv(c3).toString());
        assertEquals("1.0+1.0i", c2.ComplexDiv(c3).toString());
    }
    public void testEquals() throws Exception{
        assertEquals(false,c1.equals(c2));
        assertEquals(false,c1.equals(c3));
        assertEquals(false,c2.equals(c3));
        assertEquals(true,c1.equals(c1));
    }
}