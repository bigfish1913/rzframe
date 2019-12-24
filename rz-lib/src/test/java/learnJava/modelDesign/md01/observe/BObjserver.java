package learnJava.modelDesign.md01.observe;

public class BObjserver implements IObserver{
	@Override
	public void doChange(ObserveModel observeModel) {
		observeModel.setFlag(observeModel.getFlag()+"B");
	}
}
