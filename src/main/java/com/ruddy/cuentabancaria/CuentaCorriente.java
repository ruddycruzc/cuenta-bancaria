package com.ruddy.cuentabancaria;

/**
 * Represents a current bank account.
 */
public class CuentaCorriente extends Cuenta {

    /** Overdraft amount of the account. */
    protected float sobregiro;

    /**
     * Creates a current account.
     *
     * @param saldo initial balance
     * @param tasaAnual annual interest rate
     */
    public CuentaCorriente(float saldo, float tasaAnual) {
        super(saldo, tasaAnual);
        this.sobregiro = 0;
    }

    /**
     * Withdraws money from the account.
     * If there is not enough balance, the difference becomes overdraft.
     *
     * @param cantidad amount to withdraw
     */
    @Override
    public void retirar(float cantidad) {
        if (cantidad <= saldo) {
            saldo -= cantidad;
        } else {
            sobregiro += cantidad - saldo;
            saldo = 0;
        }

        numeroRetiros++;
    }

    /**
     * Deposits money into the account.
     * The inherited method is used first.
     *
     * @param cantidad amount to deposit
     */
    @Override
    public void consignar(float cantidad) {
        super.consignar(cantidad);

        if (sobregiro > 0) {
            if (cantidad <= sobregiro) {
                sobregiro -= cantidad;
                saldo -= cantidad;
            } else {
                saldo -= sobregiro;
                sobregiro = 0;
            }
        }
    }

    /**
     * Calculates the monthly statement.
     */
    @Override
    public void extractoMensual() {
        super.extractoMensual();
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
                + ", Total de transacciones: "
                + (numeroConsignaciones + numeroRetiros)
                + ", Sobregiro: " + sobregiro;
    }
}
