package distributore;

public class ProdottoPers {
	// attributi
		protected String nome;
		protected double prezzo;
		protected int qta;
		protected String categoria;
		protected int codice;
		
		
		public ProdottoPers(String nome, double prezzo, int qta, String categoria, int codice) {
			// costruttore
			this.nome = nome;
			this.prezzo=prezzo;
			this.qta=qta;
			this.categoria=categoria;
			this.codice=codice;
		}
		
		public String getNome() {
			return nome;
		}


		public void setNome(String nome) {
			this.nome = nome;
		}


		public double getPrezzo() {
			return prezzo;
		}


		public void setPrezzo(double prezzo) {
			this.prezzo = prezzo;
		}


		public int getQta() {
			return qta;
		}


		public void setQta(int qta) {
			this.qta = qta;
		}


		public int getCodice() {
			return codice;
		}


		public void setCodice(int codice) {
			this.codice = codice;
		}


		public String getCategoria() {
			return categoria;
		}


		public void setCategoria(String categoria) {
			this.categoria = categoria;
		}


		public void erogazione() {
			// metodo erogazione. Sottraggo 1 all'attributo quantità
			System.out.println("Erogazione del prodotto "+categoria+", Codice: "+codice+" in corso...");
			qta--;
		}
		
		@Override
		public String toString() {
			return "Nome: "+nome+", Categoria: "+categoria+", Codice: "+codice+", Prezzo: "+prezzo
					+", Quantità: "+qta;
		}
}