package org.example;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test
    void addTwoNumbersReturnsSum() {
        var result = App.add(1, 1);

        assertEquals(2, result);
    }

    @Test
    void givenOddNumberOddShouldReturnTrue() {
        assertTrue(App.odd(3));
    }

    @Test
    void givenEvenNumberOddShouldReturnFalse() {
        assertFalse(App.odd(2));
    }

    @Test
    void setNegativeNumberShouldThrowIllegalArgumentException() {
        App app = new App();

        assertThrows(IllegalArgumentException.class, () -> app.setK(-1));
    }

    @Test
    void equalsContractForApp() {
        EqualsVerifier.simple().forClass(App.class).verify();
    }
}