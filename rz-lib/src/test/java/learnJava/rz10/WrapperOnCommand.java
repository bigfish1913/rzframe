package learnJava.rz10;

public class WrapperOnCommand implements Command {
	CommandObj commandObj;
	
	public WrapperOnCommand(CommandObj commandObj){
		this.commandObj = commandObj;
	}
	
	@Override
	public void execute() {
		commandObj.on();
	}
}
