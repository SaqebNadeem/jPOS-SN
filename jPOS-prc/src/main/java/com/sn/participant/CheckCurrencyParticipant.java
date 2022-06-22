package com.sn.participant;

import com.constant.Constants;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;
import java.io.Serializable;
public class CheckCurrencyParticipant implements TransactionParticipant {
    @Override
    public int prepare(long id, Serializable context) {
        Context ctx = (Context)context;
        ISOMsg respMsg = ctx.get(Constants.RESPONSE_KEY);
        String bit49 = respMsg.getString(49);
        if(bit49.equals("360")){
            return PREPARED;
        }
        return ABORTED;
    }
    @Override
    public void commit(long l, Serializable serializable) {

    }
    @Override
    public void abort(long l, Serializable serializable) {

    }
}
