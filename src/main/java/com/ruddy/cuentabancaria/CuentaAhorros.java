package com.ruddy.cuentabancaria;

/**
 * Represents a savings bank account.
 */
public class CuentaAhorros extends Cuenta {

    private static final float SALDO_MINIMO = 10000.0f;

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
        this.activa = saldo >= SALDO_MINIMO;
    }

    /**
     * Deposits money if the account is active.
     *
     * @param cantidad amount to deposit
     */
    @Override
    public void consignar(float cantidad) {
        if (!activa) {
            return;
        }

        saldo += cantidad;
        numeroConsignaciones++;
    }

    /**
     * Withdraws money if the account is active and has enough balance.
     *
     * @param cantidad amount to withdraw
     */
    @Override
    public void retirar(float cantidad) {
        if (activa && cantidad <= saldo) {
            saldo -= cantidad;
            numeroRetiros++;
        }
    }

    /**
     * Returns the account information.
     *
     * @return account information
     */
    @Override
    public String imprimir() {
        return "Saldo: " + saldo
                + ", Consignaciones: " + numeroConsignaciones
                + ", Retiros: " + numeroRetiros
                + ", Tasa anual: " + tasaAnual
                + ", Comisión mensual: " + comisionMensual;
    }
}
