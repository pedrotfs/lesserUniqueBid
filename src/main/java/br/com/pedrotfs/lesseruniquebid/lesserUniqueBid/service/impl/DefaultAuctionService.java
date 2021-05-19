package br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.service.impl;

import br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.comparator.BidComparator;
import br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.dto.Bid;
import br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.dto.WinningBid;
import br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.service.AuctionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultAuctionService implements AuctionService {

    private List<Bid> bids = new ArrayList<>();

    private BidComparator bidComparator = new BidComparator();

    private int limit;

    private BigDecimal price;

    @Override
    public void placeBids(List<Bid> inputBids) {
        int currentLimit = Math.min(inputBids.size(), limit);
        bids = new ArrayList<>();
        bids.addAll(inputBids.subList(0, currentLimit));
        bids.sort(bidComparator);
    }

    @Override
    public WinningBid getAuctionWinner() {
        if(bids.isEmpty()) {
            return new WinningBid(new Bid("no bids", BigDecimal.ZERO), "0");
        }
        Bid currentWinner = searchLowestDistinctBid(bids.get(0));
        BigDecimal revenue = price.multiply(BigDecimal.valueOf(bids.size()));
        return new WinningBid(currentWinner, revenue.toString());
    }

    private Bid searchLowestDistinctBid(Bid currentWinner) {
        for(Bid bid : bids.subList(1, bids.size())) {
            if(currentWinner == null) {
                currentWinner = bid;
                continue;
            }

            if(currentWinner.getBid().compareTo(bid.getBid()) > 0) {
                break;
            }

            if(currentWinner.getBid().compareTo(bid.getBid()) == 0) {
                currentWinner = null;
            }
        }
        return currentWinner;
    }

    @Override
    public List<Bid> getAllBids() {
        return bids;
    }

    @Value("${bids.limit}")
    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Value("${bids.price}")
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
