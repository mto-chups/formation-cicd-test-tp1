package com.devops.cicd;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordPolicyTest {

    @Test
    void essaiPassword(){
        assertEquals(true, PasswordPolicy.isStrong("Azerty9!"));
    }
}
