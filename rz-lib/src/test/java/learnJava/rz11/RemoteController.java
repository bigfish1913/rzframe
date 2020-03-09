package learnJava.rz11;

import java.util.concurrent.ConcurrentHashMap;

public class RemoteController {
	ConcurrentHashMap<String, Command> onCommands;
	ConcurrentHashMap<String, Command> offCommands;
	
	public RemoteController(){
		this.onCommands = new ConcurrentHashMap<>();
		this.offCommands = new ConcurrentHashMap<>();
	}
	
	public void registerCommand(String key, Command onCommand, Command offCommand){
		onCommands.put(key,onCommand);
		offCommands.put(key,offCommand);
	}
	
	// 按下开按钮
	public void onButtonPressed(String key){
		onCommands.get(key).execute();
	}
	
	// 按下关按钮
	public void offButtonPressed(String key){
		offCommands.get(key).execute();
	}
}
