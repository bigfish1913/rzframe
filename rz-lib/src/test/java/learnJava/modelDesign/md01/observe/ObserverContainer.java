package learnJava.modelDesign.md01.observe;

import java.util.concurrent.ConcurrentHashMap;

public class ObserverContainer {
	
	private ConcurrentHashMap<String, IObserver> ObserverList = new ConcurrentHashMap<>();
	
	public void registerObserver(String className, IObserver observer) {
		ObserverList.putIfAbsent(className, observer);
	}
	
	public void removeObserver(String className, IObserver observer) {
		ObserverList.put(className, observer);
	}
	
	public void notifyObserver(ObserveModel observeModel) {
		for (IObserver observer : ObserverList.values()) {
			observer.doChange(observeModel);
		}
	}
	
	
}
