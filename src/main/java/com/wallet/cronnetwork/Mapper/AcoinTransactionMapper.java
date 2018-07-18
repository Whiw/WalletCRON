package com.wallet.cronnetwork.Mapper;


import com.wallet.cronnetwork.Data.TradeListDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface AcoinTransactionMapper {

/*	public void addTransaction(AcoinTransactionsDto transactionDTO) throws RuntimeException;
	public PagedList<AcoinTransactionsDto> queryPageTransactioninfo(PagingRowBounds bounds) throws RuntimeException;
	public PagedList<AcoinTransactionsDto> queryPageTransactioninfobyHash(String transaction_hash, PagingRowBounds bounds) throws RuntimeException;
	public PagedList<AcoinTransactionsDto> queryPageTransactioninfobyLedgerindex(Integer ledger_index, PagingRowBounds bounds) throws RuntimeException;
	public PagedList<AcoinTransactionsDto> queryPageTransactioninfobyAccountSend(String account, PagingRowBounds bounds) throws RuntimeException;
	public PagedList<AcoinTransactionsDto> queryPageTransactioninfobyAccountReceive(String account, PagingRowBounds bounds) throws RuntimeException;*/
	@Select("Select ncoin_address from ncoin_address_info where id = #{id}")
	ArrayList<String> getAddress(String id);

	@Select("SELECT * FROM wiz_acoin_transactions where account = #{account}")
	ArrayList<TradeListDto> getSendLog(String account);

	@Select("SELECT * FROM wiz_acoin_transactions where destination = #{account}")
	ArrayList<TradeListDto> getReceiveList(String account);
}
