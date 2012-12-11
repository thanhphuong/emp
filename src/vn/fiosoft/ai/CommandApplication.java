package vn.fiosoft.ai;

import android.content.Intent;
import vn.fiosoft.service.EMPApplication;
import vn.fiosoft.service.EMPService;
import vn.fiosoft.service.EMPTextToSpeech;

public class CommandApplication extends Command {    
    
    private KeywordApplication keywords;   
    
    public CommandApplication() {
	keywords = new KeywordApplication();
	
    }

    public boolean checkKeyword(String command, String keywords){	
	if (keywords.contains(command))
	    return true;
	return false;
    }
    
    @Override
    public boolean execute(String command) {	
	if (command.equals(""))
	    return false;
	command = command.split(" ")[0].toLowerCase();
	
	if (checkKeyword(command, keywords.getKeyword(KeywordApplication.HELLO)))
	{
	    hello();
	    return true;
	}
	if (checkKeyword(command, keywords.getKeyword(KeywordApplication.EXIT)))
	{
	    exit();
	    return true;
	}
	return false;
    }

    public void exit(){
	//show activity with style is dialog
	EMPApplication.mInstance.stopService(new Intent(EMPApplication.mInstance,EMPService.class));
    }
    
    public void hello(){
	EMPTextToSpeech textToSpeech = new EMPTextToSpeech(EMPApplication.mInstance);
	textToSpeech.say("Hi");
    }
}
