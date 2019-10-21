package com.m78159.pdm;

public class Main9_2ActivityDados {

    private String dataHora;
    private double valor1;
    private double valor2;
    private double resultado;
    private String operacao;

    public Main9_2ActivityDados(String dataHora, double valor1, double valor2, double resultado, String operacao){
       this.setDataHora(dataHora);
       this.setValor1(valor1);
       this.setValor2(valor2);
       this.setResultado(resultado);
       this.setOperacao(operacao);
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public double getValor1() {
        return valor1;
    }

    public void setValor1(double valor1) {
        this.valor1 = valor1;
    }

    public double getValor2() {
        return valor2;
    }

    public void setValor2(double valor2) {
        this.valor2 = valor2;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }
}
