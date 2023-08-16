package hp.sfs.sales.dashboard.service;

import hp.sfs.sales.dashboard.dto.ExpenseDto;
import hp.sfs.sales.dashboard.entity.Expense;
import hp.sfs.sales.dashboard.enums.CreditTransactionType;
import hp.sfs.sales.dashboard.repository.ExpenseRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

  @Autowired
  ExpenseRepository expenseRepository;

  public void saveSalesDetails(AllSalesDetailsDto salesDetails) {
    Operator operator ;
    List<SalesRecord> salesRecords = null;
    List<OilSalesRecord> oilSalesRecords = null;
    List<OnlineDeposit> onlineDeposits = null;
    List<CreditRecord> creditRecords = null;
    List<Expense> expenses = null;

    Long operatorId = salesDetails.getOperatorId();

    if (Optional.ofNullable(operatorId).isPresent()) {
      operator = operatorRepository.findById(operatorId).get();
    } else {
      throw new RuntimeException("Operator not exist");
    }

    // save sales records
    List<SalesRecordDto> salesRecordList = salesDetails.getSalesRecords();
    if (Optional.ofNullable(salesRecordList).isPresent() && !salesRecordList.isEmpty()) {
      salesRecords = saveSalesRecords(salesRecordList);
    }

    // save oil sales
    List<OilSaleDto> oilSalesList = salesDetails.getOilSales();
    if (Optional.ofNullable(oilSalesList).isPresent() && !oilSalesList.isEmpty()) {
      oilSalesRecords = saveOilSales(oilSalesList);
    }

    // save online deposit
    List<OnlineDepositDto> onlineDepositList = salesDetails.getOnlineDeposits();
    if (Optional.ofNullable(onlineDepositList).isPresent() && !onlineDepositList.isEmpty()) {
      onlineDeposits = saveOnlineDeposit(onlineDepositList);
    }

    List<CreditTransactionDto> creditDetails = salesDetails.getCreditTransactions();
    if (Optional.ofNullable(creditDetails).isPresent() && !creditDetails.isEmpty()) {
      creditRecords = saveCreditDetails(creditDetails);
    }

    List<ExpenseDto> expenseDetails = salesDetails.getExpenses();
    if (Optional.ofNullable(expenseDetails).isPresent() && !expenseDetails.isEmpty()) {
      expenses =  saveExpenses(expenseDetails);
    }

    Cash cash = saveCash(salesDetails.getCashCollected());

    saveDeposit(operator, salesRecords, oilSalesRecords, onlineDeposits, creditRecords, expenses, cash);

  }

  private Double getTotalSalesValue(List<SalesRecord> salesRecords,
                                    List<OilSalesRecord> oilSalesRecords) {
    Double salesValue = salesRecords.stream().mapToDouble(SalesRecord::getAmount).sum();
    Double oilSalesValue = oilSalesRecords.stream().mapToDouble(OilSalesRecord::getAmount).sum();
    return salesValue + oilSalesValue;
  }

  private Double getNetCollection(List<OnlineDeposit> onlineDeposits,
                                  List<CreditRecord> creditRecords,
                                  List<Expense> expenses,
                                  Cash cash) {
    double totalOnlineCollection =
        onlineDeposits.stream().mapToDouble(OnlineDeposit::getAmount).sum();
    double creditGiven = creditRecords.stream().filter(c -> c.getTransactionType().equals(
        CreditTransactionType.ISSUED)).mapToDouble(CreditRecord::getAmount).sum();
    double creditCleared = creditRecords.stream().filter(c -> c.getTransactionType().equals(
        CreditTransactionType.DEPOSITED)).mapToDouble(CreditRecord::getAmount).sum();
    double totalExpenses = expenses.stream().mapToDouble(Expense::getAmount).sum();
    return totalOnlineCollection + creditGiven + cash.getCashCollected() - creditCleared - totalExpenses;
  }

  public Cash saveCash(Double cashCollected) {
    Cash cash = new Cash();
    cash.setCashCollected(cashCollected);
    return cashRepositiory.save(cash);
  }

  private List<Expense> saveExpenses(List<ExpenseDto> expenseDetails) {
    Gson gson = new Gson();
    String expenseList = gson.toJson(expenseDetails);
    List<Expense> expenses = Arrays.asList(gson.fromJson(expenseList, Expense[].class));
    return expenseRepository.saveAll(expenses);
  }

  public List<SalesRecord> saveSalesRecords(List<SalesRecordDto> salesRecordList) {
    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy HH:mm:ss").create();
    String salesList = gson.toJson(salesRecordList);
    List<SalesRecord> salesRecords = Arrays.asList(gson.fromJson(salesList, SalesRecord[].class));
    return salesRecordRepository.saveAll(salesRecords);
  }

  public List<OilSalesRecord> saveOilSales(List<OilSaleDto> oilSalesList) {
    Gson gson = new Gson();
    String oilSales = gson.toJson(oilSalesList);
    List<OilSalesRecord> oilSalesRecords =
        Arrays.asList(gson.fromJson(oilSales, OilSalesRecord[].class));
    return oilSalesRepository.saveAll(oilSalesRecords);
  }

  public List<OnlineDeposit> saveOnlineDeposit(List<OnlineDepositDto> onlineDepositList) {
    Gson gson = new Gson();
    String onlineDeposit = gson.toJson(onlineDepositList);
    List<OnlineDeposit> onlineDeposits =
        Arrays.asList(gson.fromJson(onlineDeposit, OnlineDeposit[].class));
    return onlineDepositRepository.saveAll(onlineDeposits);
  }

  public List<CreditRecord> saveCreditDetails(List<CreditTransactionDto> creditDetails) {
    Gson gson = new Gson();
    String creditDetailJson = gson.toJson(creditDetails);
    List<CreditRecord> creditRecords =
        Arrays.asList(gson.fromJson(creditDetailJson, CreditRecord[].class));
    return creditRepository.saveAll(creditRecords);
  }

  public void saveDeposit(Operator operator, List<SalesRecord> salesRecords,
                          List<OilSalesRecord> oilSalesRecords,
                          List<OnlineDeposit> onlineDeposits, List<CreditRecord> creditRecords,
                          List<Expense> expenses,
                          Cash cash) {
    Deposit deposit = new Deposit();
    deposit.setTotalSalesValue(getTotalSalesValue(salesRecords, oilSalesRecords));
    deposit.setNetCollection(getNetCollection(onlineDeposits, creditRecords, expenses, cash));
    deposit.setOperator(operator);
    deposit.setSalesRecords(salesRecords);
    deposit.setOilSalesRecords(oilSalesRecords);
    deposit.setOnlineDeposits(onlineDeposits);
    deposit.setCreditRecords(creditRecords);
    deposit.setCashCollected(cash);
    depositRepository.save(deposit);
  }
}