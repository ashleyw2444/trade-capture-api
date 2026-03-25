package com.example.tradecapture.entity;
import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;

@Embeddable
public class PositionId implements Serializable {

    private Long accountId;
    private Long instrumentId;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @MapsId("instrumentId")
    @JoinColumn(name = "instrument_id")
    private Instrument instrument;

    public PositionId(Long accountId, Long instrumentId){
        this.accountId = accountId;
        this.instrumentId = instrumentId;
    }

    public Long getAccountId(){
        return accountId;
    }

    public Long getInstrumentId(){
        return instrumentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PositionId)) return false;
        PositionId that = (PositionId) o;
        return Objects.equals(accountId, that.accountId) &&
            Objects.equals(instrumentId, that.instrumentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, instrumentId);
    }
}