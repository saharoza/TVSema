package raspored;

public class Vreme {
	private int sati;
	private int minuti;
	
	public Vreme() {
	}
	
	public Vreme (int sati, int minuti)  throws GVreme {
		if (sati<0 || sati>23 || minuti<0 || minuti>59 || (minuti%15!=0)) throw new GVreme();
			this.sati=sati;
			this.minuti=minuti;
	}
	
	/*public static Vreme saberi(Vreme v1, Vreme v2) throws GVreme {
		int vreme=v1.minuti()+v2.minuti();
		return new Vreme(vreme/60, vreme%60);
	} nice try but nah */
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vreme other = (Vreme) obj;
		if (minuti != other.minuti)
			return false;
		if (sati != other.sati)
			return false;
		return true;
	}

	public static Vreme saberi(Vreme v1, Vreme v2) {
        Vreme v = new Vreme();
        if (v1.minuti + v2.minuti <= 59) {
            v.minuti = v1.minuti + v2.minuti;
            v.sati = v1.sati + v2.sati;
        } else {
            v.minuti = (v1.minuti + v2.minuti) % 60;
            v.sati = v1.sati + v2.sati + 1;
        }
        return v;
    }
	
	public int minuti() {
		return sati*60+minuti;
	}

	@Override
	public String toString() {
		return "(" + sati + ":" + minuti + ")";
	}
	
	
}
