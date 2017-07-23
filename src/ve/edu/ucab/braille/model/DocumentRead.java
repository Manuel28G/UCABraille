package ve.edu.ucab.braille.model;

public class DocumentRead {

	private String title;
	private String path;
	private String size; //bytes
	private String extension;
	private int actualPage;
	private int totalPage;
	private int totalLetter;
	private int actualLetter;
	
	public DocumentRead() {
		title = "";
		path = "";
		size = "";
		extension = "";
		actualPage = 0;
		actualLetter = 0;
		totalPage = 10000;
		totalLetter = 10000;
	}

	public int getActualPage() {
		return actualPage;
	}

	public void setActualPage(int actualPage) {
		this.actualPage = actualPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalLetter() {
		return totalLetter;
	}

	public void setTotalLetter(int totalLetter) {
		this.totalLetter = totalLetter;
	}

	public int getActualLetter() {
		return actualLetter;
	}

	public void setActualLetter(int actualLetter) {
		this.actualLetter = actualLetter;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	public void setSize(long size) {
		this.size = Long.toString(size);
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	
	
	
	
}
