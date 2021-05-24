package com.revature.project1.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.revature.project1.dao.ReimbursementDAO;
import com.revature.project1.exception.BusinessException;
import com.revature.project1.model.Reimbursement;
import com.revature.project1.util.dbutil.DBConnectionUtil;

public class ReimbursementDAOImpl implements ReimbursementDAO {

	@Override
	public int addPendingReimbursement(Reimbursement r) throws BusinessException {

		int updatedRecords = 0;
		try (Connection conn = DBConnectionUtil.getConnection()) {

			String sql = "insert into ers.ers_reimbursement(reimb_amount, reimb_submitted,reimb_description, "
					+ "reimb_author, reimb_status_id, reimb_type_id) " + "values(?,?,?,?,1, "
					+ "(select reimb_type_id from ers.ers_reimbursement_type where reimb_type = ? ))";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setBigDecimal(1, r.getAmount());
			pstmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			pstmt.setString(3, r.getDescription());
			pstmt.setInt(4, r.getAuthor());
			pstmt.setString(5, r.getReimbType());

			updatedRecords = pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException | IOException e) {
			throw new BusinessException("Contact System Admin. " + e.getMessage());
		}
		return updatedRecords;

	}

	@Override
	public List<Reimbursement> getPendingReimbursements(int userId) throws BusinessException {
		
		List<Reimbursement> reimbList = new ArrayList<>();
		Reimbursement r;

		try (Connection conn = DBConnectionUtil.getConnection()) {
			String sql = "select r.reimb_id, r.reimb_amount, r.reimb_submitted,r.reimb_description, "
					+ "rs.reimb_status, rt.reimb_type from ers.ers_reimbursement r join "
					+ "ers.ers_reimbursement_status rs on r.reimb_status_id =rs.reimb_status_id "
					+ "join ers.ers_reimbursement_type rt on r.reimb_type_id = rt.reimb_type_id "
					+ "where r.reimb_author = ? and rs.reimb_status = 'pending'";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);

			ResultSet results = pstmt.executeQuery();
			while (results.next()) {
				r = new Reimbursement();

				r.setId(results.getInt("reimb_id"));
				r.setAmount(results.getBigDecimal("reimb_amount"));
				r.setSubmittedTime(results.getTimestamp("reimb_submitted"));
				r.setDescription(results.getString("reimb_description"));
				r.setReimbStatus(results.getString("reimb_status"));
				r.setReimbType(results.getString("reimb_type"));
				r.setAuthor(userId);

				reimbList.add(r);
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			throw new BusinessException("Contact System Admin. " + e.getMessage());
		}
		return reimbList;
	}

