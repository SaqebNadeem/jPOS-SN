package com.sn.participant;

import com.constant.Constants;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;
import java.io.IOException;
import java.io.Serializable;
public class AuthorizationRequestParticipant implements TransactionParticipant {
    @Override
    public int prepare(long id, Serializable context) {

        return PREPARED;
    }
    @Override
    public void commit(long l, Serializable serializable) {

    }
    @Override
    public void abort(long l, Serializable serializable) {

    }
}

