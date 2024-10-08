package com.bank.stretegy;

import com.bank.util.Constant;

public class CommercialSavingsStrategy implements AccountStrategy {

  @Override
  public double applyInterest() {
    return Constant.COMMERCIAL_INTEREST_DEFAULT;
  }

  @Override
  public double applyCardLimit() {
    return 0;
  }
}
