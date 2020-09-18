package com.example.denzel.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.models.market.InstrumentsList;

@Slf4j
@Service
public class TinkoffInvestServiceImpl {

//    @Autowired
//    private OpenApi openApi;
//
//    public Mono<InstrumentsList> getMarketBonds() {
//        return Mono.fromFuture(openApi.getMarketContext().getMarketBonds());
//    }
}
