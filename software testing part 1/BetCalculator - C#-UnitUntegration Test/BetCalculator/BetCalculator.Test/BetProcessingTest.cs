using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace BetCalculator.Test
{
    [TestClass]
    public class BetProcessingTest
    {
        private BetProcessing testBetProcessor;
        private string[,] inputResults;
        private int minCorrectTips;

        [TestInitialize]
        public void SetUp()
        {
            minCorrectTips = 2;
            inputResults = new string[,] {{ "1", "1.1"}, { "1", "7.2"}, { "0", "3.3"}, { "1", "4.4"}, { "0", "1.5"},
            { "0", "6.6"}, { "1", "5.7"}, { "0", "1.8"}, { "0", "3.9"}, { "0", "2.0"}, { "0", "2.1"}, { "1", "2.2"}};
            testBetProcessor = new BetProcessing(inputResults, minCorrectTips);

        }
        
        [TestMethod]
        public void TestgetTotalHits()
        {
            int totalHits = testBetProcessor.getTotalCorrectTips();
            Assert.AreEqual(5, totalHits);
        }

        [TestMethod]
        public void TestgetBetCombinations()
        {
            long noc = testBetProcessor.getBetCombinations();
            Assert.AreEqual(66, noc);
        }

        [TestMethod]
        public void TestgetCompresedResults()
        {
            string[,] compresedResults = testBetProcessor.getCompresedResults();
            for (int i = 0; i < 5; i++)
            {
                Assert.AreEqual("1", compresedResults[i,0]);
            }
            Assert.AreEqual("1.1", compresedResults[0,1]);
            Assert.AreEqual("7.2", compresedResults[1,1]);
            Assert.AreEqual("4.4", compresedResults[2,1]);
            Assert.AreEqual("5.7", compresedResults[3,1]);
            Assert.AreEqual("2.2", compresedResults[4,1]);
        }

        [TestCleanup]
        public void CleanUp()
        {
            testBetProcessor = null;
        }
    }
}
