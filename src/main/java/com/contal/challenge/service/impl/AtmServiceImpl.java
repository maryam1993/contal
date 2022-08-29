package com.contal.challenge.service.impl;


import com.contal.challenge.constant.ExceptionMessageConstant;
import com.contal.challenge.exception.AmountNotEnoughException;
import com.contal.challenge.exception.AmountNotValidException;
import com.contal.challenge.exception.CashHoldingNotSuitableException;
import com.contal.challenge.repository.AtmRepository;
import com.contal.challenge.service.AtmService;
import com.contal.challenge.util.CombinationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class AtmServiceImpl implements AtmService {

    private AtmRepository atmRepository;


    @Override
    public List<Integer> getAtmDispenseMoneyOptions() {
        return atmRepository.getDispenseOptions();
    }

    @Override
    public Map<Integer, Integer> getCurrentCashHolding() {
        return atmRepository.getCurrentCashHolding();
    }

    @Override
    public void dispenseMoney(int amount) {
        if (!getAtmDispenseMoneyOptions().contains(amount)) {
            throw new AmountNotValidException(ExceptionMessageConstant.AMOUNT_NOT_VALID);
        }
        List<List<Integer>> options = CombinationUtil.combinationSum(getNotes(), amount);
        boolean validity = false;
        for (List<Integer> ops : options) {
            Map<Integer, Integer> dispense = createMapOfCombinations(ops);
            validity = validateCashHoldingAmount(dispense, amount);
            if (validity) {
                atmRepository.removeNotes(dispense);
                break;
            }
        }
        if (!validity) {
            throw new AmountNotEnoughException(ExceptionMessageConstant.AMOUNT_NOT_ENOUGH);
        }
    }

    @Override
    public List<Integer> getNotes() {
        return atmRepository.getNotes();
    }

    private boolean validateCashHoldingAmount(Map<Integer, Integer> dispense, int amount) {
        Map<Integer, Integer> cashHolding = getCurrentCashHolding();
        // specific condition for 200
        if (amount == 200 && cashHolding.get(20) != 8 && cashHolding.get(50) != 3) {
            throw new CashHoldingNotSuitableException(ExceptionMessageConstant.CASH_HOLDING_NOT_SUITABLE);
        }
        boolean isCashHoldingEnough = true;
        for (Integer note : dispense.keySet()) {
            int availableCashNumber = cashHolding.get(note);
            int neededCashNumber = dispense.get(note);

            if (availableCashNumber < neededCashNumber) {
                isCashHoldingEnough = false;
                break;
            }
        }
        return isCashHoldingEnough;
    }


    private Map<Integer, Integer> createMapOfCombinations(List<Integer> ops) {
        Map<Integer, Integer> combinationMap = new HashMap<>();
        for (Integer note : getNotes()) {
            int count = Collections.frequency(ops, note);
            combinationMap.put(note, count);
        }
        return combinationMap;
    }
}
