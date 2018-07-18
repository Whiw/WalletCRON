package com.wallet.cronnetwork.Data;

import lombok.Data;

@Data
public class TradeListDto {
    String account;
    String destination;
    String amount;
    String transaction_hash;
    String reg_date;

}
