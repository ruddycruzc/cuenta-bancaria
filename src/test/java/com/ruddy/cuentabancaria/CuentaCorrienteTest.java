package com.ruddy.cuentabancaria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the CuentaCorriente class.
 */
class CuentaCorrienteTest {

    private static final float SALDO_INICIAL = 10000.0f;
    private static final float TASA_ANUAL = 12.0f;
    private static final float CANTIDAD_RETIRO = 12000.0f;
    private static final float SOBREGIRO_ESPERADO = 2000.0f;

    private static final float CANTIDAD_CONSIGNADA = 1000.0f;
    private static final float SOBREGIRO_DESPUES_CONSIGNACION = 1000.0f;

    private static final float CANTIDAD_CONSIGNADA_MAYOR = 3000.0f;
    private static final float SALDO_DESPUES_CONSIGNACION_MAYOR = 1000.0f;

    @Test
    void deberiaInicializarElSobregiroEnCero() {
        CuentaCorriente cuenta = new CuentaCorriente(
                SALDO_INICIAL,
                TASA_ANUAL);

        assertEquals(0.0f, cuenta.sobregiro);
    }

    @Test
    void deberiaCrearSobregiroAlRetirarMasQueElSaldo() {
        CuentaCorriente cuenta = new CuentaCorriente(
                SALDO_INICIAL,
                TASA_ANUAL);

        cuenta.retirar(CANTIDAD_RETIRO);

        assertEquals(0.0f, cuenta.saldo);
        assertEquals(SOBREGIRO_ESPERADO, cuenta.sobregiro);
        assertEquals(1, cuenta.numeroRetiros);
    }

    @Test
    void deberiaReducirElSobregiroAlConsignar() {
        CuentaCorriente cuenta = new CuentaCorriente(
                SALDO_INICIAL,
                TASA_ANUAL);

        cuenta.retirar(CANTIDAD_RETIRO);
        cuenta.consignar(CANTIDAD_CONSIGNADA);

        assertEquals(SOBREGIRO_DESPUES_CONSIGNACION, cuenta.sobregiro);
        assertEquals(0.0f, cuenta.saldo);
        assertEquals(1, cuenta.numeroConsignaciones);
    }

    @Test
    void deberiaPasarElExcesoDeConsignacionAlSaldo() {
        CuentaCorriente cuenta = new CuentaCorriente(
                SALDO_INICIAL,
                TASA_ANUAL);

        cuenta.retirar(CANTIDAD_RETIRO);
        cuenta.consignar(CANTIDAD_CONSIGNADA_MAYOR);

        assertEquals(SALDO_DESPUES_CONSIGNACION_MAYOR, cuenta.saldo);
        assertEquals(0.0f, cuenta.sobregiro);
        assertEquals(1, cuenta.numeroConsignaciones);
    }
}
