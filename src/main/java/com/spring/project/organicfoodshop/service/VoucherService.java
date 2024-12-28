package com.spring.project.organicfoodshop.service;

import com.spring.project.organicfoodshop.domain.Voucher;
import com.spring.project.organicfoodshop.repository.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class VoucherService {

    private final VoucherRepository voucherRepository;

    public Voucher handleSaveVoucher(Voucher voucher) {
        return voucherRepository.save(voucher);
    }

    public Set<Voucher> getAllVouchers() {
        return Set.copyOf(voucherRepository.findAll());
    }

    public void handleDeleteVoucherById(Long id) {
        voucherRepository.deleteById(id);
    }

    public Voucher getVoucherById(Long id) {
        return voucherRepository.findById(id).orElse(null);
    }
}
