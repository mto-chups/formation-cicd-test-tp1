package com.devops.cicd;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PricingServiceTest {

    private final PricingConfig fakeConfig = new PricingConfig(0.2, 50.0);
    private final PricingService service = new PricingService(fakeConfig);

    @Test
    void applyVat_shouldReturnTtc_whenVatIs20Percent() {
        // HT 100 => TTC 120
        double ttc = service.applyVat(100.0);
        assertEquals(120.0, ttc, 1e-9);
    }

    @Test
    void applyVipDiscount_whenAmount100AndVIP() {
        double result = service.applyVipDiscount(100, true);
        assertEquals(90, result);
    }

    @Test
    void applyVipDiscount_whenAmount100AndNotVIP() {
        double result = service.applyVipDiscount(100, false);
        assertEquals(100, result);
    }

    @Test
    void applyShippingCost_whenAmountIsLessThan50(){
        double result = service.shippingCost(10);
        assertEquals(4.99, result);
    }

    @Test
    void applyShippingCost_whenAmountIs50(){
        double result = service.shippingCost(50);
        assertEquals(0, result);
    }

    @Test
    void totalTest_whenAmountIs100AndVIP(){
        double result = service.finalTotal(100, true);
        assertEquals(108, result);
    }

    @Test
    void totalTest_whenAmountIs20AndNotVIP(){
        double result = service.finalTotal(20, false);
        assertEquals(28.99, result, 1e-9);
    }
}
