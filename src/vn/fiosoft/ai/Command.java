package vn.fiosoft.ai;

public abstract class Command {

    public boolean checkKeyword(String command, String keywords){	
	if (keywords.contains(command))
	    return true;
	return false;
    }
    
    public abstract boolean execute(String command);

}
