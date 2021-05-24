package com.revature.project1.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.revature.project1.exception.BusinessException;
import com.revature.project1.model.Reimbursement;
import com.revature.project1.service.ReimbursementService;

class ReimbursementServiceImplTest {

	private static ReimbursementService rs;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		rs = new ReimbursementServiceImpl();
	}

	static Reimbursement[] reimbursementListInvalid() {
		Reimbursement r0 = new Reimbursement(new BigDecimal("12.1"), new Timestamp(1000), "desc", "lodging", "pending");
		Reimbursement r1 = new Reimbursement(new BigDecimal("12.111"), new Timestamp(1000), "desc", "lodging",
				"pending");
		Reimbursement r2 = new Reimbursement(new BigDecimal("12.11"), new Timestamp(1000), "", "lodging", "pending");
		Reimbursement r3 = new Reimbursement(new BigDecimal("12.11"), new Timestamp(1000), null, "lodging", "pending");
		Reimbursement r4 = new Reimbursement(new BigDecimal("12.11"), new Timestamp(1000), "desc", null, "pending");
		Reimbursement r5 = new Reimbursement(new BigDecimal("12.11"), new Timestamp(1000), "desc", "gasoline",
				"pending");
		Reimbursement r6 = new Reimbursement(new BigDecimal("12.11"), new Timestamp(1000), "   ", "lodging", "pending");
		Reimbursement r7 = new Reimbursement(new BigDecimal("12.11"), new Timestamp(1000), "desc", " ", "pending");
		Reimbursement r8 = new Reimbursement(new BigDecimal("12.11"), new Timestamp(1000), "desc", "", "pending");
		Reimbursement r9 = new Reimbursement(new BigDecimal("12"), new Timestamp(1000), "desc", "lodging", "pending");

		return new Reimbursement[] { r0, r1, r2, r3, r4, r5, r6, r7, r8, r9 };
	}

	@ParameterizedTest
	@MethodSource("reimbursementListInvalid")
	void testValidateReimbursementInvalid(Reimbursement r) {
		assertThrows(BusinessException.class, () -> rs.validateReimbursement(r));
	}

	static Reimbursement[] reimbursementListValid() {
		Reimbursement r0 = new Reimbursement(new BigDecimal("12.11"), new Timestamp(1000), "desc", "lodging",
				"pending");
		Reimbursement r1 = new Reimbursement(new BigDecimal("12.11"), new Timestamp(1000), "desc", "food", "pending");
		Reimbursement r2 = new Reimbursement(new BigDecimal("12.11"), new Timestamp(1000), "desc", "other", "pending");
		Reimbursement r3 = new Reimbursement(new BigDecimal("12.11"), new Timestamp(1000), "desc", "travel", "pending");
		Reimbursement r4 = new Reimbursement(new BigDecimal("11111111112.11"), new Timestamp(1000), "desc", "travel",
				"pending");
		Reimbursement r5 = new Reimbursement(new BigDecimal("1122.11"), new Timestamp(1000), "desc", "food", "pending");
		Reimbursement r6 = new Reimbursement(new BigDecimal("0.11"), new Timestamp(1000), "desc", "lodging", "pending");
		Reimbursement r7 = new Reimbursement(new BigDecimal("12.11"), new Timestamp(1000), "desc desc", "lodging",
				"pending");
		Reimbursement r8 = new Reimbursement(new BigDecimal("12.11"), new Timestamp(1000), " A very long description",
				"lodging", "pending");
		Reimbursement r9 = new Reimbursement(new BigDecimal("12.11"), new Timestamp(1000), "s", "lodging", "pending");

		return new Reimbursement[] { r0, r1, r2, r3, r4, r5, r6, r7, r8, r9 };
	}

	@ParameterizedTest
	@MethodSource("reimbursementListValid")
	void testValidateReimbursementValid(Reimbursement r) {
		try {
			assertTrue(rs.validateReimbursement(r));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}
