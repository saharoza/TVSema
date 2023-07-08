package raspored;

public class Sema {
	
	private static class Element{
		Sadrzaj s;
		Element sledeci;
		Element(Sadrzaj ss){
			s=ss;
			sledeci=null;
		}
	}
	private Element lista;
	private String naziv;
	private Vreme pocetak;
	private Vreme kraj;
	
	public Sema(String naziv, Vreme pocetak, Vreme kraj) throws GVreme {
		this.naziv=naziv;
		this.pocetak=pocetak;
		this.kraj=kraj;
		lista=null;
	}
	
	public Sema(String naziv) throws GVreme {
		this.naziv=naziv;
		pocetak = new Vreme(8,00);
		kraj = new Vreme(22,00);
		lista=null;
	}
	
	public void dodaj(Sadrzaj s) throws GDodaj, GVreme {
		int pocetak1=this.pocetak.minuti();
        int kraj1=this.kraj.minuti();
        int pocetak2=s.dohvPocetak().minuti();
        if(kraj1<=pocetak2) throw new GDodaj();
        if(pocetak1>pocetak2) s.postVremPocetka(this.pocetak);
        boolean e=true;
        Element tekuci=lista;
        while(tekuci!=null && e) {
            e=tekuci.s.preklapaSe(s)==null;
            tekuci=tekuci.sledeci;
        }
        if(e) {
            Element q=new Element(s);
            if(lista==null) lista=q;
            else {
                if(lista.s.dohvPocetak().minuti()>s.dohvPocetak().minuti()) {
                    q.sledeci=this.lista;
                    lista=q;
                }else {
                    Element z=lista;
                    Element y=lista;
                    while(z!=null && z.s.dohvPocetak().minuti()<s.dohvPocetak().minuti()) {
                        y=z;
                        z=z.sledeci;
                    }
                    y.sledeci=q;
                    q.sledeci=z;
                }
            }
        }
        else {
            s.pomeri(new Vreme(0,15));
            this.dodaj(s);
        }
	}
	
	public int dohvatiBrojSadrzaja(){
		Element tekuci=lista;
		int brojac=0;
		while(tekuci != null) {
			brojac++;
			tekuci=tekuci.sledeci;
		}
		return brojac;
	}
	
	public double zauzetost() throws GVreme{
		int sema = this.kraj.minuti()-this.pocetak.minuti();
		double zauzeto = 0;
		Element tekuci=lista;
		while(tekuci != null) {
			int temp = 1+ (int)((this.kraj.minuti()-tekuci.s.dohvPocetak().minuti()-tekuci.s.getTrajanje().minuti())/tekuci.s.dohvPeriodu().minuti());
			zauzeto+=temp*tekuci.s.getTrajanje().minuti();
			tekuci=tekuci.sledeci;
		}
		return (zauzeto/sema)*100;
		}
	
	public Sadrzaj dohvatiElement(int indeks) throws GIndeks{
		Element tekuci = lista;
		int brojac=0;
		if(indeks == 0) return lista.s;
		while(tekuci!=null && brojac<indeks) {
			tekuci=tekuci.sledeci;
			brojac++;
		}
		if(tekuci!=null) return tekuci.s;
		else throw new GIndeks();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(naziv).append(" : ").append(pocetak).append(" - ").append(kraj);
		sb.append('\n');
		Element tekuci = lista;
		while(tekuci!=null) {
			sb.append(tekuci.s).append('\n');
			tekuci=tekuci.sledeci;
		}
		return sb.toString();
		}
}
