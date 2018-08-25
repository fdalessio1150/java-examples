package com.example.generator;

import java.io.FileWriter;
import java.io.IOException;

public class DataGenerator {
	
	public static void main(String[] args) throws IOException {
		FileWriter fw = new FileWriter("C:\\Users\\Sergio\\Desktop\\out.txt");
		
		for (int i = 1; i <= 9999; i++) {
			for (int j = 1; j <= 99; j++) {
				int somaSaldo = 0;
				int somaLancamento = 0;
				
				for (int h = 1; h <= 10; h++) {
					int saldo = i + j + h;
					int lancamento = saldo + 5;

					if (h >= 1 && h <= 5) {
						somaSaldo = saldo + somaSaldo;
						fw.write("0341"); // Banco
						fw.write(String.format("%04d", i)); //Agencia
						fw.write(String.format("%07d", j)); //Conta
						fw.write("9"); // DAC
						fw.write("111"); // Tipo de lancamento
						fw.write(String.format("%010d", saldo)); // Saldo
						fw.write("\n"); // Pular linha
					}
					if (h > 5) {
						somaLancamento = lancamento + somaLancamento;
						fw.write("0341"); // Banco
						fw.write(String.format("%04d", i)); //Agencia
						fw.write(String.format("%07d", j)); //Conta
						fw.write("9"); // DAC
						fw.write("222"); // Tipo de lancamento
						fw.write(String.format("%010d", lancamento)); // Lancamento
						fw.write("\n"); // Pular linha
					}

					// Gerar totalizador
					if (h == 10) {
						fw.write("0341"); // Banco
						fw.write(String.format("%04d", i)); //Agencia
						fw.write(String.format("%010d", somaSaldo)); // Soma de saldo
						fw.write(String.format("%010d", somaLancamento)); // Soma de Lancamento
						fw.write("\n"); // Pular linha
					}
				}
			}
		}
	 
		fw.close();
	}

}
