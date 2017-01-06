package derj.test.code;

public class Pitannya {
    private int pitId;
	private int rozId;
    private int nomPit;
    private String pit;
    private String vidpov;
    
    public Pitannya(int pitId, int rozId, int nomPit, String pit, String vidpov) {
    	this.pitId = pitId;
    	this.rozId = rozId;
    	this.nomPit = nomPit;
    	this.pit = pit;
    	this.vidpov = vidpov;  		
    }
    
    public int getPitId() {
  		return pitId;
  	}
    
    public void setPitId(int pitId) {
    	this.pitId = pitId;
    }
    
    public int getRozId() {
		return rozId;
	}

	public void setRozId(int rozId) {
		this.rozId = rozId;
	}

	public int getNomPit() {
		return nomPit;
	}

	public void setNomPit(int nomPit) {
		this.nomPit = nomPit;
	}

	public String getPit() {
		return pit;
	}

	public void setPit(String pit) {
		this.pit = pit;
	}

	public String getVidpov() {
		return vidpov;
	}

	public void setVidpov(String vidpov) {
		this.vidpov = vidpov;
	}
}
