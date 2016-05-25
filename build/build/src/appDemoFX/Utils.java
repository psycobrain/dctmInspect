package appDemoFX;

public class Utils {
	
	static private Main mainApp;

	@SuppressWarnings("static-access")
	static public void printDebug(String msg)
	{
		if (mainApp.flag)
			System.out.println(msg);
	}
	

}
