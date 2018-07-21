package ast;

import java.util.ArrayList;
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
	 * per la stampa tabulata del ast.
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
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	String getID();
	
	void setClassID (String id);
}