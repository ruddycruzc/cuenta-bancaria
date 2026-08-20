package com.ruddy.cuentabancaria;

/**
 * Represents a bank account.
 */
public class Cuenta {

    private static final int MESES_POR_ANIO = 12;
    private static final int PORCENTAJE_BASE = 100;

    /** Current account balance. */
    protected float saldo;

    /** Number of deposits made. */
    protected int numeroConsignaciones;

    /** Number of withdrawals made. */
    protected int numeroRetiros;

    /** Annual interest rate as a percentage. */
    protected float tasaAnual;

    /** Monthly commission. */
    protected float comisionMensual;

    /**
     * Creates a bank account.
     *
     * @param saldo initial balance
     * @param tasaAnual annual interest rate
     */
    public Cuenta(float saldo, float tasaAnual) {
        this.saldo = saldo;
        this.tasaAnual = tasaAnual;
        this.numeroConsignaciones = 0;
        this.numeroRetiros = 0;
        this.comisionMensual = 0;
    }

    /**
     * Deposits money into the account.
     *
     * @param cantidad amount to deposit
     */
    public void consignar(float cantidad) {
        saldo += cantidad;
        numeroConsignaciones++;
    }

    /**
     * Withdraws money if there is enough balance.
     *
     * @param cantidad amount to withdraw
     */
    public void retirar(float cantidad) {
        if (cantidad <= saldo) {
            saldo -= cantidad;
            numeroRetiros++;
        }
    }

    /**
     * Calculates and applies the monthly interest.
     */
    public void calcularInteresMensual() {
        float interesMensual = saldo * tasaAnual
                / MESES_POR_ANIO / PORCENTAJE_BASE;
        saldo += interesMensual;
    }

    /**
     * Applies the monthly commission and calculates monthly interest.
     */
    public void extractoMensual() {
        saldo -= comisionMensual;
        calcularInteresMensual();
    }

    /**
     * Returns the account information.
     *
     * @return account information
     */
    public String imprimir() {
        return "Saldo: " + saldo
                + ", Número de consignaciones: " + numeroConsignaciones
                + ", Número de retiros: " + numeroRetiros
                + ", Tasa anual: " + tasaAnual
                + ", Comisión mensual: " + comisionMensual;
    }
}
