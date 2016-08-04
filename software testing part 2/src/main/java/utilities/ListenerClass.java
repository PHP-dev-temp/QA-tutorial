package utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class ListenerClass extends TestListenerAdapter {
    private int m_count = 0;
	 
    @Override
    public void onTestFailure(ITestResult tr) {
    	//log(tr.getName()+ "--Test method failed\t"+ tr.getThrowable().getMessage()+ "\n");
    	log(" f ");
    }
	 
    @Override
    public void onTestSkipped(ITestResult tr) {
        //log(tr.getName()+ "--Test method skipped\n");
    	log(" s ");
    }
	 
    @Override
    public void onTestSuccess(ITestResult tr) {
        //log(tr.getName()+ "--Test method success\n");
    	log(" . ");
    }
    
    @Override
    public void onFinish(ITestContext tr) {
	    System.out.println("");    	
	    System.out.println("end of testing");    
	    System.out.println("");    		
    }
	 
    private void log(String string) {
        System.out.print(string);
        if (++m_count > 5) {
        	m_count = 0;
	    System.out.println("");
        }
    }

}
