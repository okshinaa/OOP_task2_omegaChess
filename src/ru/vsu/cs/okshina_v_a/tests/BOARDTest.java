package ru.vsu.cs.okshina_v_a.tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import ru.vsu.cs.okshina_v_a.enums.BOARD;

public class BOARDTest {
    @Test
    public void getters() {
        assertEquals(BOARD.LAST_FILE.getFileVal(), 'j');
        assertEquals(BOARD.FIRST_FILE.getFileVal(), 'a');
        assertEquals(BOARD.LAST_RANK.getRankVal(), 10);
        assertEquals(BOARD.FIRST_RANK.getRankVal(), 1);
    }
}