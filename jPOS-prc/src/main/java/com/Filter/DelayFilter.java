package com.Filter;

import org.jpos.iso.ISOChannel;
import org.jpos.iso.ISOFilter;
import org.jpos.iso.ISOMsg;
import org.jpos.util.LogEvent;

public class DelayFilter implements ISOFilter{
    private int delay;
    public DelayFilter(){
        delay = 0;
    }
    public DelayFilter(int delay){
        this.delay = delay;
    }
    @Override
    public ISOMsg filter(ISOChannel isoChannel, ISOMsg isoMsg, LogEvent logEvent) throws VetoException {
        logEvent.addMessage ("<delay-filter delay=\""+delay+"\"/>");
        if (delay > 0) {
            try {
                Thread.sleep (delay);
            } catch (InterruptedException e) { }
        }
        return isoMsg;
    }
}
