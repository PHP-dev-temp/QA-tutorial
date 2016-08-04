class BetProcessing {

    private int minCorrectTips;
    private Integer totalRows;
    private Boolean[] correctTips;
    private Double[] odds;

    BetProcessing(String results[][], int minCorrectTips){
        this.totalRows = results.length;
        this.correctTips = new Boolean[this.totalRows];
        this.odds = new Double[this.totalRows];
        this.minCorrectTips = minCorrectTips;
        for (int i=0; i<this.totalRows; i++){
            if (results[i][0].equalsIgnoreCase("1")) {
                this.correctTips[i] = true;
                this.odds[i] = Double.parseDouble(results[i][1]);
            } else {
                this.correctTips[i] = false;
                this.odds[i] = 0.0;
            }
        }
    }

    int getTotalCorrectTips(){
        int total = 0;
        for (int i=0; i<totalRows; i++){
            if(correctTips[i]) total++;
        }
        return total;
    }

    String[][] getCompresedResults(){
        int total = getTotalCorrectTips();
        int j = 0;
        String[][] newResults = new String[total][2];
        for (int i=0; i<totalRows; i++){
            if (correctTips[i]){
                newResults[j][0] = "1";
                newResults[j][1] = odds[i].toString();
                j++;
            }
        }
        return newResults;
    }

    long getBetCombinations(){
        long sumUp = 1;
        long sumDown = 1;
        for (int i=1; i<=minCorrectTips; i++){
            sumUp*=(totalRows-i+1);
            sumDown*=i;
        }
        return (sumUp/sumDown);
    }
}