	@Override
	public List<Reimbursement> getAllPendingReimbursements() throws BusinessException {

		List<Reimbursement> reimbList = new ArrayList<>();
		Reimbursement r;

		try (Connection conn = DBConnectionUtil.getConnection()) {
			String sql = "select r.reimb_id, r.reimb_amount, r.reimb_submitted,r.reimb_description, "
					+ "r.reimb_author, rs.reimb_status, rt.reimb_type from ers.ers_reimbursement r join "
					+ "ers.ers_reimbursement_status rs on r.reimb_status_id =rs.reimb_status_id "
					+ "join ers.ers_reimbursement_type rt on r.reimb_type_id = rt.reimb_type_id "
					+ "where rs.reimb_status = 'pending'";

			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet results = pstmt.executeQuery();
			while (results.next()) {
				r = new Reimbursement();

				r.setId(results.getInt("reimb_id"));
				r.setAmount(results.getBigDecimal("reimb_amount"));
				r.setSubmittedTime(results.getTimestamp("reimb_submitted"));
				r.setDescription(results.getString("reimb_description"));
				r.setReimbStatus(results.getString("reimb_status"));
				r.setReimbType(results.getString("reimb_type"));
				r.setAuthor(results.getInt("reimb_author"));

				reimbList.add(r);
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			throw new BusinessException("Contact System Admin. " + e.getMessage());
		}
		return reimbList;
	}

	@Override
	public List<Reimbursement> getApprovedReimbursements(int userId) throws BusinessException {
		List<Reimbursement> reimbList = new ArrayList<>();
		Reimbursement r;

		try (Connection conn = DBConnectionUtil.getConnection()) {
			String sql = "select r.reimb_id, r.reimb_amount, r.reimb_submitted, r.reimb_resolved, "
					+ "r.reimb_description,r.reimb_resolver,rs.reimb_status, rt.reimb_type "
					+ "from ers.ers_reimbursement r join ers.ers_reimbursement_status rs "
					+ "on r.reimb_status_id =rs.reimb_status_id "
					+ "join ers.ers_reimbursement_type rt on r.reimb_type_id = rt.reimb_type_id "
					+ "where r.reimb_author = ? and rs.reimb_status = 'approved'";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);

			ResultSet results = pstmt.executeQuery();
			while (results.next()) {
				r = new Reimbursement();

				r.setId(results.getInt("reimb_id"));
				r.setAmount(results.getBigDecimal("reimb_amount"));
				r.setSubmittedTime(results.getTimestamp("reimb_submitted"));
				r.setResolvedDate(results.getTimestamp("reimb_resolved"));
				r.setDescription(results.getString("reimb_description"));
				r.setResolver(results.getInt("reimb_resolver"));
				r.setReimbStatus(results.getString("reimb_status"));
				r.setReimbType(results.getString("reimb_type"));

				reimbList.add(r);
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			throw new BusinessException("Contact System Admin. " + e.getMessage());
		}
		return reimbList;
	}

	@Override
	public List<Reimbursement> getDeniedReimbursements(int userId) throws BusinessException {
		List<Reimbursement> reimbList = new ArrayList<>();
		Reimbursement r;

		try (Connection conn = DBConnectionUtil.getConnection()) {
			String sql = "select r.reimb_id, r.reimb_amount, r.reimb_submitted, r.reimb_resolved, "
					+ "r.reimb_description,r.reimb_resolver,rs.reimb_status, rt.reimb_type "
					+ "from ers.ers_reimbursement r join ers.ers_reimbursement_status rs "
					+ "on r.reimb_status_id =rs.reimb_status_id "
					+ "join ers.ers_reimbursement_type rt on r.reimb_type_id = rt.reimb_type_id "
					+ "where r.reimb_author = ? and rs.reimb_status = 'denied'";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);

			ResultSet results = pstmt.executeQuery();
			while (results.next()) {
				r = new Reimbursement();

				r.setId(results.getInt("reimb_id"));
				r.setAmount(results.getBigDecimal("reimb_amount"));
				r.setSubmittedTime(results.getTimestamp("reimb_submitted"));
				r.setResolvedDate(results.getTimestamp("reimb_resolved"));
				r.setDescription(results.getString("reimb_description"));
				r.setResolver(results.getInt("reimb_resolver"));
				r.setReimbStatus(results.getString("reimb_status"));
				r.setReimbType(results.getString("reimb_type"));

				reimbList.add(r);
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			throw new BusinessException("Contact System Admin. " + e.getMessage());
		}
		return reimbList;
	}

	@Override
	public List<Reimbursement> getAllResolvedReimbursements() throws BusinessException {
		List<Reimbursement> reimbList = new ArrayList<>();
		Reimbursement r;

		try (Connection conn = DBConnectionUtil.getConnection()) {
			String sql = "select  r.reimb_id, r.reimb_amount, r.reimb_submitted,r.reimb_resolved,r.reimb_description, "
					+ "r.reimb_author,r.reimb_resolver,rs.reimb_status, rt.reimb_type from ers.ers_reimbursement r "
					+ "join ers.ers_reimbursement_status rs on r.reimb_status_id =rs.reimb_status_id "
					+ "join ers.ers_reimbursement_type rt on r.reimb_type_id =rt.reimb_type_id "
					+ "where rs.reimb_status = 'approved' or rs.reimb_status ='denied'";

			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet results = pstmt.executeQuery();
			while (results.next()) {
				r = new Reimbursement();

				r.setId(results.getInt("reimb_id"));
				r.setAmount(results.getBigDecimal("reimb_amount"));
				r.setSubmittedTime(results.getTimestamp("reimb_submitted"));
				r.setResolvedDate(results.getTimestamp("reimb_resolved"));
				r.setDescription(results.getString("reimb_description"));
				r.setAuthor(results.getInt("reimb_author"));
				r.setResolver(results.getInt("reimb_resolver"));
				r.setReimbStatus(results.getString("reimb_status"));
				r.setReimbType(results.getString("reimb_type"));

				reimbList.add(r);
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			throw new BusinessException("Contact System Admin. " + e.getMessage());
		}
		return reimbList;
	}

	@Override
	public int updateReimbursement(Reimbursement r) throws BusinessException {
		int updatedRecords = 0;
		try (Connection conn = DBConnectionUtil.getConnection()) {

			String sql = "update ers.ers_reimbursement set reimb_resolved = ?,reimb_resolver =? , "
					+ "reimb_status_id = (select reimb_status_id from ers.ers_reimbursement_status "
					+ "where reimb_status = ?) where reimb_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			pstmt.setInt(2, r.getResolver());
			pstmt.setString(3, r.getReimbStatus());
			pstmt.setInt(4, r.getId());

			updatedRecords = pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException | IOException e) {
			throw new BusinessException("Contact System Admin. " + e.getMessage());
		}
		return updatedRecords;
	}

	@Override
	public int getReimbursement(int reimbId) throws BusinessException {
		throw new BusinessException("Not yet implemented");
	}
}
