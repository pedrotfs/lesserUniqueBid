package br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.service;

import br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.dto.Bid;
import br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.dto.WinningBid;

import java.util.List;

public interface AuctionService {

    void placeBids(List<Bid> bids);

    WinningBid getAuctionWinner();

    List<Bid> getAllBids();
}
