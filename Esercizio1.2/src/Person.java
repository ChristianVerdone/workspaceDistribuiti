import java.io.Serializable;

// Definisce la classe Person che implementa Serializable
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;  // Numero di versione per la serializzazione
    private String nome, cognome;  // Campi per il nome e il cognome
    private int age;  // Campo per l'età

    // Costruttore vuoto
    public Person() {
    }

    // Costruttore con parametri per inizializzare i campi
    public Person(String nome, String cognome, int a) {
        this.nome = nome;
        this.cognome = cognome;
        this.age = a;
    }

    // Override del metodo toString per fornire una rappresentazione testuale dell'oggetto
    @Override
    public String toString() {
        return nome + ' ' + cognome + ' ' + age + " anni";
    }

    // Metodi getter e setter per i campi
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
}

/* In breve:
// - La classe "Person" rappresenta una persona con nome, cognome ed età.
// - Implementa l'interfaccia Serializable, il che significa che gli oggetti di questa classe possono essere serializzati (ad esempio, scritti su file o inviati tramite rete).
// - Il campo "serialVersionUID" è un numero di versione utilizzato per la serializzazione.
*/