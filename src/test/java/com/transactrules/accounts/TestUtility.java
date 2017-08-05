package com.transactrules.accounts;

import com.transactrules.accounts.configuration.*;

public class TestUtility {
    public static AccountType CreateLoanGivenAccountType()
    {
        AccountType loanGiven = new AccountType("LoanGiven");

        PositionType conversionInterestPosition = loanGiven.addPositionType("ConversionInterest");
        PositionType earlyRedemptionFeePosition = loanGiven.addPositionType("EarlyRedemptionFee");
        PositionType interestAccruedPosition = loanGiven.addPositionType("InterestAccrued");
        PositionType interestCapitalizedPosition = loanGiven.addPositionType("InterestCapitalized");
        PositionType principalPosition = loanGiven.addPositionType( "Principal" );

        DateType startDate = loanGiven.addDateType( "StartDate" );
        DateType accrualStart = loanGiven.addDateType( "AccrualStart" );
        DateType endDate = loanGiven.addDateType( "EndDate" );


        ScheduleType accrualSchedule = loanGiven.addCalculatedScheduleType(
            "AccrualSchedule",
                ScheduleFrequency.Daily,
                ScheduleEndType.NoEnd,
                "this.StartDate",
                "",
                "",
                "1"
        );

       ScheduleType interestSchedule =loanGiven.addUserInputScheduleType(
        "InterestSchedule",
               ScheduleFrequency.Monthly,
               ScheduleEndType.EndDate,
               null,
               null,
               null,
               "1");

       ScheduleType redemptionSchedule = loanGiven.addUserInputScheduleType(
            "RedemptionSchedule",
            ScheduleFrequency.Monthly,
            ScheduleEndType.EndDate,
            null,
            null,
            null,
            "1"
        );

        TransactionType interestAccrued=  loanGiven.addTransactionType(
                "InterestAccrued",
                true);

        interestAccrued.addRule(interestAccruedPosition, TransactionOperation.Add);


        TransactionType interestCapitalizedTransactionType = loanGiven.addTransactionType(
            "InterestCapitalized",
            false);

        interestCapitalizedTransactionType.addRule(principalPosition,  TransactionOperation.Add );
        interestCapitalizedTransactionType.addRule(interestAccruedPosition,  TransactionOperation.Subtract );
        interestCapitalizedTransactionType.addRule(interestCapitalizedPosition, TransactionOperation.Add );


        TransactionType redemptionTransactionType = loanGiven.addTransactionType("Redemption", false);

        redemptionTransactionType.addRule(principalPosition, TransactionOperation.Subtract );


        TransactionType advanceTransactionType = loanGiven.addTransactionType("Advance", false);

        advanceTransactionType.addRule( principalPosition,  TransactionOperation.Add );

/*

            new TransactionType {
                Name = "AdditionalAdvance",
                        TransactionRules = new List<TransactionRuleType> {
                    new TransactionRuleType { PositionType = principalPosition, TransactionOperation = TransactionOperation.Add }
                }
            },
            advanceTransactionType,
                    new TransactionType {
                Name = "ConversionInterest",
                        TransactionRules = new List<TransactionRuleType> {
                    new TransactionRuleType { PositionType = conversionInterestPosition, TransactionOperation = TransactionOperation.Add }
                }
            },

            new TransactionType {
                Name = "EarlyRedemptionFee",
                        TransactionRules = new List<TransactionRuleType> {
                    new TransactionRuleType { PositionType = earlyRedemptionFeePosition, TransactionOperation = TransactionOperation.Add }
                }
            },

            new TransactionType {
                Name = "FXResultInterest",
                        TransactionRules = new List<TransactionRuleType> {
                    new TransactionRuleType { PositionType = interestAccruedPosition, TransactionOperation = TransactionOperation.Add }
                }
            },

            new TransactionType {
                Name = "FXResultPrincipal",
                        TransactionRules = new List<TransactionRuleType> {
                    new TransactionRuleType { PositionType = principalPosition, TransactionOperation = TransactionOperation.Add }
                }
            },


                    new TransactionType {
                Name = "InterestPayment",
                        TransactionRules = new List<TransactionRuleType> {
                    new TransactionRuleType { PositionType = interestAccruedPosition, TransactionOperation = TransactionOperation.Subtract }
                }
            },

        /*

            AmountTypes = new List<AmountType> {
            new AmountType { Name = "RedemptionAmount" },
            new AmountType { Name = "AdditionalAdvanceAmount" },
            new AmountType { Name = "ConversionInterestAmount" },
            new AmountType { Name = "AdvanceAmount" },
        },

            DateTypes = new List<DateType> {
            accrualStart,
                    startDate,
                    endDate,
        },

            ScheduleTypes = new List<ScheduleType>(){
                accrualSchedule,
                interestSchedule,
                redemptionSchedule
            },

                    RateTypes = new List<RateType>() {
                        new RateType { Name = "InterestRate" }
                    },

                    OptionTypes = new List<OptionType>() {
                        new OptionType {Name = "AccrualOption",
                                OptionListExpression = "TransactRules.Calculations.AccrualCalculation.AccrualOptions()"}
                    }
                    ,
                    ScheduledTransactions = new List<ScheduledTransaction>() {
                         new ScheduledTransaction {
                            AmountExpression = "AdvanceAmount",
                                    DateType = startDate,
                                    Timing = ScheduledTransactionTiming.StartOfDay,
                                    TransactionType = advanceTransactionType,
                                    Sequence =1
                        },
                                new ScheduledTransaction {
                            AmountExpression = "TransactRules.Calculations.AccrualCalculation.InterestAccrued(accrualOption: AccrualOption, principal: Principal, rate: InterestRate, valueDate:  TransactRules.Core.Utilities.SessionState.Current.ValueDate)",
                                    ScheduleType = accrualSchedule,
                                    Timing = ScheduledTransactionTiming.EndOfDay,
                                    TransactionType = interestAccruedTransactionType,
                                    Sequence =1
                        },
                                new ScheduledTransaction {
                            AmountExpression = "InterestAccrued",
                                    ScheduleType = interestSchedule,
                                    Timing = ScheduledTransactionTiming.EndOfDay,
                                    TransactionType = interestCapitalizedTransactionType,
                                    Sequence =2
                        }
                    },

                    InstalmentTypes = new List<InstalmentType>() {
                        new InstalmentType {
                            InterestACapitalized = interestCapitalizedPosition,
                                    InterestAccrued = interestAccruedPosition,
                                    Name ="Redemptions",
                                    PrincipalPositionType = principalPosition,
                                    ScheduleType = redemptionSchedule,
                                    Timing = ScheduledTransactionTiming.StartOfDay,
                                    TransactionType = redemptionTransactionType
                        }
                    }
        };
*/
        return loanGiven;
    }

}
