using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BetCalculator
{
    public class BetProcessing
    {

        private int minCorrectTips;
        private int totalRows;
        private bool[] correctTips;
        private double[] odds;

        public BetProcessing(string[,] results, int minCorrectTips)
        {
            totalRows = results.GetLength(0);
            correctTips = new bool[totalRows];
            odds = new double[totalRows];
            this.minCorrectTips = minCorrectTips;
            for (int i = 0; i < totalRows; i++)
            {
                if (results[i,0]=="1")
                {
                    correctTips[i] = true;
                    odds[i] = double.Parse(results[i, 1], System.Globalization.CultureInfo.InvariantCulture);
                }
                else
                {
                    correctTips[i] = false;
                    odds[i] = 0.0;
                }
            }
        }

        public int getTotalCorrectTips()
        {
            int total = 0;
            for (int i = 0; i < totalRows; i++)
            {
                if (correctTips[i]) total++;
            }
            return total;
        }

        public string[,] getCompresedResults()
        {
            int total = getTotalCorrectTips();
            int j = 0;
            string[,] newResults = new string[total,2];
            for (int i = 0; i < totalRows; i++)
            {
                if (correctTips[i])
                {
                    newResults[j,0] = "1";
                    newResults[j,1] = odds[i].ToString("0.##"); 
                    j++;
                }
            }
            return newResults;
        }

        public long getBetCombinations()
        {
            long sumUp = 1;
            long sumDown = 1;
            for (int i = 1; i <= minCorrectTips; i++)
            {
                sumUp *= (totalRows - i + 1);
                sumDown *= i;
            }
            return (sumUp / sumDown);
        }
    }
}
