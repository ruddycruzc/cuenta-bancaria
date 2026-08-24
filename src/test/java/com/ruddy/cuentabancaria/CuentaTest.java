package com.ruddy.cuentabancaria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the Cuenta class.
 */
class CuentaTest {

    private static final float SALDO_INICIAL = 10000.0f;
    private static final float TASA_ANUAL = 12.0f;

    private static final float CANTIDAD_CONSIGNADA = 2500.0f;
    private static final float SALDO_DESPUES_CONSIGNACION = 12500.0f;

    private static final float CANTIDAD_RETIRO = 2500.0f;
    private static final float SALDO_DESPUES_RETIRO = 7500.0f;

    private static final float CANTIDAD_RETIRO_MAYOR_AL_SALDO = 15000.0f;

    @Test
    void deberiaInicializarLaCuentaCorrectamente() {
        Cuenta cuenta = new Cuenta(SALDO_INICIAL, TASA_ANUAL);

        assertEquals(SALDO_INICIAL, cuenta.saldo);
        assertEquals(TASA_ANUAL, cuenta.tasaAnual);
        assertEquals(0, cuenta.numeroConsignaciones);
        assertEquals(0, cuenta.numeroRetiros);
        assertEquals(0.0f, cuenta.comisionMensual);
    }

    @Test
    void deberiaConsignarDinero() {
        Cuenta cuenta = new Cuenta(SALDO_INICIAL, TASA_ANUAL);

        cuenta.consignar(CANTIDAD_CONSIGNADA);

        assertEquals(SALDO_DESPUES_CONSIGNACION, cuenta.saldo);
        assertEquals(1, cuenta.numeroConsignaciones);
    }

    @Test
    void deberiaRetirarDinero() {
        Cuenta cuenta = new Cuenta(SALDO_INICIAL, TASA_ANUAL);

        cuenta.retirar(CANTIDAD_RETIRO);

        assertEquals(SALDO_DESPUES_RETIRO, cuenta.saldo);
        assertEquals(1, cuenta.numeroRetiros);
    }

    @Test
    void noDeberiaRetirarMasDineroDelSaldo() {
        Cuenta cuenta = new Cuenta(SALDO_INICIAL, TASA_ANUAL);

        cuenta.retirar(CANTIDAD_RETIRO_MAYOR_AL_SALDO);

        assertEquals(SALDO_INICIAL, cuenta.saldo);
        assertEquals(0, cuenta.numeroRetiros);
    }
}
