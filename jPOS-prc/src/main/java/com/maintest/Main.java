package com.maintest;

import com.Filter.DelayFilter;
import org.jpos.iso.*;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.packager.ISO87APackager;
import org.jpos.util.LogSource;
import org.jpos.util.Logger;
import org.jpos.util.SimpleLogListener;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

public class Main  {
    public static void main(String[] args) throws IOException, ISOException {
        Logger logger = new Logger();
        logger.addListener(new SimpleLogListener(System.out));
        FilteredChannel channel = new ASCIIChannel("localhost",8000,new ISO87APackager());
        DelayFilter delayFilter = new DelayFilter(1500);//added channel-Filter -- 1.5 seconds
        channel.addFilter(delayFilter);
        ((LogSource)channel).setLogger(logger,"channel");
        channel.connect();

        ISOMsg isoMsg = new ISOMsg();
        HashMap<String,String> hm48 = new HashMap<String, String>();
        isoMsg.set(0,"0200");
        isoMsg.set(3,"39000");
        isoMsg.set(4,"000000000000");
        isoMsg.set(7,ISODate.getDate(new Date()));
        isoMsg.set(11,"143708");
        isoMsg.set(12,"143708");
        isoMsg.set(13,"1005");
        isoMsg.set(32,"123");
        isoMsg.set(41,"11110001");
        isoMsg.set(42,"111111111100001");
        hm48.put("099","3647");
        hm48.put("083","846239");
        hm48.put("037","9837293");
        hm48.put("051","4872354");
        isoMsg.set(48, String.valueOf(hm48));//adding tags
        isoMsg.set(49,"890");

        channel.send(isoMsg);
        channel.receive();
    }
}