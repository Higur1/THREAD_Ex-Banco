package view;
import java.util.concurrent.Semaphore;


import controller.CaixaEletronico;


public class Painel {

	public static void main(String[] args) {

		int perm = 1;
		Semaphore semaforo = new Semaphore(perm);
		
		for(int Pessoa = 1; Pessoa <= 20; Pessoa++) {
			int codigo = (int)(Math.random()*101);
            double saldo = (Math.random()*1001);
            double valor = (Math.random()*1001);
			Thread iniciar = new CaixaEletronico (codigo, saldo, valor, semaforo);
			iniciar.start();
		}
	}
}
