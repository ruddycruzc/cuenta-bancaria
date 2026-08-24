package com.ruddy.cuentabancaria;

/**
 * Represents a savings account.
 */
public class CuentaAhorros extends Cuenta {

    private static final float SALDO_MINIMO = 10000.0f;
    private static final int RETIROS_INCLUIDOS = 4;
    private static final float COMISION_RETIRO = 1000.0f;

    /** Indicates whether the account is active. */
    protected boolean activa;

    /**
     * Creates a savings account.
     *
     * @param saldo initial balance
     * @param tasaAnual annual interest rate
     */
    public CuentaAhorros(float saldo, float tasaAnual) {
        super(saldo, tasaAnual);
        activa = saldo >= SALDO_MINIMO;
    }

    /**
     * Deposits money if the account is active.
     *
     * @param cantidad amount to deposit
     */
    @Override
    public void consignar(float cantidad) {
        if (activa) {
            super.consignar(cantidad);
        }
    }

    /**
     * Withdraws money if the account is active.
     *
     * @param cantidad amount to withdraw
     */
    @Override
    public void retirar(float cantidad) {
        if (activa) {
            super.retirar(cantidad);
        }
    }

    /**
     * Calculates the monthly statement.
     */
    @Override
    public void extractoMensual() {
        if (numeroRetiros > RETIROS_INCLUIDOS) {
            int retirosAdicionales = numeroRetiros - RETIROS_INCLUIDOS;
            comisionMensual = retirosAdicionales * COMISION_RETIRO;
        }

        super.extractoMensual();

        activa = saldo >= SALDO_MINIMO;
    }

    /**
     * Returns the account information.
     *
     * @return account information
     */
    @Override
    public String imprimir() {
        return "Saldo: " + saldo
                + ", Comisión mensual: " + comisionMensual
                + ", Número de transacciones: "
                + (numeroConsignaciones + numeroRetiros);
    }
}
