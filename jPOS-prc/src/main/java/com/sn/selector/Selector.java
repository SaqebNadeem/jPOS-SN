package com.sn.selector;

import com.constant.Constants;
import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.GroupSelector;
import java.io.Serializable;
public class Selector implements GroupSelector,Configurable{
    private Configuration configuration;
    @Override
    public void setConfiguration(Configuration cfg) throws ConfigurationException {
        this.configuration = cfg;
    }
    @Override
    public String select(long l, Serializable serializable) {
        Context ctx = (Context)serializable;
        ISOMsg resIsoMsg = ctx.get(Constants.REQUEST_KEY);//getting the req key String i.e req MTI & casting it into isomsg
        String selector = "";
        try {
            selector = configuration.get(resIsoMsg.getMTI());//getting MTI from the space\or\what we earlier have mentioned in main() which decides the participant
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