package LearnJava.modelDesign.md01.observe;

import org.junit.Assert;

import static org.hamcrest.CoreMatchers.equalTo;

public class ObjserverMain {
	
	public static void main(String[] args) {
		ObserverContainer container=new ObserverContainer();
		container.registerObserver(AObjserver.class.getName(),new AObjserver());
		container.registerObserver(BObjserver.class.getName(),new BObjserver());
		ObserveModel observeModel = new ObserveModel();
		observeModel.setFlag("Test");
		container.notifyObserver(observeModel);
		Assert.assertThat("TestBA",equalTo(observeModel.getFlag()));
	}
}
