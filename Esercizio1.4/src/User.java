import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
    private String nome,cognome;
    private int age;
    
    public User() {}
    
    

    public User(String nome, String cognome, int a) {
        this.nome = nome;
        this.cognome = cognome;
        age = a;
    }

    @Override
    public String toString() {
        return  nome + ' ' + cognome + ' ' + age;
    }



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getCognome() {
		return cognome;
	}



	public void setCognome(String cognome) {
		this.cognome = cognome;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}
    
    //get e set
}
