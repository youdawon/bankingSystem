package com.bank.stretegy;

import com.bank.util.Constant;

public class SavingsStrategy implements AccountStrategy {

  @Override
  public double applyInterest() {
    return Constant.CONSUMER_INTEREST_DEFAULT;
  }

  @Override
  public double applyCardLimit() {
    return 0;
  }
}
