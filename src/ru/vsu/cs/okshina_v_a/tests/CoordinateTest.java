package ru.vsu.cs.okshina_v_a.tests;

import org.junit.Test;
import ru.vsu.cs.okshina_v_a.rules.Coordinate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CoordinateTest {

    Coordinate coord1 = new Coordinate('a',1);
    Coordinate coord2 = new Coordinate('A',1);
    Coordinate coord3 = new Coordinate('c',7);
    Coordinate coord4 = new Coordinate('d',5);


    @Test
    public void equalCoords() {
        assertEquals(coord1, coord2);
    }

    @Test
    public void diffCoords() {
        assertNotEquals(coord1, coord3);
    }

    @Test
    public void coordString() {
        assertEquals("a1", coord1.toString());
        assertEquals("d5", coord4.toString());
    }

    @Test
    public void getters() {
        assertEquals('c', coord3.getFile());
        assertEquals(5, coord4.getRank());
    }

}