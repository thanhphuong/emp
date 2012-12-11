package vn.fiosoft.ai.keyword;

public class KeywordApplication {
    public static final int HELLO = 0;
    public static final int EXIT = 1;

    public String getKeyword(int keywordApplication) {
	switch (keywordApplication) {
	case HELLO:
	    return "hi|hello";
	case EXIT:
	    return "exit|close";
	default:
	    return "";
	}
    }
}
