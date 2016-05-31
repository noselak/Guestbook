package pl.pawc.guestbook.POJO;

public class Entry{

	private int id;
	private String name;
	private String message;

	public int getId(){
		return id;
	}

	public String getName(){
		return name;
	}

	public String getMessage(){
		return message;
	}
	
	public void setId(int id){
		this.id=id;
	}

	public void setName(String name){
		this.name=name;
	}

	public void setMessage(String message){
		this.message=message;
	}

}
