

public class DVD {
	
	private int id;
	private String title;
	private String genre;
	private int year;
  private int rating;
	
	
	public DVD(int ID, String title, String genre, int year, int rating) {
		
		this.id = ID;
		this.title = title;
		this.genre = genre;
		this.year = year;
    this.rating = rating;
	}
	public int getID() {
		return this.id;
	}
	public void setID(int id) {
		this.id = id;
	}
	public String getGenre() {
		return this.genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getYear() {
		return this.year;
	}
	public void setYear(int year) {
		this.year = year;
	}

  public int getRating() {
		return this.rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "DVD [ID=" + this.id + ", Title=" + this.title + ", Genre=" + this.genre + ", Year=" + this.year + ",Rating=" + this.rating + "]";
	}
	
}
