package com.ruddy.cuentabancaria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the CuentaCorriente class.
 */
class CuentaCorrienteTest {

    private static final float SALDO_INICIAL = 10000.0f;
    private static final float TASA_ANUAL = 12.0f;
    private static final float CANTIDAD_RETIRO = 15000.0f;
    private static final float SALDO_DESPUES_RETIRO = 0.0f;
    private static final float SOBREGIRO_ESPERADO = 5000.0f;
    private static final float CANTIDAD_CONSIGNADA = 3000.0f;
    private static final float SOBREGIRO_DESPUES_CONSIGNACION = 2000.0f;

    @Test
    void deberiaInicializarElSobregiroEnCero() {
        CuentaCorriente cuenta = new CuentaCorriente(SALDO_INICIAL, TASA_ANUAL);

        assertEquals(0.0f, cuenta.sobregiro);
    }

    @Test
    void deberiaPermitirRetirarPorEncimaDelSaldo() {
        CuentaCorriente cuenta = new CuentaCorriente(SALDO_INICIAL, TASA_ANUAL);

        cuenta.retirar(CANTIDAD_RETIRO);

        assertEquals(SALDO_DESPUES_RETIRO, cuenta.saldo);
        assertEquals(SOBREGIRO_ESPERADO, cuenta.sobregiro);
        assertEquals(1, cuenta.numeroRetiros);
    }

    @Test
    void deberiaReducirElSobregiroAlConsignar() {
        CuentaCorriente cuenta = new CuentaCorriente(SALDO_INICIAL, TASA_ANUAL);

        cuenta.retirar(CANTIDAD_RETIRO);
        cuenta.consignar(CANTIDAD_CONSIGNADA);

        assertEquals(SOBREGIRO_DESPUES_CONSIGNACION, cuenta.sobregiro);
        assertEquals(1, cuenta.numeroConsignaciones);
    }

}
