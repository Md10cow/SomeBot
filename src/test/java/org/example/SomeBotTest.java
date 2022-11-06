package org.example;

import org.junit.jupiter.api.Test;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.*;
public class SomeBotTest {
    Logic bot = new Logic();
    @Test
    public void testCopy() {
        assertEquals(8.4, bot.parseArg("8.4"));
        assertEquals(8.4, bot.parseArg("8,4"));
        assertEquals(8, bot.parseArg("8"));
        assertEquals(NaN, bot.parseArg("~fawa"));
    }
}