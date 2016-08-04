import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

public class BetProcessingTest {

    private BetProcessing testBetProcessor;
    private String[][] inputResults = {{"1", "1.1"}, {"1", "7.2"}, {"0", "3.3"}, {"1", "4.4"}, {"0", "1.5"},
            {"0", "6.6"}, {"1", "5.7"}, {"0", "1.8"}, {"0", "3.9"}, {"0", "2.0"}, {"0", "2.1"}, {"1", "2.2"}};

    @org.junit.Test
    @Before
    public void constructorBetProcessorTest() throws Exception{
        testBetProcessor = new BetProcessing(inputResults, 2);
    }

    @org.junit.Test
    public void totalHitTest(){
        Integer correctTips = testBetProcessor.getTotalCorrectTips();
        Assert.assertEquals("Should be 5 correct tips", "5", correctTips.toString());
    }

    @org.junit.Test
    public void combinationsTest(){
        long noc = testBetProcessor.getBetCombinations();
        Assert.assertEquals("Should be 66 combinations", 66, noc);
    }

    @org.junit.Test
    public void compressedResultsTest(){
        String[][] compresedResults = testBetProcessor.getCompresedResults();
        for (int i=0; i<5; i++) {
            Assert.assertEquals("Should be 1", "1", compresedResults[i][0]);
        }
        Assert.assertEquals("Quote shoud be same as initialed", "1.1", compresedResults[0][1]);
        Assert.assertEquals("Quote shoud be same as initialed", "7.2", compresedResults[1][1]);
        Assert.assertEquals("Quote shoud be same as initialed", "4.4", compresedResults[2][1]);
        Assert.assertEquals("Quote shoud be same as initialed", "5.7", compresedResults[3][1]);
        Assert.assertEquals("Quote shoud be same as initialed", "2.2", compresedResults[4][1]);
    }

    @After
    public void cleanUp(){
        testBetProcessor = null;
    }
}
