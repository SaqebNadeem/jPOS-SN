package com.sn.participant;

import org.jpos.transaction.TransactionParticipant;

import java.io.Serializable;

public class ReversalAdviceParticipant implements TransactionParticipant {
    @Override
    public int prepare(long id, Serializable serializable) {

        return PREPARED;
    }
    @Override
    public void commit(long l, Serializable serializable) {

    }
    @Override
    public void abort(long l, Serializable serializable) {

    }
}
