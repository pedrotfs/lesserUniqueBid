package br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.controller;

import br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.dto.Bid;
import br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.dto.WinningBid;
import br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.service.AuctionService;
import br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.service.InputHandlerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auction")
public class AuctionRestController {

    private Logger log = LoggerFactory.getLogger(AuctionRestController.class);

    @Autowired
    private InputHandlerService inputHandlerService;

    @Autowired
    private AuctionService auctionService;

    @PostMapping("/bid")
    public ResponseEntity<String> bid(@RequestBody final String bid) {
        log.info("receiving request body: " + bid);
        try {
            List<Bid> bids = inputHandlerService.handleInput(bid);
            auctionService.placeBids(bids);
        } catch(Exception e) {
            return new ResponseEntity<>("Malformed Input Json.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("bids successfully placed", HttpStatus.OK);
    }

    @GetMapping("/lowest")
    public ResponseEntity<WinningBid> getLowestDistinctBid() {
        log.info("returning current winner: ");
        return new ResponseEntity<>(auctionService.getAuctionWinner(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Bid>> getAllBids() {
        log.info("returning all bids: " + auctionService.getAllBids());
        return new ResponseEntity<>(auctionService.getAllBids(), HttpStatus.OK);
    }

}
