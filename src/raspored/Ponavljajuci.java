package raspored;

public class Ponavljajuci extends Sadrzaj {
	
	private Vreme perioda;
	private char vrsta;
	
	public Ponavljajuci(String nazivv, Vreme trajanjee, Vreme periodaa) {
		super(nazivv, trajanjee);
		this.perioda=periodaa;
		vrsta='P';
	}
	
	public Vreme dohvPeriodu() {
		return perioda;
	}
	
	@Override
	public char getVrsta() {
		return vrsta;
	}
	
	@Override
    public Vreme preklapaSe(Sadrzaj s) throws GVreme {
        int pocetak1=this.dohvPocetak().minuti();
        int kraj1=Vreme.saberi(this.dohvPocetak(), this.getTrajanje()).minuti();
        int pocetak2=s.dohvPocetak().minuti();
        int kraj2=Vreme.saberi(s.dohvPocetak(), s.getTrajanje()).minuti();
        if(pocetak1<pocetak2) {
            if(kraj1>pocetak2) return s.dohvPocetak();
            else {
                if(kraj1+this.dohvPeriodu().minuti()<1440 ) {
                Ponavljajuci temp= new Ponavljajuci(this.getNaziv(), this.getTrajanje(), this.dohvPeriodu());
                temp.postVremPocetka(this.dohvPocetak());
                temp.pomeri(this.dohvPeriodu());
                return temp.preklapaSe(s);
                }
            }
        }else {
            if(kraj2>pocetak1) return this.dohvPocetak();
            else {
                if(kraj2+s.dohvPeriodu().minuti()<1440) {
                    Ponavljajuci temp =new Ponavljajuci(s.getNaziv(), s.getTrajanje(), s.dohvPeriodu());
                    temp.postVremPocetka(s.dohvPocetak());
                    temp.pomeri(s.dohvPeriodu());
                    return this.preklapaSe(temp);
                }
            }
        }
        return null;
    }
	
	public String toString() {
		return super.toString()+" T"+perioda;
	}
}
