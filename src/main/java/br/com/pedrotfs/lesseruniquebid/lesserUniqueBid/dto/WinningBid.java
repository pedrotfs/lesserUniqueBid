package br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.dto;

public class WinningBid {

    private Bid bid;

    private String revenue;

    public WinningBid(Bid bid, String revenue) {
        this.bid = bid;
        this.revenue = revenue;
    }

    public Bid getBid() {
        return bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }
}
