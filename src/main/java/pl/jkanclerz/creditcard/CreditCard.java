package pl.jkanclerz.creditcard;

import java.math.BigDecimal;

public class CreditCard {
    private BigDecimal cardLimit;
    private BigDecimal currentBalance;


    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public CreditCard(String cardNumber) {

    }

    public void assignLimit(BigDecimal creditAmount) {
        if (isBelowLimitThreshold(creditAmount)) {
            throw new CreditBelowLimitException();
        }
        if (cardLimit != null) {
            throw new AssignLimitTwiceException();
        }
        this.cardLimit = creditAmount;
        this.currentBalance = creditAmount;
}

    private boolean isBelowLimitThreshold(BigDecimal creditAmount) {
        return creditAmount.compareTo(BigDecimal.valueOf(100)) < 0;
    }

    public BigDecimal getCardLimit() {
        return cardLimit;
    }

    private boolean isBelowCurrentBalanceThreshold(BigDecimal amountOfMoney) {
        return currentBalance.compareTo(amountOfMoney) < 0;
    }

    public void withdraw(BigDecimal amountOfMoney){
        if(isBelowCurrentBalanceThreshold(amountOfMoney)){
            throw new WithdrawBelowLimitException();
        }
        currentBalance=currentBalance.subtract(amountOfMoney);
    }
}
