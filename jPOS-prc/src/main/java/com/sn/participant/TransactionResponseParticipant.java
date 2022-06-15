package com.sn.participant;

import org.jpos.transaction.TransactionParticipant;
import java.io.Serializable;
public class TransactionResponseParticipant implements TransactionParticipant{
    @Override
    public int prepare(long l, Serializable serializable) {

        return PREPARED;
    }
    @Override
    public void commit(long l, Serializable serializable) {

    }
    @Override
    public void abort(long l, Serializable serializable) {

    }
}