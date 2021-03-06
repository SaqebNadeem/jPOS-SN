package com.sn.selector;

import com.constant.Constants;
import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.GroupSelector;
import java.io.Serializable;
public class Selector implements GroupSelector,Configurable{
    private Configuration configuration;//Configurable inherits almost all jpos classes and interfaces
    @Override
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration; //this method is basically used to update the configuration variable
    }
    @Override
    public String select(long l, Serializable serializable) {
        Context ctx = (Context)serializable;
        ISOMsg isoMsg = ctx.get(Constants.REQUEST_KEY);//getting the req key String that is req MTI & casting it into ISOMsg
        String selector = "";
        try {
            selector = configuration.get(isoMsg.getMTI());//getting MTI from the space\or\what we earlier have mentioned in main() which decides the participant
        } catch (ISOException e) {
            e.printStackTrace();
        }
        return selector;
    }
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