package fr.proxibanque.metier;

import java.util.Date;

public class Virement {

	private String virementID;
	private Date dateVirement;
	private float amount;
	private int sourceAccount;
	private int targetAccount;

	// Constructeurs
	public Virement(String virementID, String dateVirement, int sourceAccount, int targetAccount, int amount) {
		super();
		this.virementID = virementID;
		this.dateVirement = new Date();
		this.sourceAccount = sourceAccount;
		this.targetAccount = targetAccount;
		this.amount = amount;
	}

	public Virement() {
		super();
	}

	// Getters et setteurs
	public String getVirementID() {
		return virementID;
	}

	public void setVirementID(String virementID) {
		this.virementID = virementID;
	}

	public Date getDateVirement() {
		return dateVirement;
	}

	public void setDateVirement(Date dateVirement) {
		this.dateVirement = dateVirement;
	}

	public int getSourceAccount() {
		return sourceAccount;
	}

	public void setSourceAccount(int sourceAccount) {
		this.sourceAccount = sourceAccount;
	}

	public int getTargetAccount() {
		return targetAccount;
	}

	public void setTargetAccount(int targetAccount) {
		this.targetAccount = targetAccount;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float f) {
		this.amount = f;
	}

	// toString
	@Override
	public String toString() {
		return "Virement [virementID=" + virementID + ", dateVirement=" + dateVirement + ", sourceAccount="
				+ sourceAccount + ", targetAccount=" + targetAccount + ", amount=" + amount + "]";
	}

}
