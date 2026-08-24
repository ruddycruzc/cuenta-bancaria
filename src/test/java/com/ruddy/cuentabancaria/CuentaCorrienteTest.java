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

    private static final float COMISION_MENSUAL = 100.0f;
    private static final float SALDO_DESPUES_EXTRACTO = 9999.0f;

    private static final float SALDO_IMPRIMIR = 10000.0f;
    private static final float CANTIDAD_CONSIGNADA_IMPRIMIR = 1000.0f;
    private static final float CANTIDAD_RETIRO_IMPRIMIR = 1000.0f;
    private static final float COMISION_IMPRIMIR = 500.0f;

    private static final String DATOS_CUENTA = "Saldo: 10000.0"
            + ", Comisión mensual: 500.0"
            + ", Número de transacciones: 2"
            + ", Sobregiro: 0.0";

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

    @Test
    void deberiaAplicarElExtractoMensual() {
        CuentaCorriente cuenta = new CuentaCorriente(
                SALDO_INICIAL,
                TASA_ANUAL);

        cuenta.comisionMensual = COMISION_MENSUAL;
        cuenta.extractoMensual();

        assertEquals(SALDO_DESPUES_EXTRACTO, cuenta.saldo);
    }

    @Test
    void deberiaImprimirLosDatosDeLaCuenta() {
        CuentaCorriente cuenta = new CuentaCorriente(
                SALDO_IMPRIMIR,
                TASA_ANUAL);

        cuenta.comisionMensual = COMISION_IMPRIMIR;

        cuenta.consignar(CANTIDAD_CONSIGNADA_IMPRIMIR);
        cuenta.retirar(CANTIDAD_RETIRO_IMPRIMIR);

        assertEquals(DATOS_CUENTA, cuenta.imprimir());
    }
}
