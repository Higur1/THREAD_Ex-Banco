package controller;

import java.text.DecimalFormat;
import java.util.concurrent.Semaphore;


public class CaixaEletronico extends Thread{
	
	private int codigo;
	private double saldo;
	private double valorTransacionado;
	private Semaphore semaforo;
	
	DecimalFormat df = new DecimalFormat("#,###.##");
	
	public CaixaEletronico(int codigo, double saldo, double valorTransacionado, Semaphore semaforo) {
		this.codigo = codigo;
		this.saldo = saldo;
		this.valorTransacionado = valorTransacionado;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		if (codigo% 2 == 0){
			try {
				semaforo.acquire();
				Soma();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo.release();
			}
		}
		else {
			if(!(saldo <= 0)) {
				try {
					semaforo.acquire();
					Subtracao();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaforo.release();
				}
			
			}
			else {
				System.out.println("Saldo insuficiente!!");
			}
		}
	}
	private void Soma() {
		System.out.print("Saldo anterior: R$" + df.format(saldo));
		saldo += valorTransacionado;
		System.out.println(" // Valor do deposito: R$"+df.format(valorTransacionado)+" // Novo saldo: R$" + df.format(saldo));
	}
	private void Subtracao() {
		if (valorTransacionado > saldo) {
			valorTransacionado = saldo;
		}
		System.out.print("Saldo anterior: R$" + df.format(saldo));
		saldo -= valorTransacionado;
		System.out.println(" // Valor do saque: R$" +df.format(valorTransacionado)+ " // Novo saldo: R$" + df.format(saldo));
	}
	
	
}
