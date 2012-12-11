package vn.fiosoft.ai;

import vn.fiosoft.emp.MainActivity;
import vn.fiosoft.service.EMPApplication;
import vn.fiosoft.service.EMPTextToSpeech;

public class CommandMain extends Command {

    private KeywordMain keywords;
    private MainActivity activity;

    public CommandMain(MainActivity acitivity) {
	this.activity = acitivity;
	this.keywords = new KeywordMain();
    }

    @Override
    public boolean execute(String command) {
	if (command.equals(""))
	    return false;
	command = command.split(" ")[0].toLowerCase();

	if (checkKeyword(command, keywords.getKeyword(KeywordApplication.HELLO))) {
	    hello();
	    return true;
	}
	if (checkKeyword(command, keywords.getKeyword(KeywordApplication.EXIT))) {
	    exit();
	    return true;
	}
	return false;
    }

    public void exit() {
	// show activity with style is dialog
	activity.finish();
    }

    public void hello() {
	EMPTextToSpeech textToSpeech = new EMPTextToSpeech(
		EMPApplication.mInstance);
	textToSpeech.say("Hi");
    }
}
