class OddsProcessing {
    private int minCorrectTips;
    private int total;
    private String[] results;
    private Integer[] marker;
    private double sumOdds;

    OddsProcessing(String[][] results, int minCorrectTips){
        this.total = results.length;
        this.minCorrectTips = minCorrectTips;
        this.results = new String[this.total];
        this.sumOdds = 0;
        marker = new Integer[minCorrectTips];
        for (int i=0; i<minCorrectTips; i++){
            marker[i] = i;
        }
        for (int i=0; i<this.total; i++){
            this.results[i] = results[i][1];
        }
    }

    private void iteration(int current){
        double odds;
        int last = total-minCorrectTips+current;
        int start = 0;
        int delta = minCorrectTips - current - 1;
        if(current>0) start = marker[current-1]+1;
        for (int i=start; i<=last; i++){
            marker[current] = i;
            if(delta>0) {
                iteration(current+1);
            } else {
                odds = 1;
                for (int j=0; j<minCorrectTips; j++){
                    odds *= Double.parseDouble(results[marker[j]]);
                }
                sumOdds += odds;
            }
        }
    }

    double getOdds(){
        this.iteration(0);
        return sumOdds;
    }
}
