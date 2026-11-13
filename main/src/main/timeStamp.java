package main;
import java.time.LocalTime;
import java.util.ArrayList;
public class timeStamp {
	
	private ArrayList <LocalTime> arrayTime;
	
	
	// create contructor, storing time
	public timeStamp() {
		this.arrayTime = new ArrayList<LocalTime>();
	}
	
	public ArrayList<LocalTime> getArrayTime() {
		return this.arrayTime;
	}
	
	public void addLocalTime(LocalTime time) {
		arrayTime.add(time);
		
	}
	
	public void removeElement(int index) {
		this.arrayTime.remove(index);
		
	}
	
	public LocalTime returnElement(int index){
		return this.arrayTime.get(index);
	}
	public int returnArraySize() {
		if(this.arrayTime.isEmpty()) {
			return 0;
		}
		return this.arrayTime.size();
	}
	
	@Override
    public String toString() {
        return this.arrayTime.toString();
    }
}
