package com.manudev.coinService.mapper;

import com.manudev.coinService.model.Coin;
import com.manudev.coinService.model.CoinDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-20T01:33:54-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class CoinMapperImpl implements CoinMapper {

    @Override
    public CoinDTO coinToDTO(Coin coin) {
        if ( coin == null ) {
            return null;
        }

        CoinDTO.CoinDTOBuilder coinDTO = CoinDTO.builder();

        coinDTO.id( coin.getId() );
        coinDTO.symbol( coin.getSymbol() );
        coinDTO.name( coin.getName() );
        coinDTO.image( coin.getImage() );
        coinDTO.currentPrice( coin.getCurrentPrice() );
        coinDTO.marketCap( coin.getMarketCap() );
        coinDTO.marketCapRank( coin.getMarketCapRank() );
        coinDTO.fullyDilutedValuation( coin.getFullyDilutedValuation() );
        coinDTO.totalVolume( coin.getTotalVolume() );
        coinDTO.high24h( coin.getHigh24h() );
        coinDTO.low24h( coin.getLow24h() );
        coinDTO.priceChange24h( coin.getPriceChange24h() );
        coinDTO.priceChangePercentage24h( coin.getPriceChangePercentage24h() );
        coinDTO.marketCapChange24h( coin.getMarketCapChange24h() );
        coinDTO.marketCapChangePercentage24h( coin.getMarketCapChangePercentage24h() );
        coinDTO.circulatingSupply( coin.getCirculatingSupply() );
        coinDTO.totalSupply( coin.getTotalSupply() );
        coinDTO.maxSupply( coin.getMaxSupply() );
        coinDTO.ath( coin.getAth() );
        coinDTO.athChangePercentage( coin.getAthChangePercentage() );
        coinDTO.athDate( coin.getAthDate() );
        coinDTO.atl( coin.getAtl() );
        coinDTO.atlChangePercentage( coin.getAtlChangePercentage() );
        coinDTO.atlDate( coin.getAtlDate() );
        if ( coin.getRoi() != null ) {
            coinDTO.roi( coin.getRoi() );
        }
        coinDTO.lastUpdated( coin.getLastUpdated() );

        return coinDTO.build();
    }

    @Override
    public Coin dtoToCoin(CoinDTO coinDTO) {
        if ( coinDTO == null ) {
            return null;
        }

        Coin coin = new Coin();

        coin.setId( coinDTO.getId() );
        coin.setSymbol( coinDTO.getSymbol() );
        coin.setName( coinDTO.getName() );
        coin.setImage( coinDTO.getImage() );
        coin.setCurrentPrice( coinDTO.getCurrentPrice() );
        coin.setMarketCap( coinDTO.getMarketCap() );
        coin.setMarketCapRank( coinDTO.getMarketCapRank() );
        coin.setFullyDilutedValuation( coinDTO.getFullyDilutedValuation() );
        coin.setTotalVolume( coinDTO.getTotalVolume() );
        coin.setHigh24h( coinDTO.getHigh24h() );
        coin.setLow24h( coinDTO.getLow24h() );
        coin.setPriceChange24h( coinDTO.getPriceChange24h() );
        coin.setPriceChangePercentage24h( coinDTO.getPriceChangePercentage24h() );
        coin.setMarketCapChange24h( coinDTO.getMarketCapChange24h() );
        coin.setMarketCapChangePercentage24h( coinDTO.getMarketCapChangePercentage24h() );
        coin.setCirculatingSupply( coinDTO.getCirculatingSupply() );
        coin.setTotalSupply( coinDTO.getTotalSupply() );
        coin.setMaxSupply( coinDTO.getMaxSupply() );
        coin.setAth( coinDTO.getAth() );
        coin.setAthChangePercentage( coinDTO.getAthChangePercentage() );
        coin.setAthDate( coinDTO.getAthDate() );
        coin.setAtl( coinDTO.getAtl() );
        coin.setAtlChangePercentage( coinDTO.getAtlChangePercentage() );
        coin.setAtlDate( coinDTO.getAtlDate() );
        coin.setRoi( coinDTO.getRoi() );
        coin.setLastUpdated( coinDTO.getLastUpdated() );

        return coin;
    }
}
