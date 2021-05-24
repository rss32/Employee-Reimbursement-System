package com.revature.project1.service;

import com.revature.project1.exception.BusinessException;
import com.revature.project1.model.Reimbursement;

public interface ReimbursementService {

	public boolean validateReimbursement(Reimbursement r) throws BusinessException;	

}
