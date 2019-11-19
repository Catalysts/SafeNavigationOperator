package cc.catalysts.utils.test;

import cc.catalysts.utils.test.model.A;
import cc.catalysts.utils.test.model.B;
import cc.catalysts.utils.test.model.C;
import org.junit.Test;

import java.util.Arrays;

import static cc.catalysts.utils.SafeNavigationOperator.*;
import static org.junit.Assert.*;

public class SafeNavigationOperatorTest {
    @Test
    public void testResolveNullValue(){
        A a = new A(new B(null));
        assertNull(resolve(() -> a.getB().getC().getValue()));
    }

    @Test
    public void testResolveActualValue(){
        A a = new A(new B(new C("test", null)));
        assertEquals(resolve(() -> a.getB().getC().getValue()), "test");
    }

    @Test
    public void testResolveToListNullValue(){
        A a = new A(new B(null));
        assertTrue(resolveToList(() -> a.getB().getC().getMultiValue()).isEmpty());
    }

    @Test
    public void testResolveToListActualValue(){
        A a = new A(new B(new C(null, Arrays.asList("asdf", "test"))));
        assertFalse(resolveToList(() -> a.getB().getC().getMultiValue()).isEmpty());
    }

    @Test
    public void testIsValuePresentNullValue(){
        A a = new A(new B(null));
        assertFalse(isValuePresent(() -> a.getB().getC().getValue()));
    }

    @Test
    public void testIsValuePresentActualValue(){
        A a = new A(new B(new C("test", null)));
        assertTrue(isValuePresent(() -> a.getB().getC().getValue()));
    }
}
