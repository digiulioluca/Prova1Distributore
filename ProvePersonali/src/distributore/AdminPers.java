package distributore;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminPers {
	private final int pin = 1234;

	ArrayList<ProdottoPers> lista = new ArrayList<ProdottoPers>();
	ArrayList<Double> guadagniTot = new ArrayList<Double>();
	ArrayList<ProdottoPers> listaAcquisti = new ArrayList<ProdottoPers>();
	
	public void aggiungiElemento(ProdottoPers prodotto) {
		boolean trovato=false;
		for(ProdottoPers inserimento:lista) {
			if(inserimento.nome.equalsIgnoreCase(prodotto.nome)) {
				System.out.println("Prodotto già esistente, aggiornamento quantità");
				inserimento.qta += prodotto.qta;
				trovato = true;
			}
			
			if(inserimento.codice==prodotto.codice) {
				System.out.println("Codice già inserito. Nuovo prodotto non accettato.");
				trovato=true;
			}
		}
		if (trovato==false) {
			lista.add(prodotto);
			System.out.println("Prodotto aggiunto con successo!\n");
		}
	}
	
	public void rimuoviElemento(int codice) {
		boolean trovato=false;
		for(ProdottoPers rimozione:lista) {
			if(rimozione.codice==codice) {
				trovato=true;
				System.out.println("Prodotto "+rimozione.nome+" rimosso.");
				lista.remove(rimozione);
			}
		}
		if (trovato==false) System.out.println("Codice non trovato.");
	}
	
	public void cambiaPrezzo(int codice, Scanner scanner) {
		boolean trovato=false;
		for(ProdottoPers newPrezzo:lista) {
			if(newPrezzo.codice==codice) {
				trovato=true;
				System.out.println("Inserire nuovo prezzo per "+newPrezzo.nome+": ");
				newPrezzo.setPrezzo(scanner.nextDouble());
			}
		}
		if (trovato==false) System.out.println("Codice non trovato.");
	}
	
	public void cambiaQta(int codice, Scanner scanner) {
		boolean trovato=false;
		for(ProdottoPers newQta:lista) {
			if(newQta.codice==codice) {
				trovato=true;
				System.out.println("Inserire nuova quantità per "+newQta.nome+": ");
				newQta.setQta(scanner.nextInt());
			}
		}
		if (trovato==false) System.out.println("Codice non trovato.");
	}
	
	public void stampaTotale() {
		for(ProdottoPers stampa:lista) {
			System.out.println(stampa.toString());
		}
	}
	
	public void updateGuadagni(double prezzo) {
		guadagniTot.add(prezzo);
	}
	
	public double getGuadagniTot() {
		double somma = 0;
		for(Double guad:guadagniTot) {
			somma += guad;
		}
		
		return somma;
	}
	
	public void updateAcquistati(String nome, Double prezzo,
			String categoria, int codice) {
		boolean trovato=false;
		ProdottoPers prodotto = new ProdottoPers(nome, prezzo, 1, categoria, codice);
		for(ProdottoPers update:listaAcquisti) {
			if(update.nome.equalsIgnoreCase(prodotto.nome)) {
				update.qta ++;
				trovato = true;
			}
		}
		if (trovato==false) {
			listaAcquisti.add(prodotto);
		}
	}
	
	public void stampaAcquistati() {
		for (ProdottoPers stampa:listaAcquisti) {
			System.out.println(stampa.toString());
		}
	}
	
	public void gestioneAcquisto(int codice) {
		boolean trovato=false;
		
		for(ProdottoPers acquisto:lista) {
			if(acquisto.codice==codice) {
				trovato=true;
				if (acquisto.qta>0) {
				acquisto.erogazione();
				updateGuadagni(acquisto.prezzo);
				updateAcquistati(acquisto.nome, acquisto.prezzo, acquisto.categoria, acquisto.codice);
				}else {
					System.out.println("Prodotto al momento non disponibile.");
				}
			}
		}
		if (trovato==false) System.out.println("Codice non trovato.");
	}
	
	public boolean gestioneAccesso (Scanner scanner) {
		boolean accesso=false;
		int tentativo;
		System.out.println("Inserisci pin admin: ");
		for (int i=1; i<=3; i++) {
			tentativo=scanner.nextInt();
			if (tentativo==pin) {
				System.out.println("Accesso riuscito.");
				accesso=true;
				break;		
			} else if (i==3) {
				System.out.println("Accesso bloccato.");
			} else {
				System.out.println("Pin non errato. Riprovare:\n");
			}		
		}
		return accesso;
	}
	
	public boolean filtraPerCategoria(String categoria) {
		boolean trovato=false;
		System.out.println("RISULTATI CATEGORIA: ");
		for (ProdottoPers filtra:lista) {
			if(categoria.equalsIgnoreCase(filtra.categoria)) {
				System.out.println(filtra.toString());
				trovato=true;
			}
		}
		
		if (trovato==false) System.out.println("Categoria non trovata.");
		return trovato;
	}
	
	public boolean controlloEta(int codice, Scanner scanner) {
		boolean acquisto=true;
		for (ProdottoPers controllo:lista) {
			if(codice==controllo.codice&&controllo.codice>200) {
				System.out.println("Stai cercando di acquistare un prodotto alcolico"
						+". Inserire età:");
				int eta=scanner.nextInt();
				if (eta>=18) {
					System.out.println("Acquisto consentito.");
				} else {
					acquisto=false;
					System.out.println("Acquisto non consentito.");
				}
			}
		}
		return acquisto;
	}

	public void menuGestore(Scanner scanner) {
		int scelta = 0;
		double prezzo = 0;
		String cat = "";
		String nome = "";
		int qta = 0;
		int codice = 0;

		do {
			System.out.println("MENU' GESTORE:\n1) Aggiungi prodotto\n"
					+ "2) Rimuovi prodotto\n3) Cambia il prezzo di un prodotto\n"
					+ "4) Cambia la quantità di un prodotto\n5) Stampa guadagni totali"
					+ "\n6) Stampa lista prodotti acquistati\n7) Ritorna al menù cliente.");
			scelta = scanner.nextInt();

			switch (scelta) {
			case 1:
				scanner.nextLine();

				System.out.println("Quanti prodotti vuoi aggiungere?");
				int num = scanner.nextInt();
				for (int c = 1; c <= num; c++) {
					scanner.nextLine();
					
					System.out.println("Inserisci nome prodotto "+c+": ");
					nome = scanner.nextLine();
					System.out.println("Inserisci categoria prodotto "+c+": ");
					cat = scanner.nextLine();
					System.out.println("Inserisci codice prodotto "+c+": ");
					codice = scanner.nextInt();
					System.out.println("Inserisci prezzo prodotto "+c+": ");
					prezzo = scanner.nextDouble();
					System.out.println("Inserisci quantità prodotto "+c+": ");
					qta = scanner.nextInt();
					ProdottoPers prod = new ProdottoPers(nome, prezzo, qta, cat, codice);
					aggiungiElemento(prod);
				}
				break;
			case 2:
				System.out.println("Inserisci codice prodotto: ");
				codice = scanner.nextInt();
				rimuoviElemento(codice);
				break;
			case 3:
				stampaTotale();
				System.out.println("Inserisci codice prodotto: ");
				codice = scanner.nextInt();
				cambiaPrezzo(codice, scanner);
				break;
			case 4:
				stampaTotale();
				System.out.println("Inserisci codice prodotto: ");
				codice = scanner.nextInt();
				cambiaQta(codice, scanner);
				break;
			case 5:
				System.out.println("Guadagni totali: "+getGuadagniTot());
				break;
			case 6:
				stampaAcquistati();
				break;
			case 7:
				System.out.println("Uscita dal menù gestore.");
				break;
			default:
				System.out.println("Codice errato.");
			}
		} while (scelta != 7);
	}

}
