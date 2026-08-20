package com.ruddy.cuentabancaria;

/**
 * Represents a savings bank account.
 */
public class CuentaAhorros extends Cuenta {

    private static final float SALDO_MINIMO_ACTIVA = 10000.0f;

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
        this.activa = saldo >= SALDO_MINIMO_ACTIVA;
    }
}
