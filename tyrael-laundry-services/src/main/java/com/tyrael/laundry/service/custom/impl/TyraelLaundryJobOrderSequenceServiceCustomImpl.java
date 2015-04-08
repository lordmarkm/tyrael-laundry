package com.tyrael.laundry.service.custom.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tyrael.laundry.sequence.TyraelLaundryJobOrderSequence;
import com.tyrael.laundry.service.TyraelLaundryJobOrderSequenceService;
import com.tyrael.laundry.service.custom.TyraelLaundryJobOrderSequenceServiceCustom;

public class TyraelLaundryJobOrderSequenceServiceCustomImpl implements TyraelLaundryJobOrderSequenceServiceCustom {

    @Autowired
    private TyraelLaundryJobOrderSequenceService service;

    @Override
    public String next() {
        List<TyraelLaundryJobOrderSequence> sequenceList = service.findAll();
        TyraelLaundryJobOrderSequence sequence;
        if (sequenceList.isEmpty()) {
            sequence = service.save(new TyraelLaundryJobOrderSequence());
        } else {
            sequence = sequenceList.get(0);
        }

        String next = sequence.next();
        sequence.setSequence(sequence.getSequence() + 1);
        service.save(sequence);

        return next + checkSum(next);
    }

    private int checkSum(String code){
        int val=0;
        for(int i=0;i<code.length();i++){
            val+=((int)Integer.parseInt(code.charAt(i)+""))*((i%2==0)?1:3);
        }

        int checksum_digit = 10 - (val % 10);
        if (checksum_digit == 10) checksum_digit = 0;

        return checksum_digit;
    }
}
