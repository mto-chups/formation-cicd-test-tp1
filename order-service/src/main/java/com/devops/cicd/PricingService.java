package com.devops.cicd;

public class PricingService {
    private final PricingConfig config;

    public PricingService(PricingConfig config) {
        this.config = config;
    }

    public double applyVat(double amountExclVat) {
        return amountExclVat*(1+this.config.getVatRate());
    }

    public double applyVipDiscount(double amount, boolean vip) {
        if(!vip){return amount;}
        return amount * 0.9;

    }

    public double shippingCost(double amount) {
        if(amount >= this.config.getFreeShippingThreshold()){
            return 0;
        }else{
            return 4.99;
        }
    }

    /**
     * - TVA appliquée d'abord : HT -> TTC
     * - remise VIP appliquée sur TTC
     * - frais de livraison ajoutés ensuite (calculés sur TTC)
     */
    public double finalTotal(double amountExclVat, boolean vip) {
        double result = applyVat(amountExclVat);
        result = applyVipDiscount(result, vip);
        result = result + shippingCost(result);
        return result;
    }
}
