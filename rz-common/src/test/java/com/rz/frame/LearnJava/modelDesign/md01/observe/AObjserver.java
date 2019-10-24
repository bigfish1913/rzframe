package com.rz.frame.LearnJava.modelDesign.md01.observe;


public class AObjserver implements IObserver {
	@Override
	public void doChange(ObserveModel observeModel) {
	   observeModel.setFlag(observeModel.getFlag()+"A");
	}
}
