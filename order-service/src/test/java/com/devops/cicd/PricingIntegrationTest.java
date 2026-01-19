package com.devops.cicd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PricingIntegrationTest {

    @Test
    void fullPricingFlow_withRealConfigFile() {
        PricingConfig config = new PricingConfigLoader().load();

        PricingService service = new PricingService(config);

        double result1 = service.finalTotal(100, true);
        assertEquals(108, result1);

        double result2 = service.finalTotal(100, false);
        assertEquals(120, result2);
    }
}
