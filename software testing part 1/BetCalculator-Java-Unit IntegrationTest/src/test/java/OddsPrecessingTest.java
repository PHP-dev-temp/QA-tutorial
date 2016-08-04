import org.junit.Assert;

public class OddsPrecessingTest {

    private String[][] inputResults = {{"1", "2"}, {"1", "3"}, {"1", "4"}, {"1", "5"}};

    @org.junit.Test
    public void quoteProcessingTest() throws Exception{
        OddsProcessing testQuoteProcessing = new OddsProcessing(inputResults, 3);
        double sumQuote = testQuoteProcessing.getOdds();
        Assert.assertEquals(154.0, sumQuote, 0.1);
    }

}
