package br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.controller;

import br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.dto.Bid;
import br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.dto.WinningBid;
import br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.service.AuctionService;
import br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.service.InputHandlerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuctionRestControllerTest {

    @InjectMocks
    private AuctionRestController auctionRestController;

    @Mock
    private AuctionService auctionService;

    @Mock
    private InputHandlerService inputHandlerService;

    private final static String INVALID_BID = "invalid";

    private final static String VALID_BID = "valid";

    @Test
    void bid() throws Exception {
        ArrayList<Bid> bids = new ArrayList<>();
        doReturn(bids).when(inputHandlerService).handleInput(VALID_BID);
        doNothing().when(auctionService).placeBids(bids);
        ResponseEntity<String> bid = auctionRestController.bid(VALID_BID);
        Assertions.assertEquals(bid.getStatusCodeValue(), 200);
        Assertions.assertEquals(bid.getBody(), "bids successfully placed");
    }

    @Test
    void bidThrowsException() throws Exception {
        doThrow(Exception.class).when(inputHandlerService).handleInput(INVALID_BID);
        ResponseEntity<String> bid = auctionRestController.bid(INVALID_BID);
        Assertions.assertEquals(bid.getStatusCodeValue(), 400);
        Assertions.assertEquals(bid.getBody(), "Malformed Input Json.");
    }

    @Test
    void getLowestDistinctBid() {
        WinningBid winner = new WinningBid(new Bid("winner", BigDecimal.TEN), BigDecimal.ONE.toString());
        doReturn(winner).when(auctionService).getAuctionWinner();
        ResponseEntity<WinningBid> lowestDistinctBid = auctionRestController.getLowestDistinctBid();
        Assertions.assertEquals(200, lowestDistinctBid.getStatusCodeValue());
        Assertions.assertEquals(winner, lowestDistinctBid.getBody());
    }

    @Test
    void getAllBids() {
        ArrayList<Bid> bids = new ArrayList<>();
        doReturn(bids).when(auctionService).getAllBids();
        ResponseEntity<List<Bid>> bid = auctionRestController.getAllBids();
        Assertions.assertEquals(bid.getStatusCodeValue(), 200);
        Assertions.assertEquals(bid.getBody(), bids);
    }

}