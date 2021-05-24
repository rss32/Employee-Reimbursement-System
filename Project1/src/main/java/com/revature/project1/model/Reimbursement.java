package com.revature.project1.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;

public class Reimbursement implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id, author, resolver;

	private BigDecimal amount;
	private Timestamp submitted;
	private Timestamp resolved;
	private String description;
	private byte[] receipt;
	private String reimbType, reimbStatus;

	public Reimbursement() {
		id = 0;
	}

	public Reimbursement(BigDecimal amt, Timestamp submitted, String description) {
		this();
		amount = amt;
		this.submitted = submitted;		
		this.description = description;
	}

	public Reimbursement(BigDecimal amt, Timestamp submitted, String description, String type, String status) {
		this(amt, submitted, description);
		
		reimbType = type;
		reimbStatus = status;
	}

	public int getResolver() {
		return resolver;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Timestamp getSubmittedTime() {
		return submitted;
	}

	public void setSubmittedTime(Timestamp submitted) {
		this.submitted = submitted;
	}

	public Timestamp getResolvedDate() {
		return resolved;
	}

	public void setResolvedDate(Timestamp resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getReceipt() {
		return receipt;
	}

	public void setReceipt(byte[] receipt) {
		this.receipt = receipt;
	}

	public String getReimbType() {
		return reimbType;
	}

	public void setReimbType(String reimbType) {
		this.reimbType = reimbType;
	}

	public String getReimbStatus() {
		return reimbStatus;
	}

	public void setReimbStatus(String reimbStatus) {
		this.reimbStatus = reimbStatus;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + author;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + Arrays.hashCode(receipt);
		result = prime * result + ((reimbStatus == null) ? 0 : reimbStatus.hashCode());
		result = prime * result + ((reimbType == null) ? 0 : reimbType.hashCode());
		result = prime * result + ((resolved == null) ? 0 : resolved.hashCode());
		result = prime * result + resolver;
		result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (author != other.author)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (!Arrays.equals(receipt, other.receipt))
			return false;
		if (reimbStatus == null) {
			if (other.reimbStatus != null)
				return false;
		} else if (!reimbStatus.equals(other.reimbStatus))
			return false;
		if (reimbType == null) {
			if (other.reimbType != null)
				return false;
		} else if (!reimbType.equals(other.reimbType))
			return false;
		if (resolved == null) {
			if (other.resolved != null)
				return false;
		} else if (!resolved.equals(other.resolved))
			return false;
		if (resolver != other.resolver)
			return false;
		if (submitted == null) {
			if (other.submitted != null)
				return false;
		} else if (!submitted.equals(other.submitted))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", author=" + author + ", resolver=" + resolver + ", amount=" + amount
				+ ", submitted=" + submitted + ", resolved=" + resolved + ", description=" + description
				+ ", reimbType=" + reimbType + ", reimbStatus=" + reimbStatus + "]";
	}

}
