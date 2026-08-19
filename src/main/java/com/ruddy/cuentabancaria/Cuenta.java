package com.ruddy.cuentabancaria;

public class Cuenta {
    protected float saldo;
    protected int numeroConsignaciones;
    protected int numeroRetiros;
    protected float tasaAnual;
    protected float comisionMensual;

    public Cuenta(float saldo, float tasaAnual) {
        this.saldo = saldo;
        this.tasaAnual = tasaAnual;
        this.numeroConsignaciones = 0;
        this.numeroRetiros = 0;
        this.comisionMensual = 0;
    }

        public void consignar(float cantidad) {
        saldo += cantidad;
        numeroConsignaciones++;
    }

        public void retirar(float cantidad) {
        if (cantidad <= saldo) {
            saldo -= cantidad;
            numeroRetiros++;
        }
    }

        public void calcularInteresMensual() {
        float interesMensual = saldo * tasaAnual / 12 / 100;
        saldo += interesMensual;
    }

        public void extractoMensual() {
        saldo -= comisionMensual;
        calcularInteresMensual();
    }

    
    public String imprimir() {
        return "Saldo: " + saldo
                + ", Número de consignaciones: " + numeroConsignaciones
                + ", Número de retiros: " + numeroRetiros
                + ", Tasa anual: " + tasaAnual
                + ", Comisión mensual: " + comisionMensual;
    }
}
