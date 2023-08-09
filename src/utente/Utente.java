package utente;

import java.util.Scanner;
import login.Login;
/*
 * Set: E' una struttura dati che contiene un insieme di elementi unici, senza duplicati.
 * Nel contesto della gestione degli utenti, potresti utilizzare un HashSet o un TreeSet per archiviare gli account degli utenti.
 * Questo garantirebbe che ogni account sia unico all'interno del set, evitando duplicati.
 */
import java.util.HashSet;

public class Utente {
	
	private Scanner input = new Scanner(System.in);
	private Login login;
	private Login loginLoggato;
	private Utente utente;
	private HashSet<Login> accounts = new HashSet<>();
	private String nome;
	private String cognome;
	private String IBAN;

	public String getNome() {
		return nome;
	}
	
	public String getCognome() {
		return cognome;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
	public String getIBAN() {
		return IBAN;
	}
	
	/*
	 * Nella classe Utente, assicurati di richiamare il metodo loginRegistrati() per ottenere gli account degli utenti prima di utilizzare il metodo loginUtente() o prelievo().
	 * Puoi chiamare il metodo loginRegistrati() all'interno del costruttore della classe Utente per assicurarti che gli account siano disponibili:
	 */
	public Utente() {
		accounts = loginRegistrati();
	}
	
	public HashSet<Login> loginRegistrati() {
		// Aggiungi gli account degli utenti al set
		accounts.add(new Login("Stefano", "Bianchi", "imthebest@libero.com", "password", 1500.0, "IT015323265974"));
		accounts.add(new Login("Daniele", "Rossi", "imtheworst@outlook.it", "passuord", 1800.0, "IT24630215342"));
		return accounts;
	}
	
	// Restituisci l'insieme di account degli utenti
	public HashSet<Login> getAccounts() {
		return accounts;
	}
	
	
	public void welcome() {
		System.out.println("Benvenuto in FunkBank!\nSeleziona dal menù:\n\t1. Registrati\n\t2. Login\n\t3. Esci");
		System.out.print("\nSelezione: ");
	}

	
	public void registrazioneUtente() {
		System.out.println("Registra un nuovo account\n");
		System.out.print("Nome: ");
		nome = input.next();
		System.out.print("Cognome: ");
		cognome = input.next();
		boolean emailEsistente;
		do {
			System.out.print("Email: ");
			input.nextLine();
			String email = input.nextLine();
			System.out.print("Password: ");
			String password = input.next();
			emailEsistente = accounts.stream().anyMatch(login -> login.getEmail().contains(email));
			if (emailEsistente) {
				System.out.println("Email già registrata. Riprova.");
			} else {
				System.out.println("Utente registrato!\nRiepilogo:\n" + nome + " " + cognome + ", email: " + email
						+ ", password: " + password);
				accounts.add(new Login(nome, cognome, email, password, 0.0, "IBAN"));
				break;
			}
		} while(emailEsistente);
	}
	
	
	public void loginUtente() {
		System.out.println("Effettua il login\n");
		boolean loginTrovato;
		boolean passwordCorretta = false;
		do {
			System.out.print("Email: ");
			String email = input.next();
			System.out.print("Password: ");
			String password = input.next();
			login = new Login("nome", "cognome", email, password, 0.0, "IBAN");
			loginTrovato = accounts.stream().anyMatch(login -> login.getEmail().contains(email));
			passwordCorretta = false;
			
			if (loginTrovato) {
				passwordCorretta = accounts.stream().filter(acc -> acc.getEmail().equals(email)).anyMatch(acc -> acc.getPassword().equals(password));
				if(passwordCorretta) {
					String nomeUtente = accounts.stream().filter(acc -> acc.getEmail().equals(email)).findFirst().get().getNome();
					String cognomeUtente = accounts.stream().filter(acc -> acc.getEmail().equals(email)).findFirst().get().getCognome();
					System.out.println("Benvenuto " + nomeUtente + " " + cognomeUtente);
					loginLoggato = accounts.stream().filter(acc -> acc.getEmail().equals(email)).findFirst().get();
					loginLoggato.setLogin(loginLoggato);
					break;					
				} else {
					System.out.println("Password errata!");
				}
			} else {
				System.out.println("Email o password errati!");
			}
		} while(!loginTrovato || !passwordCorretta);
	}
	
	
	public void prelievo() {
		loginLoggato.getLogin();
		double quantità;
		double saldoUtente = loginLoggato.getSaldo();
		System.out.println("Saldo: €" + saldoUtente);
		System.out.print("Quanto vuoi prelevare? €");
		quantità = input.nextDouble();
		boolean prelievoOk;
		do {
			if(quantità > loginLoggato.getSaldo()) {
				System.out.println("L'importo supera il saldo disponibile.");
				prelievoOk = false;
				break;
			} else {
				System.out.println("Importo prelevato: €" + quantità);
				double nuovoSaldo = (loginLoggato.getSaldo() - quantità);
				System.out.println("Saldo corrente: €" + nuovoSaldo);
				prelievoOk = true;
				break;
			}
		} while(!prelievoOk);
	}
	
	
	public void deposito() {
		System.out.println("Saldo: €" + loginLoggato.getSaldo());
		System.out.print("Quanto vuoi depositare? € ");
		double deposito = input.nextDouble();
		double nuovoSaldo = loginLoggato.getSaldo() + deposito;
		System.out.println("Saldo corrente: €" + nuovoSaldo);
	}
	
	
	public void bonifico() {
		boolean bonificoOk = true;
		do {
			System.out.println("Il tuo saldo è: " + loginLoggato.getSaldo());
			System.out.print("Nome del ricevente: ");
			nome = input.next();
			System.out.print("Cognome del ricevente: ");
			cognome = input.next();
			System.out.print("IBAN: ");
			IBAN = input.next();
			System.out.print("Inserisci l'importo: €");
			double importo = input.nextDouble();
			nome = accounts.stream().filter(acc -> acc.getNome().equals(nome)).findFirst().get().getNome();
			cognome = accounts.stream().filter(acc -> acc.getCognome().equals(cognome)).findFirst().get().getCognome();
			boolean ibanTrovato = accounts.stream().filter(acc -> acc.getNome().equals(nome)).anyMatch(acc -> acc.getIban().equals(IBAN));
			double saldoRicevente = accounts.stream().filter(acc -> acc.getIban().equals(IBAN)).findFirst().get().getSaldo();
			
			if(nome.equals(getNome()) && cognome.equals(getCognome()) && ibanTrovato) {
				
				if(importo <= loginLoggato.getSaldo()) {
					double saldoUtente = loginLoggato.getSaldo() - importo;
					System.out.println("Il tuo saldo corrente è: €" + saldoUtente);
					System.out.println("Bonifico effettuato con successo!");
					double nuovoSaldoRicevente = saldoRicevente + importo;
					System.out.println("\nNuovo saldo ricevente: €" + nuovoSaldoRicevente);
					bonificoOk = true;
					break;
				} else if(importo > loginLoggato.getSaldo()) {
					System.out.println("L'importo supera il saldo disponibile!");
					bonificoOk = false;
					break;
				}
				
			} else {
				System.out.println("Dati inseriti errati!");
				bonificoOk = false;
				break;
			}
		} while(!bonificoOk);
	}
	
	
	// Controllare questo metodo
	public void logout() {
		System.out.print("Sei sicuro di voler uscire? Y/N ");
		String choice = input.next();
		while (!choice.equalsIgnoreCase("Y") && !choice.equalsIgnoreCase("N")) {
			if (choice.equalsIgnoreCase("Y")) {
				System.out.println("Logout effettuato con successo!");
				System.out.print("Sei sicuro di voler uscire? Y/N ");
				choice = input.next();
				break;
			} else if (choice.equalsIgnoreCase("N")) {
				System.out.println();
			} else {
				System.out.println("Scelta errata!");
			}
		}
	}
}
