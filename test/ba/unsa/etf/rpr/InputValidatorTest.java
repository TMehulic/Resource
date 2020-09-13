package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {

    InputValidator validator = new InputValidator();

    @Test
    void isCorrectName() {
        assertFalse(validator.isCorrectName(""));
        assertFalse(validator.isCorrectName("123"));
        assertFalse(validator.isCorrectName("t1"));
        assertFalse(validator.isCorrectName("tar1k"));
        assertTrue(validator.isCorrectName("tarik"));
        assertTrue(validator.isCorrectName("ahsdhasduhasduhasdu"));
    }

    @Test
    void isCorrectBirthPlace() {
        assertTrue(validator.isCorrectBirthPlace("Zimbabwe"));
        assertTrue(validator.isCorrectBirthPlace("Bolnica"));
        assertTrue(validator.isCorrectBirthPlace("Travnik"));
        assertFalse(validator.isCorrectBirthPlace("Test123"));
        assertFalse(validator.isCorrectBirthPlace(""));
        assertFalse(validator.isCorrectBirthPlace("123"));
    }

    @Test
    void isCorrectJMBG() {
        assertTrue(validator.isCorrectJMBG("1234567123456"));
        assertFalse(validator.isCorrectJMBG("12345671234561"));
        assertFalse(validator.isCorrectJMBG("123456712341"));
        assertFalse(validator.isCorrectJMBG("T"));
        assertFalse(validator.isCorrectJMBG(""));
        assertFalse(validator.isCorrectJMBG("             "));
    }

    @Test
    void isCorrectEmail() {
        assertTrue(validator.isCorrectEmail("mail@mail.com"));
        assertTrue(validator.isCorrectEmail("mail@mail.ba.com"));
        assertFalse(validator.isCorrectEmail("mail@mailcom"));
        assertFalse(validator.isCorrectEmail("@mail.com"));
        assertFalse(validator.isCorrectEmail("123mail.com"));
        assertFalse(validator.isCorrectEmail("@"));
    }

    @Test
    void isCorrectPhone() {
        assertFalse(validator.isCorrectPhone("asd"));
        assertFalse(validator.isCorrectPhone(""));
        assertFalse(validator.isCorrectPhone("+"));
        assertFalse(validator.isCorrectPhone("+ "));
        assertTrue(validator.isCorrectPhone("123"));
        assertTrue(validator.isCorrectPhone("+123"));
    }

    @Test
    void isCorrectEcts() {
        assertTrue(validator.isCorrectEcts("5"));
        assertTrue(validator.isCorrectEcts("51"));
        assertFalse(validator.isCorrectEcts("5a"));
        assertFalse(validator.isCorrectEcts(""));
        assertFalse(validator.isCorrectEcts("asd"));
    }

    @Test
    void isCorrectIndex() {
        assertTrue(validator.isCorrectIndex("11111"));
        assertFalse(validator.isCorrectIndex("11a11"));
        assertFalse(validator.isCorrectIndex("11a111"));
        assertFalse(validator.isCorrectIndex("1111"));
        assertFalse(validator.isCorrectIndex(""));
    }
}