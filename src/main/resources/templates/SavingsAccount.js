function createLoanGiven(account, accountType) {
    var accountValuation = Java.type("com.transactrules.accounts.runtime.accounts.AccountValuation");

    var valuationType = Java.extend(accountValuation,{

        StartDate : function () {
            return Java.super(valuationType).account.dates().get("StartDate").date();
        }


        EndDate : function () {
            return Java.super(valuationType).account.dates().get("EndDate").date();
        }


        AccrualStart : function () {
            return Java.super(valuationType).account.dates().get("AccrualStart").date();
        }

    });

    var valuation = new valuationType();

    valuation.initialize(account,accountType);

    return valuation;
}



