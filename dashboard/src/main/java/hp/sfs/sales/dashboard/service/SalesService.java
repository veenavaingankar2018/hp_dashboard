package hp.sfs.sales.dashboard.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import hp.sfs.sales.dashboard.dto.AllSalesDetailsDto;
import hp.sfs.sales.dashboard.dto.CreditTransactionDto;
import hp.sfs.sales.dashboard.dto.OilSaleDto;
import hp.sfs.sales.dashboard.dto.OnlineDepositDto;
import hp.sfs.sales.dashboard.dto.SalesRecordDto;
import hp.sfs.sales.dashboard.entity.Cash;
import hp.sfs.sales.dashboard.entity.CreditRecord;
import hp.sfs.sales.dashboard.entity.Deposit;
import hp.sfs.sales.dashboard.entity.OilSalesRecord;
import hp.sfs.sales.dashboard.entity.OnlineDeposit;
import hp.sfs.sales.dashboard.entity.Operator;
import hp.sfs.sales.dashboard.entity.SalesRecord;
import hp.sfs.sales.dashboard.repository.CashRepositiory;
import hp.sfs.sales.dashboard.repository.CreditRepository;
import hp.sfs.sales.dashboard.repository.DepositRepository;
import hp.sfs.sales.dashboard.repository.OilSalesRepository;
import hp.sfs.sales.dashboard.repository.OnlineDepositRepository;
import hp.sfs.sales.dashboard.repository.OperatorRepository;
import hp.sfs.sales.dashboard.repository.SalesRecordRepository;

@Service
public class SalesService {

	@Autowired
	OperatorRepository operatorRepository;

	@Autowired
	SalesRecordRepository salesRecordRepository;

	@Autowired
	OilSalesRepository oilSalesRepository;

	@Autowired
	OnlineDepositRepository onlineDepositRepository;

	@Autowired
	DepositRepository depositRepository;

	@Autowired
	CreditRepository creditRepository;

	@Autowired
	CashRepositiory cashRepositiory;

	public void saveSalesDetails(AllSalesDetailsDto salesDetails) {
		Operator operator = null;
		List<SalesRecord> salesRecords = null;
		List<OilSalesRecord> oilSalesRecords = null;
		List<OnlineDeposit> onlineDeposits = null;
		List<CreditRecord> creditRecords = null;

		Long operatorId = salesDetails.getOperatorId();

		if (Optional.ofNullable(operatorId).isPresent()) {
			operator = operatorRepository.findById(operatorId).get();
		} else {
			throw new RuntimeException("Operator not exist");
		}

		// save sales records
		List<SalesRecordDto> salesRecordList = salesDetails.getSalesRecords();
		if (Optional.ofNullable(salesRecordList).isPresent() && !salesRecordList.isEmpty())
			salesRecords = saveSalesRecords(salesRecordList);

		// save oil sales
		List<OilSaleDto> oilSalesList = salesDetails.getOilSales();
		if (Optional.ofNullable(oilSalesList).isPresent() && !oilSalesList.isEmpty())
			oilSalesRecords = saveOilSales(oilSalesList);

		// save online deposit
		List<OnlineDepositDto> onlineDepositList = salesDetails.getOnlineDeposits();
		if (Optional.ofNullable(onlineDepositList).isPresent() && !onlineDepositList.isEmpty())
			onlineDeposits = saveOnlineDeposit(onlineDepositList);

		List<CreditTransactionDto> creditDetails = salesDetails.getCreditTransactions();
		if (Optional.ofNullable(creditDetails).isPresent() && !creditDetails.isEmpty())
			creditRecords = saveCreditDetails(creditDetails);

		Cash cash = saveCash(salesDetails.getCashCollected());

		saveDeposit(operator, salesRecords, oilSalesRecords, onlineDeposits, creditRecords, cash,
				salesDetails.getDifference());

	}

	public Cash saveCash(Double cashCollected) {
		Cash cash = new Cash();
		cash.setCashCollected(cashCollected);
		return cashRepositiory.save(cash);
	}

	public List<SalesRecord> saveSalesRecords(List<SalesRecordDto> salesRecordList) {
		Gson gson = new Gson();
		String salesList = gson.toJson(salesRecordList);
		List<SalesRecord> salesRecords = Arrays.asList(gson.fromJson(salesList, SalesRecord[].class));
		return salesRecordRepository.saveAll(salesRecords);
	}

	public List<OilSalesRecord> saveOilSales(List<OilSaleDto> oilSalesList) {
		Gson gson = new Gson();
		String oilSales = gson.toJson(oilSalesList);
		List<OilSalesRecord> oilSalesRecords = Arrays.asList(gson.fromJson(oilSales, OilSalesRecord[].class));
		return oilSalesRepository.saveAll(oilSalesRecords);
	}

	public List<OnlineDeposit> saveOnlineDeposit(List<OnlineDepositDto> onlineDepositList) {
		Gson gson = new Gson();
		String onlineDeposit = gson.toJson(onlineDepositList);
		List<OnlineDeposit> onlineDeposits = Arrays.asList(gson.fromJson(onlineDeposit, OnlineDeposit[].class));
		return onlineDepositRepository.saveAll(onlineDeposits);
	}

	public List<CreditRecord> saveCreditDetails(List<CreditTransactionDto> creditDetails) {
		Gson gson = new Gson();
		String creditDetailJson = gson.toJson(creditDetails);
		List<CreditRecord> creditRecords = Arrays.asList(gson.fromJson(creditDetailJson, CreditRecord[].class));
		return creditRepository.saveAll(creditRecords);
	}

	public void saveDeposit(Operator operator, List<SalesRecord> salesRecords, List<OilSalesRecord> oilSalesRecords,
			List<OnlineDeposit> onlineDeposits, List<CreditRecord> creditRecords, Cash cash, Double difference) {
		Deposit deposit = new Deposit();
		deposit.setDifference(difference);
		deposit.setOperator(operator);
		deposit.setSalesRecords(salesRecords);
		deposit.setOilSalesRecords(oilSalesRecords);
		deposit.setOnlineDeposits(onlineDeposits);
		deposit.setCreditRecords(creditRecords);
		deposit.setCashCollected(cash);
		depositRepository.save(deposit);
	}

}
