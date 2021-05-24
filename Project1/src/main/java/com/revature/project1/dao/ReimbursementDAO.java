package com.revature.project1.dao;

import java.util.List;

import com.revature.project1.exception.BusinessException;
import com.revature.project1.model.Reimbursement;

public interface ReimbursementDAO {

	public int addPendingReimbursement(Reimbursement r) throws BusinessException;

	public List<Reimbursement> getPendingReimbursements(int userId) throws BusinessException;

	public List<Reimbursement> getAllPendingReimbursements() throws BusinessException;

	public List<Reimbursement> getApprovedReimbursements(int userId) throws BusinessException;

	public List<Reimbursement> getDeniedReimbursements(int userId) throws BusinessException;

	public List<Reimbursement> getAllResolvedReimbursements() throws BusinessException;

	public int updateReimbursement(Reimbursement r) throws BusinessException;

	public int getReimbursement(int reimbId) throws BusinessException;
}
