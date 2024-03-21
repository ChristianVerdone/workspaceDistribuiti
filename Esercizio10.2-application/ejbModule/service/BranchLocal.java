package service;

import javax.ejb.Local;

@Local
public interface BranchLocal {
	public double totalAmount();
	public double totalAmountQ();
	public int createAccount();
	public void deposit (int accountId,double amount);
}
