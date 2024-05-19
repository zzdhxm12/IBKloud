package com.ibkhe;

public class BaseBallGame {
    public static void main(String[] args) {
        int[] given = {1,2,3};
        int[] input = {3,2,1};

        int bcnt = 0;
        int scnt = 0;

        for (int i=0;i<3;i++){
            if(given[i] == input[i]) scnt++;
            for (int j=0;j<3;j++){
                if(i==j) continue;
                if(given[i] == input[j]){
                    bcnt++;
                }
            }
        }

        if(scnt==0 && bcnt==0) {
            System.out.println("null");
        }else {
            System.out.println((scnt>0?scnt+"S":"")+(bcnt>0?bcnt+"B":""));
        }
    }
}
