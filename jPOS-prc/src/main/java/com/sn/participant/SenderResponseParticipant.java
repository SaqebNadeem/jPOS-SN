package com.sn.participant;

import com.constant.Constants;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;
import java.io.IOException;
import java.io.Serializable;
public class SenderResponseParticipant implements TransactionParticipant {
    @Override
    public int prepare(long l, Serializable serializable) {

//        Context ctx = (Context) serializable;
//        ISOMsg respMsg = ctx.get(Constants.REQUEST_KEY)
//        example for how to not echo a field in the response
//        respMsg.set(49,(String) null);
        return PREPARED;
    }
    @Override
    public void commit(long l, Serializable serializable) {

        Context ctx = (Context) serializable;
        ISOMsg respMsg = ctx.get(Constants.REQUEST_KEY);
        try {
            respMsg.setResponseMTI();
        } catch (ISOException e) {
            throw new RuntimeException(e);
        }
        String bit38 = respMsg.getString(38);
        String bit39 = respMsg.getString(39);
        if (bit38==null) {
            respMsg.set(38, "060000");
        }
        if (bit39==null) {
            respMsg.set(39, "00");
        }
        ctx.put(Constants.RESPONSE_KEY, respMsg);
        ISOSource source = ctx.get(Constants.RESOURCE_KEY);
        ISOMsg msgResp = ctx.get(Constants.RESPONSE_KEY);
        try {
            source.send(msgResp);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ISOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void abort(long l, Serializable serializable) {

        Context ctx = (Context)serializable;
        ISOMsg respMsg = ctx.get(Constants.REQUEST_KEY);
        try {
            respMsg.setResponseMTI();
        } catch (ISOException e) {
            throw new RuntimeException(e);
        }
        String bit38 = respMsg.getString(38);
        String bit39 = respMsg.getString(39);
//        String bit49 = respMsg.getString(49);
        if(bit38==null || bit38.equals("060000")){
            respMsg.set(38,"060066");
        }
        if(bit39==null || bit39.equals("00")){
            respMsg.set(39,"06");
        }
//        if(!bit49.equals("360")){
//            respMsg.set(49,"006");
//        }
        ctx.put(Constants.RESPONSE_KEY,respMsg);
        ISOSource source = ctx.get(Constants.RESOURCE_KEY);
        ISOMsg msgResp = ctx.get(Constants.RESPONSE_KEY);
        try {
            source.send(msgResp);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ISOException e) {
            e.printStackTrace();
        }
    }
}
