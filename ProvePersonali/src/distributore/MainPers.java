/*
Partendo dai diagrammi di flusso realizzati in precedenza, programmare il software
 per la gestione del distributore di bevande.

Nel progetto dovranno essere utilizzati tutti i concetti visti fino a questo momento,
inoltre il codice dovrà essere integrato/migliorato
con i concetti della OOP che impareremo nei prossimi giorni.

New feature: gestione operatore. L'operatore può, inserendo un codice riservato,
accedere ad un menu di selezione con cui
poter fare le seguenti operazioni:

aggiungere un nuovo prodotto
rimuovere un prodotto
cambiare la quantità disponibile di un prodotto
cambiare il prezzo di un prodotto
Inoltre, l'operatore potrà vedere:

totale incassato dal distributore
elenco dei prodotti acquistati con relative quantità (es: caffè 10, ginseng 18, coca 4)
 */

//LEGENDA DISTRIBUTORE: da 1 a 100 caffetteria, da 101 a 200 bevande fredde, da 200 in su alcolici

package distributore;

import java.util.Scanner;

public class MainPers {
	
	static void menu(Scanner scanner) {
		AdminPers admin = new AdminPers();
		int scelta = 0;
		
		do {
			System.out.println("MENU' DISTRIBUTORE:\n1) Acquista prodotto\n"
					+ "2) Visualizza lista prodotti\n3) Menù gestore"
					+ "\n4) Esci.");
			scelta=scanner.nextInt();
			switch(scelta) {
			case 1:
				scanner.nextLine();
				System.out.println("Inserisci nome categoria: ");
				String filtro = scanner.nextLine();
				if(admin.filtraPerCategoria(filtro)) {
					System.out.println("Inserisci codice prodotto: ");
					int codice = scanner.nextInt();
					if (admin.controlloEta(codice, scanner)) admin.gestioneAcquisto(codice);
				}
				break;
			case 2:
				admin.stampaTotale();
				break;
			case 3:
				if (admin.gestioneAccesso(scanner)) {
					admin.menuGestore(scanner);
				}
				break;
			case 4:
				System.out.println("Uscita dal programma in corso.");
				break;
			default:
				System.out.println("Codice errato.");
			}
		}while(scelta!=4);
		
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		menu(scanner);
		scanner.close();
		System.exit(0);
	}
}
