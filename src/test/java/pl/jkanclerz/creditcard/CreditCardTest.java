package pl.jkanclerz.creditcard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.math.BigDecimal;

public class CreditCardTest {

    @Test
    void itMyFirstTestRun() {
        boolean value = true;
        assertTrue(value);
    }

    @Test
    void my2ndTest() {
        boolean value = false;
    }

    @Test
    void testSchema() {
        //Arrange // Given // Input

        //Act // When // Action

        //Assert // Then // Expected // Output
    }

    @Test
    void itAllowsToAssignCreditLimit() {
        //Arrange
        CreditCard card = new CreditCard("1234-5678");
        //Act
        card.assignLimit(BigDecimal.valueOf(1000));
        //Assert // Then
        assertEquals(BigDecimal.valueOf(1000), card.getCardLimit());
    }

    @Test
    void itAllowsCreditLimit() {
        //Arrange
        CreditCard card = new CreditCard("1234-5678");
        //Act
        card.assignLimit(BigDecimal.valueOf(1000));
        //Assert // Then
        assertEquals(BigDecimal.valueOf(1000), card.getCardLimit());
    }

    @Test
    void testDoubleAndFloats(){
        double x1 = 0.03;
        double x2 = 0.02;
        double result = x1 - x2;
        System.out.println(result);
    }

    @Test
    void itAllowsToAssignDifferentCreditLimit() {
        //Arrange
        CreditCard card1 = new CreditCard("1234-5678");
        CreditCard card2 = new CreditCard("1234-5679");
        //Act
        card1.assignLimit(BigDecimal.valueOf(1000));
        card2.assignLimit(BigDecimal.valueOf(1100));
        //Assert // Then
        assertEquals(BigDecimal.valueOf(1000), card1.getCardLimit());
        assertEquals(BigDecimal.valueOf(1100), card2.getCardLimit());
    }

    @Test
    void itDenyLimitsBelow100() {
        CreditCard card1 = new CreditCard("1234-5678");
        CreditCard card2 = new CreditCard("1234-5678");
        CreditCard card3 = new CreditCard("1234-5678");

        assertThrows(
                CreditBelowLimitException.class,
                () -> card1.assignLimit(BigDecimal.valueOf(10)));

        try {
            card2.assignLimit(BigDecimal.valueOf(99));
            fail("Should throw exception");
        } catch (CreditBelowLimitException e) {
            assertTrue(true);
        }

        assertDoesNotThrow(() -> card1.assignLimit(BigDecimal.valueOf(100)));
    }

    @Test
    void canNotAssignLimitTwice(){
        CreditCard card = new CreditCard("1234-5678");
        card.assignLimit(BigDecimal.valueOf(110));

        assertThrows(
                AssignLimitTwiceException.class,
                () -> card.assignLimit(BigDecimal.valueOf(111)));


    }

    @Test
    void itAllowsToWithdraw() {
        CreditCard card = new CreditCard("1234-5678");
        card.assignLimit(BigDecimal.valueOf(1000));

        card.withdraw(BigDecimal.valueOf(100));

        assertEquals(BigDecimal.valueOf(900), card.getCurrentBalance());
    }

}
