package learnJava.rz12;

import java.util.ArrayList;

public class MarketSubject {
	
	private double price;
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
		notifyObserver(price);
	}
	
	private ArrayList<Observer> observerList=new ArrayList<>();
	public void addObserver(Observer observer){
		observerList.add(observer);
	}
	
	public void removeObserver(Observer observer){
		observerList.remove(observer);
	}
	
	public void notifyObserver(double price){
		for (Observer ob : observerList) {
			ob.reciveMessage(price);
		}
	}
}
