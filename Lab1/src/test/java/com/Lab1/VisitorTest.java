package com.Lab1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VisitorTest {

    @Test
    void testPhoneValidationValid() {
        Visitor visitor = new Visitor(1, "Mykola", "+380501563822");
        assertTrue(visitor.isPhoneValid());
    }

    @Test
    void testPhoneValidationInvalid() {
        Visitor visitor = new Visitor(2, "Olga", "00000");
        assertFalse(visitor.isPhoneValid());
    }

    @Test
    void testEqualsAndHashCode() {
        Visitor v1 = new Visitor(1, "Ivan", "+380971234567");
        Visitor v2 = new Visitor(1, "Ivan", "+380971234567");
        assertEquals(v1, v2);
        assertEquals(v1.hashCode(), v2.hashCode());
    }
}

