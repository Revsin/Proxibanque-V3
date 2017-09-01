/**
 * 
 */
package fr.proxibanque.metier;

/**
 * @author SEBASTIENM
 *
 */
public class BasicAccount {

	private static int nextAvailableAccountNumber;

	protected String accountNumber;
	protected String creationDate;
	protected String accountType;
	protected float accountBalance;
	protected int client_id;

	/**
	 * @param ACCOUNT_TYPE
	 *            Account type must be a Constant - enterprise or personal Defaults
	 *            to personal account type
	 * 
	 * @param creationDate
	 *            Creation date format "YYYY-MM-DD" (no check performed) Defaults to
	 *            Constants.DEFAULT_CREATION_DATE if left empty
	 */
	public BasicAccount(String ACCOUNT_TYPE, String creationDate) {
		super();

		if (BasicAccount.nextAvailableAccountNumber < 0)
			BasicAccount.nextAvailableAccountNumber = 0;

		// Concatenation with "" necessary in order to create String and cast int
		accountNumber = "" + BasicAccount.nextAvailableAccountNumber++;

		if (ACCOUNT_TYPE == null)
			accountType = Constants.ACCOUNT_TYPE_PERSONAL;
		else if (ACCOUNT_TYPE == Constants.ACCOUNT_TYPE_ENTERPRISE)
			accountType = Constants.ACCOUNT_TYPE_ENTERPRISE;
		else
			accountType = Constants.ACCOUNT_TYPE_PERSONAL;

		if (creationDate == null)
			this.creationDate = Constants.DEFAULT_CREATION_DATE;
		else if (creationDate.isEmpty())
			this.creationDate = Constants.DEFAULT_CREATION_DATE;
		else
			this.creationDate = creationDate;
	}

	public BasicAccount() {
		super();
	}

	public BasicAccount(String accountNumber, String creationDate, String accountType, float accountBalance,
			int client_id) {
		super();
		this.accountNumber = accountNumber;
		this.creationDate = creationDate;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.client_id = client_id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public float getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(float accountBalance) {
		this.accountBalance = accountBalance;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	@Override
	public String toString() {
		return "BasicAccount [accountNumber=" + accountNumber + ", creationDate=" + creationDate + ", accountType="
				+ accountType + ", accountBalance=" + accountBalance + ", client_id=" + client_id + "]";
	}

}
