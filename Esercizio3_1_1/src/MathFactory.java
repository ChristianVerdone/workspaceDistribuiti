
public interface MathFactory {
    // Metodo che crea un'istanza di Math in base al valore booleano passato come parametro.
    // Se ao è true, crea un Active Object per Math, altrimenti crea un'istanza normale di Math.
    public Math create(boolean ao);
}