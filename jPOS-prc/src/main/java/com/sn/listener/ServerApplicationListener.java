package com.sn.listener;

import com.constant.Constants;
import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOSource;
import org.jpos.space.Space;
import org.jpos.space.SpaceFactory;
import org.jpos.transaction.Context;

// we don't make use of server object here as
// from JPOS user guide this approach is not advisable,so we should be using Q2
// not connecting through channel by hardcoding but by using the .xml QBean ,
// but the class Q2 is responsible for connecting
// to the server as its QBean is in resources/deploy directory

// this listener is called by listener.xml
public class ServerApplicationListener implements ISORequestListener,Configurable{
    private Configuration configuration;
    @Override
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;//this method is basically used to update the configuration variable
    }
    @Override
    public boolean process(ISOSource isoSource, ISOMsg isoMsg) {
        System.out.println("SERVER IS ACTIVATED BY SAQEB");
        String spaceN = configuration.get("space");
        long timeout = configuration.getLong("spaceTimeout");
        String queueN = configuration.get("queue");
        Context context = new Context();
        Space<String,Context> space = SpaceFactory.getSpace(spaceN);

        ISOMsg respMsg = (ISOMsg)isoMsg.clone();

        context.put(Constants.REQUEST_KEY,isoMsg);//puts an Object in the transient Map
        context.put(Constants.RESPONSE_KEY,respMsg);//so we can use it to modify in sender-response
        context.put(Constants.RESOURCE_KEY,isoSource);//without changing the original iso-msg
        space.out(queueN,context,timeout);//throwing into space is now accessible to the txnmgr.xml
        //as soon as we put the ctx obj to space it is picked up by txnmgr where it selects the participants
        return true;
    }
}