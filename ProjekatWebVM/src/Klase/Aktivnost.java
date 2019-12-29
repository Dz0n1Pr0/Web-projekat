package Klase;

public class Aktivnost {
	private String id,date,status;

	public Aktivnost(String id, String date, String status) {
		super();
		this.id = id;
		this.date = date;
		this.status = status;
	}

	public Aktivnost() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
