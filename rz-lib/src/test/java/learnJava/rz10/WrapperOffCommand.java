package learnJava.rz10;

public class WrapperOffCommand  implements Command {
	CommandObj commandObj;
	
	public WrapperOffCommand(CommandObj commandObj){
		this.commandObj = commandObj;
	}
	
	@Override
	public void execute() {
		commandObj.off();
	}
}
