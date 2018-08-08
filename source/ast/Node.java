package ast;

import java.util.HashSet;
import utils.*;

public interface Node {
	/**
	 *
	 * Interfaccia generale della classe nodo, definisce i template delle funzioni
	 * generali necessarie a gestire i nodi del ast che conterranno le informazioni
	 * per gestire eventuali errori e che verranno utilizzati per la generazione del
	 * codice eseguibile.
	 *
	 * */
	
	/**
	 *
	 * La funzione <strong >toPrint</strong> gestisce la creazione della stringa formattata
	 * per la stampa tabulata dell'AST.
	 *
	 * */
	String toPrint(String indent);
	
	/**
	 *
	 * La funzione <strong >checkSemantics</strong> controlla che tutte le istruzioni siano coerenti
	 * con le dichiarazioni effettuate in precedenza.
	 *
	 * */
	HashSet<String> checkSemantics(Environment env);
	
	/**
	 *
	 * La funzione <strong >typeChecking</strong> controlla se esistono incoerenze di tipo tra
	 * le componenti del codice in esame (gestisce anche il sottotipaggio).
	 *
	 * */
	Node typeCheck() throws Exception;
	
	/**
	 *
	 * La funzione <strong >codeGeneration</strong> genera la stringa di codice eseguibile che sarà
	 * input iniziale per la macchina virtuale. Successivamente questa stringa verrà trasformata in
	 * albero a sua volta dalla VM.
	 *
	 * */
	String codeGeneration();
	
	/**
	 *
	 * La funzione <strong >getID()</strong> ritorna la stringa identificativa di un nodo.
	 * Nei nodi in cui tale ID non è significativo, viene restituito null.
	 *
	 * */
	String getID();
	
	/**
	 *
	 * La funzione <strong >copyInstance()</strong> serve per implementare un meccanismo di
	 * deep copy per gli oggetti dell'AST in Java. In generale non viene utilizzato, se non
	 * in alcuni casi speciali come la modifica di un oggetto che è campo di una classe,
	 * in seguito ad un assegnamento. Ciò si è reso necessario per evitare che anche altri
	 * oggetti della stessa classe subissero gli effetti dell'assegnamento.
	 *
	 * */
	Node copyInstance();
}