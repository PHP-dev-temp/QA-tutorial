using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BetCalculator
{
    class OddsProcessing
    {
        private int minCorrectTips;
        private int total;
        private string[] results;
        private int[] marker;
        private double sumOdds;

        public OddsProcessing(string[,] results, int minCorrectTips)
        {
            total = results.GetLength(0);
            this.minCorrectTips = minCorrectTips;
            this.results = new string[total];
            sumOdds = 0;
            marker = new int[minCorrectTips];
            for (int i = 0; i < minCorrectTips; i++)
            {
                marker[i] = i;
            }
            for (int i = 0; i < total; i++)
            {
                this.results[i] = results[i,1];
            }
        }

        private void iteration(int current)
        {
            double odds;
            int last = total - minCorrectTips + current;
            int start = 0;
            int delta = minCorrectTips - current - 1;
            if (current > 0) start = marker[current - 1] + 1;
            for (int i = start; i <= last; i++)
            {
                marker[current] = i;
                if (delta > 0)
                {
                    iteration(current + 1);
                }
                else
                {
                    odds = 1;
                    for (int j = 0; j < minCorrectTips; j++)
                    {
                        odds *= double.Parse(results[marker[j]], System.Globalization.CultureInfo.InvariantCulture);
                    }
                    sumOdds += odds;
                }
            }
        }

        public double getQuote()
        {
            iteration(0);
            return sumOdds;
        }
    }
}
