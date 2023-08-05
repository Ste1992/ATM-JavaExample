package login;

public class Login {
	
	private Login login;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private double saldo;
	private String iban;
	
	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
	public Login(String nome, String cognome, String email, String password, double saldo, String iban) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.saldo = saldo;
		this.iban = iban;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}
	
	public double getSaldo() {
		return saldo;
	}

	public String getIban() {
		return iban;
	}
	
}
