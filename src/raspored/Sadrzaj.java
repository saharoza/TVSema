package raspored;

public abstract class Sadrzaj {
	private String naziv;
	private Vreme trajanje;
	private Vreme vpocetka;
	
	public Sadrzaj(String naziv, Vreme trajanje) {
		this.naziv=naziv;
		this.trajanje=trajanje;
		vpocetka = new Vreme();
	}
	
	/*public abstract Vreme preklapaSe (Sadrzaj s) throws GVreme;*/
	
	public Vreme preklapaSe(Sadrzaj s) throws GVreme{
		int pocetak1 = this.vpocetka.minuti();
		int kraj1 = Vreme.saberi(this.vpocetka, this.trajanje).minuti();
		int pocetak2 = s.vpocetka.minuti();
		int kraj2 = Vreme.saberi(s.vpocetka, s.trajanje).minuti();
		if(pocetak1>pocetak2 && kraj2>pocetak1) return this.vpocetka;
		if(pocetak1<pocetak2 && kraj1>pocetak2) return s.vpocetka;
		return null;
	}

	public Vreme dohvPocetak() {
		return vpocetka;
	}
	
	public void postVremPocetka(Vreme v) {
		this.vpocetka=v;
	}

	public String getNaziv() {
		return naziv;
	}

	public Vreme getTrajanje() {
		return trajanje;
	}
	
	public abstract char getVrsta();
	
	public void pomeri(Vreme v) {
		this.vpocetka=Vreme.saberi(this.vpocetka, v);
	}
	
	public String toString() {
		return getVrsta()+", "+naziv+" | "+vpocetka+" - "+Vreme.saberi(this.trajanje, this.vpocetka).toString();
	}

	public abstract Vreme dohvPeriodu();
}
