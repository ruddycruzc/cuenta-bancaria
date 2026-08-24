package com.ruddy.cuentabancaria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the CuentaAhorros class.
 */
class CuentaAhorrosTest {

    private static final float SALDO_MINIMO = 10000.0f;
    private static final float SALDO_INFERIOR = 9000.0f;
    private static final float TASA_ANUAL = 12.0f;

    private static final float CANTIDAD_CONSIGNADA = 2500.0f;
    private static final float SALDO_DESPUES_CONSIGNACION = 12500.0f;

    private static final float CANTIDAD_RETIRO = 2500.0f;
    private static final float SALDO_DESPUES_RETIRO = 7500.0f;

    @Test
    void deberiaEstarActivaConSaldoMinimo() {
        CuentaAhorros cuenta =
                new CuentaAhorros(SALDO_MINIMO, TASA_ANUAL);

        assertTrue(cuenta.activa);
    }

    @Test
    void deberiaEstarInactivaConSaldoInferiorAlMinimo() {
        CuentaAhorros cuenta =
                new CuentaAhorros(SALDO_INFERIOR, TASA_ANUAL);

        assertFalse(cuenta.activa);
    }

    @Test
    void deberiaConsignarSiLaCuentaEstaActiva() {
        CuentaAhorros cuenta =
                new CuentaAhorros(SALDO_MINIMO, TASA_ANUAL);

        cuenta.consignar(CANTIDAD_CONSIGNADA);

        assertEquals(SALDO_DESPUES_CONSIGNACION, cuenta.saldo);
        assertEquals(1, cuenta.numeroConsignaciones);
    }

    @Test
    void noDeberiaConsignarSiLaCuentaEstaInactiva() {
        CuentaAhorros cuenta =
                new CuentaAhorros(SALDO_INFERIOR, TASA_ANUAL);

        cuenta.consignar(CANTIDAD_CONSIGNADA);

        assertEquals(SALDO_INFERIOR, cuenta.saldo);
        assertEquals(0, cuenta.numeroConsignaciones);
    }

    @Test
    void deberiaRetirarSiLaCuentaEstaActiva() {
        CuentaAhorros cuenta =
                new CuentaAhorros(SALDO_MINIMO, TASA_ANUAL);

        cuenta.retirar(CANTIDAD_RETIRO);

        assertEquals(SALDO_DESPUES_RETIRO, cuenta.saldo);
        assertEquals(1, cuenta.numeroRetiros);
    }

    @Test
    void noDeberiaRetirarSiLaCuentaEstaInactiva() {
        CuentaAhorros cuenta =
                new CuentaAhorros(SALDO_INFERIOR, TASA_ANUAL);

        cuenta.retirar(CANTIDAD_RETIRO);

        assertEquals(SALDO_INFERIOR, cuenta.saldo);
        assertEquals(0, cuenta.numeroRetiros);
    }

    @Test
    void deberiaDesactivarseCuandoElSaldoBajaDelMinimo() {
        CuentaAhorros cuenta =
                new CuentaAhorros(SALDO_MINIMO, TASA_ANUAL);

        cuenta.retirar(CANTIDAD_RETIRO);
        cuenta.extractoMensual();

        assertFalse(cuenta.activa);
    }
}
