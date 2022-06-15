package com.sn.listener;

import com.constant.Constants;
import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOSource;
import org.jpos.space.Space;
import org.jpos.space.SpaceFactory;
import org.jpos.transaction.Context;
public class ServerApplicationListener implements ISORequestListener,Configurable{
    private Configuration configuration;
    @Override
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
    @Override
    public boolean process(ISOSource isoSource, ISOMsg isoMsg) {
        String spaceN = configuration.get("space");
        long timeout = configuration.getLong("spaceTimeout");
        String queueN = configuration.get("queue");
        Context context = new Context();
        Space<String,Context> space = SpaceFactory.getSpace(spaceN);

        ISOMsg respMsg = (ISOMsg)isoMsg.clone();
        try {
            respMsg.setResponseMTI();
        } catch (ISOException e) {
            throw new RuntimeException(e);
        }
        context.put(Constants.REQUEST_KEY,isoMsg);
        context.put(Constants.RESPONSE_KEY,respMsg);
        context.put(Constants.RESOURCE_KEY,isoSource);
        space.out(queueN,context,timeout);//throwing into space
        return true;
    }
}