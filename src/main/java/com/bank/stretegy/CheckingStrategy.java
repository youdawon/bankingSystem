package com.bank.stretegy;

import com.bank.util.Constant;

public class CheckingStrategy implements AccountStrategy {

  public CheckingStrategy() {
  }

  @Override
  public double applyCardLimit() {
    return Constant.CONSUMER_CARD_LIMIT_DEFAULT;
  }

  @Override
  public double applyInterest() {
    return Constant.CONSUMER_INTEREST_DEFAULT;
  }
}
