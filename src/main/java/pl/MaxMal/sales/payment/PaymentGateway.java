package pl.MaxMal.sales.payment;

public interface PaymentGateway {
    PaymentData register(RegisterPaymentRequest request);
}
