package com.devops.cicd;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

public class PricingConfigLoader {

    public PricingConfig load() {
        Properties props = new Properties();
        try (InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream("app.properties")) {

            if (is == null) {
                throw new IllegalStateException("app.properties not found in classpath");
            }

            props.load(is);

        } catch (IOException e) {
            throw new UncheckedIOException("Failed to load app.properties", e);
        }

        double vatRatePercent = Double.parseDouble(required(props, "vatRate"));
        double freeShippingThreshold = Double.parseDouble(required(props, "freeShippingThreshold"));

        // conversion
        double vatRate = vatRatePercent / 100.0;

        return new PricingConfig(vatRate, freeShippingThreshold);
    }

    private String required(Properties props, String key) {
        String value = props.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Missing required property: " + key);
        }
        return value.trim();
    }
}