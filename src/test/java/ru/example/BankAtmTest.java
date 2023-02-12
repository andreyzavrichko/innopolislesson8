package ru.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BankAtmTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void shouldCheckPositiveTest(int count) {
        final boolean res = BankATM.checkCardAndPassword("6228123123", 888888, count);
        assertTrue(res);
    }

    @Test
    void shouldCheckNegativeCardTest() {
        final boolean res = BankATM.checkCardAndPassword("62281231", 888888, 1);
        assertFalse(res);
    }

    @Test
    void shouldCheckNegativePassTest() {
        final boolean res = BankATM.checkCardAndPassword("6228123123", 88888, 0);
        assertFalse(res);
    }


    @Test
    void shouldCheckNegativePassConsoleTest() {
        BankATM.checkCardAndPassword("4", 4, 0);
        assertThat(outContent.toString()).startsWith("Извините, пароль неверный");
    }


    @Test
    void shouldCheckBlockCardTest() {
        BankATM.checkCardAndPassword("6228123123", 88888, 3);
        assertThat(outContent.toString()).startsWith("Извините, ваша карта заблокирована!");
    }


}
