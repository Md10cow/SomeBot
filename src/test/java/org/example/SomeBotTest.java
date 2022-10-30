package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class SomeBotTest {
    Logic bot = new Logic();
    @Test
    public void testCopy() {
        assertEquals("Завтра апокалипсис", bot.parseMessage("Завтра апокалипсис"));
    }
}