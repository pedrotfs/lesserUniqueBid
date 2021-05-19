package br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.service.impl;

import br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.dto.Bid;
import br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.service.InputHandlerService;
import br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.utils.AuctionConstants;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultInputHandlerService implements InputHandlerService {

    private Gson gson = new Gson();

    private String identifier;

    private int decimalPlaces;

    @Override
    public List<Bid> handleInput(String json) throws Exception{
        JsonElement jsonElement = gson.fromJson(json, JsonElement.class);
        JsonArray jsonArray = (JsonArray) jsonElement.getAsJsonObject().get(identifier);
        return assembleBids(jsonArray);
    }

    private List<Bid> assembleBids(JsonArray jsonArray) throws Exception {
        List<Bid> bids = new ArrayList<>();
        jsonArray.forEach(b -> {
            JsonObject bidAsJson = b.getAsJsonObject();
            bids.add(new Bid(bidAsJson.get(AuctionConstants.INPUT_NAME).getAsString(),
                    bidAsJson.get(AuctionConstants.INPUT_BID).getAsBigDecimal()));
        });
        if(! validateScales(bids)) {
            throw new Exception("scale exceeds parametrized limit");
        }
        return bids;
    }

    private boolean validateScales(List<Bid> bids) {
        return bids.stream().noneMatch(b -> b.getBid().scale() > decimalPlaces);
    }

    @Value("${bids.identifier}")
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Value("${bids.decimalPlaces}")
    public void setDecimalPlaces(int decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }
}
