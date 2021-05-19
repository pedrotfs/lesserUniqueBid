package br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.comparator;

import br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.dto.Bid;

import java.util.Comparator;

public class BidComparator implements Comparator<Bid> {

    @Override
    public int compare(Bid a, Bid b) {
        return a.getBid().compareTo(b.getBid());
    }
}
