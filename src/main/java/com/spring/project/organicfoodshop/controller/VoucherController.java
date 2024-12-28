package com.spring.project.organicfoodshop.controller;

import com.spring.project.organicfoodshop.domain.Voucher;
import com.spring.project.organicfoodshop.domain.request.management.voucher.CreateVoucherRequest;
import com.spring.project.organicfoodshop.domain.request.management.voucher.EditVoucherRequest;
import com.spring.project.organicfoodshop.domain.response.management.voucher.CreatedVoucherResponse;
import com.spring.project.organicfoodshop.domain.response.management.voucher.DeletedVoucherReponse;
import com.spring.project.organicfoodshop.domain.response.management.voucher.EditedVoucherResponse;
import com.spring.project.organicfoodshop.service.VoucherService;
import com.spring.project.organicfoodshop.service.mapper.VoucherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vouchers")
@RequiredArgsConstructor
public class VoucherController {
    private final VoucherService voucherService;

    @GetMapping
    public void getAllVouchers() {

    }

    @PostMapping
    public ResponseEntity<CreatedVoucherResponse> createVoucher(@RequestBody CreateVoucherRequest request) {
        Voucher voucher = VoucherMapper.INSTANCE.toVoucher(request);
        voucher = voucherService.handleSaveVoucher(voucher);
        CreatedVoucherResponse response = VoucherMapper.INSTANCE.toCreatedVoucherResponse(voucher);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{voucherId}")
    public ResponseEntity<EditedVoucherResponse> editVoucher(@PathVariable Long voucherId, @RequestBody EditVoucherRequest request) {
        Voucher voucher = voucherService.getVoucherById(voucherId);
        voucher.setTitle(request.getTitle());
        voucher.setDescription(request.getDescription());
        voucher.setDiscountPercent(request.getDiscountPercent());
        voucher.setQuantity(request.getQuantity());
        voucher = voucherService.handleSaveVoucher(voucher);
        EditedVoucherResponse response = VoucherMapper.INSTANCE.toEditedVoucherResponse(voucher);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{voucherId}")
    public ResponseEntity<DeletedVoucherReponse> deleteVoucher(@PathVariable Long voucherId) {
        voucherService.handleDeleteVoucherById(voucherId);
        DeletedVoucherReponse reponse = DeletedVoucherReponse.builder().id(voucherId).build();
        return ResponseEntity.status(HttpStatus.OK).body(reponse);
    }
}
