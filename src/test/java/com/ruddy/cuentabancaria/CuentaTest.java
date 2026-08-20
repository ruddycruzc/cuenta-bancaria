package com.ruddy.cuentabancaria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the Cuenta class.
 */
class CuentaTest {

    private static final float SALDO_INICIAL = 10000.0f;
    private static final float TASA_ANUAL = 12.0f;

    @Test
    void deberiaInicializarLaCuentaCorrectamente() {
        Cuenta cuenta = new Cuenta(SALDO_INICIAL, TASA_ANUAL);

        assertEquals(SALDO_INICIAL, cuenta.saldo);
        assertEquals(TASA_ANUAL, cuenta.tasaAnual);
        assertEquals(0, cuenta.numeroConsignaciones);
        assertEquals(0, cuenta.numeroRetiros);
        assertEquals(0.0f, cuenta.comisionMensual);
    }
}
