package com.maintest;

import org.jpos.iso.*;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.packager.ISO87APackager;
import org.jpos.util.LogSource;
import org.jpos.util.Logger;
import org.jpos.util.SimpleLogListener;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException, ISOException {
        Logger logger = new Logger();
        logger.addListener(new SimpleLogListener(System.out));
        ISOChannel channel = new ASCIIChannel("localhost",8000,new ISO87APackager());
        ((LogSource)channel).setLogger(logger,"channel");
        channel.connect();

        ISOMsg isoMsg = new ISOMsg();
        HashMap<Integer,String> hm48 = new HashMap<Integer, String>();
        isoMsg.set(0,"0200");
        isoMsg.set(3,"39000");
        isoMsg.set(4,"000000000000");
        isoMsg.set(7, ISODate.getDate(new Date()));
        isoMsg.set(11,"143708");
        isoMsg.set(12,"143708");
        isoMsg.set(13,"1005");
        isoMsg.set(32,"123");
        isoMsg.set(41,"11110001");
        isoMsg.set(42,"111111111100001");
        hm48.put(99,"3647");
        hm48.put(83,"846239");
        hm48.put(51,"4872354");
        isoMsg.set(48, String.valueOf(hm48));
        isoMsg.set(49,"360");

        channel.send(isoMsg);
        channel.receive();
    }
}