package com.capstone.app.service.dash;

import com.capstone.app.entity.Member;
import com.capstone.app.entity.Transaction;
import com.capstone.app.entity.dto.pagination.PaginationResponse;
import com.capstone.app.entity.dto.filter.TransactionFilterRequest;
import com.capstone.app.entity.type.TransactionStatus;
import com.capstone.app.entity.type.TransactionType;

import java.util.List;

public interface TransactionService {
    PaginationResponse<List<Transaction>> findByFilter(TransactionFilterRequest request);
    String changeDepositStatus(Integer id, TransactionStatus status);
    boolean createRequestTransaction(Member member, double amount, TransactionType type);
}
