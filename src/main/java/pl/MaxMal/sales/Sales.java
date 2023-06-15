package pl.MaxMal.sales;

import pl.MaxMal.sales.cart.Cart;
import pl.MaxMal.sales.cart.CartStorage;
import pl.MaxMal.sales.offering.EveryNItemLineDiscountPolicy;
import pl.MaxMal.sales.offering.Offer;
import pl.MaxMal.sales.offering.OfferCalculator;
import pl.MaxMal.sales.offering.TotalDiscountPolicy;
import pl.MaxMal.sales.payment.PaymentData;
import pl.MaxMal.sales.payment.PaymentGateway;
import pl.MaxMal.sales.payment.RegisterPaymentRequest;
import pl.MaxMal.sales.productdetails.NoSuchProductException;
import pl.MaxMal.sales.productdetails.ProductDetails;
import pl.MaxMal.sales.productdetails.ProductDetailsProvider;
import pl.MaxMal.sales.reservation.OfferAcceptanceRequest;
import pl.MaxMal.sales.reservation.Reservation;
import pl.MaxMal.sales.reservation.ReservationDetails;
import pl.MaxMal.sales.reservation.InMemoryReservationStorage;

import java.math.BigDecimal;
import java.util.Optional;

public class Sales {
    private CartStorage cartStorage;
    private ProductDetailsProvider productDetailsProvider;
    private final OfferCalculator offerCalculator;
    private PaymentGateway paymentGateway;
    private InMemoryReservationStorage reservationStorage;

    public Sales(
            CartStorage cartStorage,
            ProductDetailsProvider productDetails,
            OfferCalculator offerCalculator,
            PaymentGateway paymentGateway,
            InMemoryReservationStorage reservationStorage
        ) {
        this.cartStorage = cartStorage;
        this.productDetailsProvider = productDetails;
        this.offerCalculator = offerCalculator;
        this.paymentGateway = paymentGateway;
        this.reservationStorage = reservationStorage;
    }

    public void addToCart(String customerId, String productId) {
        Cart customerCart = loadCartForCustomer(customerId)
                .orElse(Cart.empty());

        ProductDetails product = loadProductDetails(productId)
                .orElseThrow(() -> new NoSuchProductException());

        customerCart.add(product.getId());

        cartStorage.addForCustomer(customerId, customerCart);
    }

    private Optional<ProductDetails> loadProductDetails(String productId) {
        return productDetailsProvider.load(productId);
    }

    private Optional<Cart> loadCartForCustomer(String customerId) {
        return cartStorage.load(customerId);
    }

    public Offer getCurrentOffer(String customerId) {
        Cart customerCart = loadCartForCustomer(customerId)
                .orElse(Cart.empty());

        Offer offer = this.offerCalculator.calculateOffer(
                customerCart.getCartItems(),
                new TotalDiscountPolicy(BigDecimal.valueOf(500), BigDecimal.valueOf(50)),
                new EveryNItemLineDiscountPolicy(5)
        );

        return offer;
    }

    public ReservationDetails acceptOffer(String customerId, OfferAcceptanceRequest request) {
        Offer offer = this.getCurrentOffer(customerId);

        PaymentData payment = paymentGateway.register(RegisterPaymentRequest.of(request, offer));

        Reservation reservation = Reservation.of(request, offer, payment);

        reservationStorage.save(reservation);

        return new ReservationDetails(reservation.getId(), reservation.getPaymentUrl());
    }
}
