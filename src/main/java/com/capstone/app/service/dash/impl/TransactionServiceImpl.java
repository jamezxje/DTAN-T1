package com.capstone.app.service.dash.impl;

import com.capstone.app.entity.Member;
import com.capstone.app.entity.SystemOption;
import com.capstone.app.entity.Transaction;
import com.capstone.app.entity.dto.pagination.PaginationResponse;
import com.capstone.app.entity.dto.filter.TransactionFilterRequest;
import com.capstone.app.entity.type.TransactionStatus;
import com.capstone.app.entity.type.TransactionType;
import com.capstone.app.repository.SystemOptionRepo;
import com.capstone.app.repository.TransactionRepo;
import com.capstone.app.service.dash.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepo transactionRepo;
    private final SystemOptionRepo systemOptionRepo;

    @Override
    public PaginationResponse<List<Transaction>> findByFilter(TransactionFilterRequest request) {
        Pageable pageable = PageRequest.of(request.getPageIndex(), request.getPageSize(), Sort.by("requestTime").descending());
        Page<Transaction> page = transactionRepo.findByFilter(request, pageable);
        return new PaginationResponse<>(page.getContent(), request.getPageIndex() + 1,
                page.getTotalPages()+ 1,page.getTotalElements(),request.getPageSize());
    }

    @Override
    public String changeDepositStatus(Integer id, TransactionStatus status) {
        Transaction transaction = transactionRepo.findById(id).orElseThrow();
        if (status.equals(TransactionStatus.APPROVED)){
            double amount = transaction.getAmount();
            if (transaction.getType().equals(TransactionType.REQUEST_OUT)){
                if (transaction.getMember().getWalletBalance() < amount){
                    return "Member Not enough money";
                }
                amount = -amount;
            }
            transaction.getMember().setWalletBalance(transaction.getMember().getWalletBalance() + amount);
            SystemOption systemOption = systemOptionRepo.findByOptionkey("system_deposit");
            systemOption.setOptionValue((Double.parseDouble(systemOption.getOptionValue()) + amount) + "");
            systemOptionRepo.save(systemOption);
        }
        transaction.setStatus(status);
        transactionRepo.save(transaction);
        return null;
    }

    @Override
    public boolean createRequestTransaction(Member member, double amount, TransactionType type) {
        if(type.equals(TransactionType.REQUEST_OUT)){
            if (member.getWalletBalance() < amount){
               return false;
            }
        }
        Transaction transaction = new Transaction();
        transaction.setMember(member);
        transaction.setAmount(amount);
        transaction.setType(type);
        transaction.setStatus(TransactionStatus.PENDING);
        transaction.setNote(type.getMess());
        transaction.setRequestTime(LocalDateTime.now());
        transactionRepo.save(transaction);
        return true;
    }
}
