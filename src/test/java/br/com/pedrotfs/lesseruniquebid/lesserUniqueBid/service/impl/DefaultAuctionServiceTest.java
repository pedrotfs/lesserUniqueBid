package br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.service.impl;

import br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.dto.Bid;
import br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.dto.WinningBid;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DefaultAuctionServiceTest {

    private DefaultAuctionService defaultAuctionService = new DefaultAuctionService();

    private List<Bid> bidList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        defaultAuctionService.setLimit(999);
        defaultAuctionService.setPrice(new BigDecimal("2"));
    }

    @Test
    void placeBids() {

        bidList = new ArrayList<>();
        bidList.add(new Bid("user1", new BigDecimal("1")));
        bidList.add(new Bid("user2", new BigDecimal("2")));
        bidList.add(new Bid("user3", new BigDecimal("3")));
        bidList.add(new Bid("user4", new BigDecimal("4")));

        Assertions.assertTrue(defaultAuctionService.getAllBids().isEmpty());
        defaultAuctionService.placeBids(bidList);
        Assertions.assertEquals(defaultAuctionService.getAllBids().size(), 4);
        List<Bid> allBids = defaultAuctionService.getAllBids();

        Assertions.assertEquals(allBids.get(0).getName(), "user1");
        Assertions.assertEquals(allBids.get(0).getBid(), new BigDecimal("1"));
        Assertions.assertEquals(allBids.get(1).getName(), "user2");
        Assertions.assertEquals(allBids.get(1).getBid(), new BigDecimal("2"));
        Assertions.assertEquals(allBids.get(2).getName(), "user3");
        Assertions.assertEquals(allBids.get(2).getBid(), new BigDecimal("3"));
        Assertions.assertEquals(allBids.get(3).getName(), "user4");
        Assertions.assertEquals(allBids.get(3).getBid(), new BigDecimal("4"));

        WinningBid auctionWinner = defaultAuctionService.getAuctionWinner();
        Assertions.assertEquals(auctionWinner.getBid(), bidList.get(0));
        Assertions.assertEquals(auctionWinner.getRevenue(), new BigDecimal("8").toString());
    }

    @Test
    void placeBidsTie() {

        bidList = new ArrayList<>();
        bidList.add(new Bid("user1", new BigDecimal("1")));
        bidList.add(new Bid("user2", new BigDecimal("1")));
        bidList.add(new Bid("user3", new BigDecimal("3")));
        bidList.add(new Bid("user4", new BigDecimal("4")));

        Assertions.assertTrue(defaultAuctionService.getAllBids().isEmpty());
        defaultAuctionService.placeBids(bidList);
        Assertions.assertEquals(defaultAuctionService.getAllBids().size(), 4);
        List<Bid> allBids = defaultAuctionService.getAllBids();

        Assertions.assertEquals(allBids.get(0).getName(), "user1");
        Assertions.assertEquals(allBids.get(0).getBid(), new BigDecimal("1"));
        Assertions.assertEquals(allBids.get(1).getName(), "user2");
        Assertions.assertEquals(allBids.get(1).getBid(), new BigDecimal("1"));
        Assertions.assertEquals(allBids.get(2).getName(), "user3");
        Assertions.assertEquals(allBids.get(2).getBid(), new BigDecimal("3"));
        Assertions.assertEquals(allBids.get(3).getName(), "user4");
        Assertions.assertEquals(allBids.get(3).getBid(), new BigDecimal("4"));

        WinningBid auctionWinner = defaultAuctionService.getAuctionWinner();
        Assertions.assertEquals(auctionWinner.getBid(), allBids.get(2));
        Assertions.assertEquals(auctionWinner.getRevenue(), new BigDecimal("8").toString());
    }

    @Test
    void placeBidsOnlyTie() {

        bidList = new ArrayList<>();
        bidList.add(new Bid("user1", new BigDecimal("1")));
        bidList.add(new Bid("user2", new BigDecimal("1")));

        Assertions.assertTrue(defaultAuctionService.getAllBids().isEmpty());
        defaultAuctionService.placeBids(bidList);
        Assertions.assertEquals(defaultAuctionService.getAllBids().size(), 2);
        List<Bid> allBids = defaultAuctionService.getAllBids();

        Assertions.assertEquals(allBids.get(0).getName(), "user1");
        Assertions.assertEquals(allBids.get(0).getBid(), new BigDecimal("1"));
        Assertions.assertEquals(allBids.get(1).getName(), "user2");
        Assertions.assertEquals(allBids.get(1).getBid(), new BigDecimal("1"));

        WinningBid auctionWinner = defaultAuctionService.getAuctionWinner();
        Assertions.assertNull(auctionWinner.getBid());
        Assertions.assertEquals(auctionWinner.getRevenue(), new BigDecimal("4").toString());
    }

    @Test
    void placeBidsEmpty() {

        bidList = new ArrayList<>();

        Assertions.assertTrue(defaultAuctionService.getAllBids().isEmpty());
        defaultAuctionService.placeBids(bidList);
        Assertions.assertTrue(defaultAuctionService.getAllBids().isEmpty());

        WinningBid auctionWinner = defaultAuctionService.getAuctionWinner();
        Assertions.assertEquals(auctionWinner.getBid().getBid(), BigDecimal.ZERO);
        Assertions.assertEquals(auctionWinner.getBid().getName(), "no bids");
        Assertions.assertEquals(auctionWinner.getRevenue(), new BigDecimal("0").toString());
    }

}