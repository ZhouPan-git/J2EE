package com.yc.biz;

import com.yc.bean.Account;
import com.yc.bean.OpRecord;
import com.yc.bean.OpType;
import com.yc.dao.AccountDao;
import com.yc.dao.OpRecordDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author zp
 * @Date 2023/8/2 14:15
 * @PackageName:com.yc.biz
 * @ClassName: AccountBizImpl
 * @Description:
 * @Version 1.0
 */
@Service
@Log4j2
@Transactional(propagation = Propagation.REQUIRED)
public class AccountBizImpl implements AccountBiz{
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private OpRecordDao opRecordDao;


    @Override
    public Account openAccount(double money) {
        //开户 返回新账号的id
        int accountId=this.accountDao.insert(money);
        //包装流水信息
        OpRecord opRecord=new OpRecord();
        opRecord.setAccountid(accountId);
        opRecord.setOpmoney(money);
        opRecord.setOptype(OpType.DEPOSITE);
        this.opRecordDao.insertOpRecord(opRecord);
        //返回新的账户信息
        Account a=new Account();
        a.setAccountid(accountId);
        a.setBalance(money);
        return a;
    }

    @Override
    public Account findAccount(int accountId) {
        return this.accountDao.findById(accountId);
    }

    @Override
    public Account deposite(int accountId, double money) {
        return this.deposite(accountId,money,null);
    }

    @Override
    public Account deposite(int accountId, double money, Integer transferId) {
        Account a=null;
        try {
            a = this.accountDao.findById(accountId);
        }catch (RuntimeException e) {
            log.error(e.getMessage());
            throw new RuntimeException("查无此账户："+accountId+"无法完成存款操作");
        }
        a.setBalance(a.getBalance()+money);
        this.accountDao.update(accountId,a.getBalance());
        OpRecord opRecord=new OpRecord();
        opRecord.setAccountid(accountId);
        opRecord.setOpmoney(money);
        if (transferId != null) {
            opRecord.setOptype(OpType.TRANSFER);
            opRecord.setTransferid(transferId);
        }else {
            opRecord.setOptype(OpType.WITHDRAW);
        }
        this.opRecordDao.insertOpRecord(opRecord);
        this.accountDao.update(accountId,a.getBalance());
        return a;
    }

    @Override
    public Account withdraw(int accountId, double money) {
        return this.withdraw(accountId, money,null);
    }

    @Override
    public Account withdraw(int accountId, double money, Integer transferId) {
        Account a=null;
        try {
            a = this.accountDao.findById(accountId);
        }catch (RuntimeException e) {
            log.error(e.getMessage());
            throw new RuntimeException("查无此账户："+accountId+"无法完成取款操作");
        }
        a.setBalance(a.getBalance()-money);
        this.accountDao.update(accountId,a.getBalance());
        OpRecord opRecord=new OpRecord();
        opRecord.setAccountid(accountId);
        opRecord.setOpmoney(money);
        if (transferId != null) {
            opRecord.setOptype(OpType.TRANSFER);
            opRecord.setTransferid(transferId);
        }else {
            opRecord.setOptype(OpType.WITHDRAW);
        }
        this.opRecordDao.insertOpRecord(opRecord);
        this.accountDao.update(accountId,a.getBalance());
        return a;
    }

    @Override
    public Account transfer(int accountId, double money,int toAccountId) {
        this.deposite(toAccountId, money, accountId);
        Account a=this.withdraw(accountId,money,toAccountId);
        return a;
    }

}