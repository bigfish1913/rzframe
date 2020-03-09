package learnJava.rz11;

public class RemoteControllerTest {
	public static void main(String[] args) {
		CommandObj light = new Light();
		CommandObj tv = new TV();
		
		Command lightOn = new WrapperOnCommand(light);
		Command lightOff = new WrapperOffCommand(light);
		Command TVOn = new WrapperOnCommand(tv);
		Command TVOff = new WrapperOffCommand(tv);
		
		
		RemoteController remoteController = new RemoteController();
		remoteController.registerCommand(RemoteTypeEnum.Light.toString(), lightOn, lightOff);
		remoteController.registerCommand(RemoteTypeEnum.TV.toString(), TVOn, TVOff);
		
		
		remoteController.onButtonPressed(RemoteTypeEnum.Light.toString());
		remoteController.offButtonPressed(RemoteTypeEnum.Light.toString());
		remoteController.onButtonPressed(RemoteTypeEnum.TV.toString());
		remoteController.offButtonPressed(RemoteTypeEnum.TV.toString());
	}
}
