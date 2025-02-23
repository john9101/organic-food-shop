package com.spring.project.organicfoodshop.service;

import com.spring.project.organicfoodshop.domain.Voucher;
import com.spring.project.organicfoodshop.repository.VoucherRepository;
import com.spring.project.organicfoodshop.util.FormatterUtil;
import com.spring.project.organicfoodshop.util.constant.ModuleEnum;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class VoucherService {

    private final VoucherRepository voucherRepository;

    public Voucher handleSaveVoucher(Voucher voucher) {
        return voucherRepository.save(voucher);
    }

    public List<Voucher> getAllVouchers() {
        return voucherRepository.findAll();
    }

    public void handleDeleteVoucherById(Long id) {
        voucherRepository.deleteById(id);
    }

    public Voucher getVoucherById(Long id) {
        return voucherRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(FormatterUtil.formateExistExceptionMessage("id", id, ModuleEnum.VOUCHER)));
    }
}
