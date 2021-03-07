package com.example.demo.service.mappers;

import com.example.demo.domain.model.Bidding;
import com.example.demo.model.BiddingDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class BiddingMapper {

    public Bidding map(BiddingDto biddingDto) {
        Bidding bidding = new Bidding();
        bidding.setOfferPrice(biddingDto.getOfferPrice());
        return bidding;
    }

    public BiddingDto map(Bidding bidding) {
        BiddingDto biddingDto = new BiddingDto();
        biddingDto.setOfferPrice(bidding.getOfferPrice());
        biddingDto.setOfferDateTime(bidding.getOfferDateTime());
        biddingDto.setUserId(bidding.getUser().getId());
        biddingDto.setAuctionId(bidding.getAuction().getId());
        return biddingDto;
    }

}
