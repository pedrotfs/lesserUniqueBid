package br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.service;

import br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.dto.Bid;

import java.util.List;

public interface InputHandlerService {

    List<Bid> handleInput(String json) throws Exception;
}
