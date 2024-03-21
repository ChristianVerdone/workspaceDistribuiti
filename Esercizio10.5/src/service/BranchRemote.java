package service;

import javax.ejb.Remote;

@Remote
public interface BranchRemote {
	public double totalAmount();
	public int createAccount();
	public void deposit(int accountId, double amount);
}
