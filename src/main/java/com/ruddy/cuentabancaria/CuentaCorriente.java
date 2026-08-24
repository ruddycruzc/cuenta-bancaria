package com.ruddy.cuentabancaria;

/**
 * Represents a current bank account.
 */
public class CuentaCorriente extends Cuenta {

    /** Account overdraft amount. */
    protected float sobregiro;

    /**
     * Creates a current account.
     *
     * @param saldo initial balance
     * @param tasaAnual annual interest rate
     */
    public CuentaCorriente(float saldo, float tasaAnual) {
        super(saldo, tasaAnual);
        sobregiro = 0;
    }

    /**
     * Withdraws money from the account.
     * If there is not enough balance, the difference becomes an overdraft.
     *
     * @param cantidad amount to withdraw
     */
    @Override
    public void retirar(float cantidad) {
        if (cantidad <= saldo) {
            super.retirar(cantidad);
        } else {
            sobregiro += cantidad - saldo;
            saldo = 0;
            numeroRetiros++;
        }
    }

    /**
     * Deposits money into the account.
     * The deposit first pays the existing overdraft.
     *
     * @param cantidad amount to deposit
     */
    @Override
    public void consignar(float cantidad) {
        if (sobregiro > 0) {
            if (cantidad <= sobregiro) {
                sobregiro -= cantidad;
            } else {
                saldo += cantidad - sobregiro;
                sobregiro = 0;
            }
            numeroConsignaciones++;
        } else {
            super.consignar(cantidad);
        }
    }

    /**
     * Returns the current overdraft.
     *
     * @return overdraft amount
     */
    public float getSobregiro() {
        return sobregiro;
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
                + (numeroConsignaciones + numeroRetiros)
                + ", Sobregiro: " + sobregiro;
    }
}
