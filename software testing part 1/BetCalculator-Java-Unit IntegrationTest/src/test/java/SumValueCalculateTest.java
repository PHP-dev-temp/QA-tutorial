import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class SumValueCalculateTest {

    private String[][] inputResults;
    private int winHits;
    private int value;

    @Before
    public void SetUp(){
        inputResults = new String[][]{{"1", "2"}, {"1", "1.5"}, {"0", "3.3"}, {"1", "3"}, {"0", "1.5"},
             {"0", "6.6"}, {"1", "4"}, {"0", "1.8"}, {"0", "3.9"}, {"0", "2.0"}, {"0", "2.1"}, {"1", "1.2"},
             {"0", "6.6"}, {"1", "3"}, {"0", "1.8"}, {"0", "3.9"}, {"0", "2.0"}, {"0", "2.1"}};
        winHits = 2;
        value = 2500;
    }

    @Test
    public void integrationTest(){
        BetProcessing betProcessor = new BetProcessing(inputResults, winHits);
        String[][] compresedResults = betProcessor.getCompresedResults();
        long comb = betProcessor.getBetCombinations();
        OddsProcessing quoteProcessing = new OddsProcessing(compresedResults, winHits);
        double sumOdds = quoteProcessing.getOdds();
        double summaryValue = sumOdds*value/comb;
        Assert.assertEquals(1424.83, summaryValue, 0.01);
    }

    @After
    public void CleanUp(){
        inputResults = null;
    }
}
