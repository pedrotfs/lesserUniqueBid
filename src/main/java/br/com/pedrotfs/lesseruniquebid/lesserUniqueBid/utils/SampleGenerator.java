package br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.utils;

import java.util.Random;

public class SampleGenerator {

    public static final int LIMIT = 1200;

    public static void main(String[] args) {
        Random random = new Random();

        System.out.println("{ \"bids\": [");
        for(int i = 1; i <= LIMIT; i++) {
            printBidElement(random, i);
        }
        System.out.println("] }");
    }

    private static void printBidElement(Random random, int i) {
        System.out.println("{ ");
        System.out.println("\"name\": \"Auction Player" + i + "\", ");
        System.out.println("\"bid\": \"" + ((double) (random.nextInt(10000) + 1)/100) + "\" ");
        printElementEndDelimiter(i);
    }

    private static void printElementEndDelimiter(int i) {
        if(i == LIMIT) {
            System.out.println("} ");
        } else {
            System.out.println("},");
        }
    }
}
