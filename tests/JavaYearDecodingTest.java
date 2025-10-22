package tests;

import io.github.vindecoder.offline.VINValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaYearDecodingTest {
    private String withCharAt(String base, int index, char c) {
        char[] arr = base.toCharArray();
        arr[index] = c;
        return new String(arr);
    }

    private String baseVin() {
        // Base VIN with allowed characters (no I, O, Q). Length 17
        return "1ABCDEFGH1JKLMNR"; // index 6 (pos7), index 9 (pos10)
    }

    @Test
    void digit1_pos7digit_is_2001() {
        String vin = withCharAt(withCharAt(baseVin(), 6, '1'), 9, '1');
        assertEquals(Integer.valueOf(2001), VINValidator.getModelYear(vin));
    }

    @Test
    void digit1_pos7letter_is_2031() {
        String vin = withCharAt(withCharAt(baseVin(), 6, 'A'), 9, '1');
        assertEquals(Integer.valueOf(2031), VINValidator.getModelYear(vin));
    }

    @Test
    void digit9_pos7digit_is_2009() {
        String vin = withCharAt(withCharAt(baseVin(), 6, '2'), 9, '9');
        assertEquals(Integer.valueOf(2009), VINValidator.getModelYear(vin));
    }

    @Test
    void digit9_pos7letter_is_2039() {
        String vin = withCharAt(withCharAt(baseVin(), 6, 'B'), 9, '9');
        assertEquals(Integer.valueOf(2039), VINValidator.getModelYear(vin));
    }

    @Test
    void letterA_pos7digit_is_1980() {
        String vin = withCharAt(withCharAt(baseVin(), 6, '3'), 9, 'A');
        assertEquals(Integer.valueOf(1980), VINValidator.getModelYear(vin));
    }

    @Test
    void letterY_pos7letter_is_2030() {
        String vin = withCharAt(withCharAt(baseVin(), 6, 'C'), 9, 'Y');
        assertEquals(Integer.valueOf(2030), VINValidator.getModelYear(vin));
    }
}
