package com.tyrael.laundry.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyrael.laundry.sequence.TyraelLaundryJobOrderSequence;
import com.tyrael.laundry.service.custom.TyraelLaundryJobOrderSequenceServiceCustom;

public interface TyraelLaundryJobOrderSequenceService extends JpaRepository<TyraelLaundryJobOrderSequence, Long>,
    TyraelLaundryJobOrderSequenceServiceCustom {

}
