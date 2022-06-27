package com.sn.participant;

import com.constant.Constants;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;
import java.io.Serializable;
public class AuthorizationRequestParticipant implements TransactionParticipant {
    @Override
    public int prepare(long id, Serializable serializable) {
        Context ctx = (Context)serializable;
        ISOMsg respMsg = ctx.get(Constants.RESPONSE_KEY);
        respMsg.set(38,"060000");
        ctx.put(Constants.RESPONSE_KEY,respMsg);
        return PREPARED;
    }
    @Override
    public void commit(long l, Serializable serializable) {

    }
    @Override
    public void abort(long l, Serializable serializable) {

    }
}

