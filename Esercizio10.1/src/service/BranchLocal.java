package service;

import javax.ejb.Local;

@Local
public interface BranchLocal {
	public double totalAmount();
	public double totalAmountQ();
	public void createAccount(double a);
}
