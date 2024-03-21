//Specifica un�operazione matematica che ritorna void dato che il risultato prodotto 
//verr� restituito in maniera asincrona da una callback o semplicemente stampato.
//Non implementa Remote.

public interface Math {
	public void add(float x, float y); //non restituisce nulla perchè deve essere asincrona. //Object se voglio rendere più trasparente l'esecuzione
}
