package com.revature.project1.service.impl;

import com.revature.project1.exception.BusinessException;
import com.revature.project1.model.Reimbursement;

import com.revature.project1.service.ReimbursementService;

public class ReimbursementServiceImpl implements ReimbursementService {

	@Override
	public boolean validateReimbursement(Reimbursement r) throws BusinessException {

		if (!r.getAmount().toString().matches("^[0-9]+\\.[0-9]{2}$") || r.getAmount() == null) {
			throw new BusinessException("Invalid amount");
		}

		if (r.getDescription() == null || r.getDescription().replaceAll(" ","").equals("")) {
			throw new BusinessException("Invalid description");
		}
		if (r.getReimbType() == null) {
			throw new BusinessException("Invalid description");
		} else {
			switch (r.getReimbType()) {
			case "other":
			case "lodging":
			case "food":
			case "travel":
				break;
			default:
				throw new BusinessException("invalid expense type");
			}
		}
		return true;
	}
}
