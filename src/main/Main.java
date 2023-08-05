package main;

import java.util.Scanner;
import utente.Utente;

public class Main {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		Utente utente = new Utente();
		boolean logged = false; // Inizialmente l'utente non è loggato
		boolean toLogin = false;
		utente.loginRegistrati();
		utente.welcome();
		
		while(!logged) {
			int scelta = input.nextInt();
			switch(scelta) {
			
				case 1:
					utente.registrazioneUtente();
					toLogin = true;
					
					if(toLogin) {
						utente.loginUtente();
						System.out.print("\nMenù:\n\t1. Deposito\n\t2. Prelievo\n\t3. Bonifico\nSelezione: ");
						scelta = input.nextInt();
						
						switch(scelta) {
							case 1:
								utente.deposito();
								break;
							case 2:
								utente.prelievo();
								break;
							case 3:
								utente.bonifico();
								break;
						}
						break;
						
					} else {
						System.out.println();
						break;
					}
					
				case 2:
					utente.loginUtente();
					System.out.print("\nMenù:\n\t1. Deposito\n\t2. Prelievo\n\t3. Bonifico\nSelezione: ");
					scelta = input.nextInt();
					switch(scelta) {
						case 1:
							utente.deposito();
							break;
						case 2:
							utente.prelievo();
							break;
						case 3:
							utente.bonifico();
							break;
					}
					break;
					
				case 3:
					utente.logout();
						System.out.print("Vuoi effettuare un'altra operazione? Y/N ");
						String choice = input.next();
						if(choice.equalsIgnoreCase("Y")) {
							
						} else if(choice.equalsIgnoreCase("N")) {
							System.out.println("Gazie e arrivederci!");
							logged = true;
							
						} else {
							System.out.println("Scelta errata.");
						}
						
				default:
					System.out.println("Scelta errata! Riprova.");
					break;
			}
		}
		input.close();
	}

}
