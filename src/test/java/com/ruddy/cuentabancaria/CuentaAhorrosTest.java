package com.ruddy.cuentabancaria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the CuentaAhorros class.
 */
class CuentaAhorrosTest {

    private static final float SALDO_MINIMO = 10000.0f;
    private static final float SALDO_INFERIOR = 9000.0f;
    private static final float TASA_ANUAL = 12.0f;

    @Test
    void deberiaEstarActivaConSaldoMinimo() {
        CuentaAhorros cuenta = new CuentaAhorros(SALDO_MINIMO, TASA_ANUAL);

        assertTrue(cuenta.activa);
    }

    @Test
    void deberiaEstarInactivaConSaldoInferiorAlMinimo() {
        CuentaAhorros cuenta = new CuentaAhorros(SALDO_INFERIOR, TASA_ANUAL);

        assertFalse(cuenta.activa);
    }
}
