package vn.fiosoft.actions;

public class Exit extends Action{
	
	public static boolean check(String cmd) {
		if (cmd.length() == 0)
			return false;
	
		cmd = cmd.toLowerCase();
		
		if (cmd.contains("exit"))
			return true;
		
		return false;
	}
	
	
	public static void execute() {	
		
	}

}
