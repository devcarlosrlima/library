package main.java.com.example.library.service;

import  main.java.com.example.library.dto.LoanDTO;
import  main.java.com.example.library.entity.Loan;
import  main.java.com.example.library.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public List<LoanDTO> getAllLoans() {
        return loanRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public LoanDTO addLoan(LoanDTO loanDTO) {
        Loan loan = convertToEntity(loanDTO);
        loan = loanRepository.save(loan);
        return convertToDTO(loan);
    }

    public LoanDTO updateLoan(Long id, LoanDTO loanDTO) {
        Loan loan = loanRepository.findById(id).orElseThrow(() -> new RuntimeException("Loan not found"));
        loan.setBookId(loanDTO.getBookId());
        loan.setMemberId(loanDTO.getMemberId());
        loan.setLoanDate(loanDTO.getLoanDate());
        loan.setReturnDate(loanDTO.getReturnDate());
        loan = loanRepository.save(loan);
        return convertToDTO(loan);
    }

    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }

    private LoanDTO convertToDTO(Loan loan) {
        return new LoanDTO(loan.getId(), loan.getBookId(), loan.getMemberId(), loan.getLoanDate(), loan.getReturnDate());
    }

    private Loan convertToEntity(LoanDTO loanDTO) {
        return new Loan(loanDTO.getId(), loanDTO.getBookId(), loanDTO.getMemberId(), loanDTO.getLoanDate(), loanDTO.getReturnDate());
    }
}
