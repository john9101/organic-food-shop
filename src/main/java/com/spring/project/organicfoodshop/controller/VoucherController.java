package com.spring.project.organicfoodshop.controller;

import com.spring.project.organicfoodshop.domain.Voucher;
import com.spring.project.organicfoodshop.domain.request.management.voucher.AddVoucherRequest;
import com.spring.project.organicfoodshop.domain.request.management.voucher.EditVoucherRequest;
import com.spring.project.organicfoodshop.domain.response.common.voucher.GotAllVouchersResponse;
import com.spring.project.organicfoodshop.domain.response.common.voucher.GotVoucherDetailResponse;
import com.spring.project.organicfoodshop.domain.response.management.voucher.*;
import com.spring.project.organicfoodshop.service.VoucherService;
import com.spring.project.organicfoodshop.service.mapper.VoucherMapper;
import com.spring.project.organicfoodshop.util.annotation.ApiRequestMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vouchers")
@RequiredArgsConstructor
public class VoucherController {
    private final VoucherService voucherService;

    @GetMapping
    @ApiRequestMessage("Call get all voucher API request")
    public ResponseEntity<GotAllVouchersResponse> getAllVouchers() {
        List<Voucher> vouchers = voucherService.getAllVouchers();
        List<GotAllVouchersResponse.Item> items = VoucherMapper.INSTANCE.toGotAllVoucherItemsResponse(vouchers);
        GotAllVouchersResponse response = GotAllVouchersResponse.builder().items(items).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{voucherId}")
    public ResponseEntity<GotVoucherDetailResponse> getVoucherDetail(@PathVariable Long voucherId) {
        Voucher voucher = voucherService.getVoucherById(voucherId);
        GotVoucherDetailResponse response = VoucherMapper.INSTANCE.toGotVoucherDetailResponse(voucher);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<AddedVoucherResponse> addVoucher(@RequestBody AddVoucherRequest request) {
        Voucher voucher = VoucherMapper.INSTANCE.toVoucher(request);
        voucher = voucherService.handleSaveVoucher(voucher);
        AddedVoucherResponse response = VoucherMapper.INSTANCE.toAddedVoucherResponse(voucher);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{voucherId}")
    public ResponseEntity<EditedVoucherResponse> editVoucher(@PathVariable Long voucherId, @RequestBody EditVoucherRequest request) {
        Voucher voucher = voucherService.getVoucherById(voucherId);
        voucher.setCode(request.getCode());
        voucher.setDescription(request.getDescription());
        voucher.setDiscountPercent(request.getDiscountPercent());
        voucher.setQuantity(request.getQuantity());
        voucher.setEffectiveDate(request.getEffectiveDate());
        voucher.setExpiryDate(request.getExpiryDate());
        voucher.setMinimumAmount(request.getMinimumAmount());
        voucher = voucherService.handleSaveVoucher(voucher);
        EditedVoucherResponse response = VoucherMapper.INSTANCE.toEditedVoucherResponse(voucher);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{voucherId}")
    public ResponseEntity<DeletedVoucherResponse> deleteVoucher(@PathVariable Long voucherId) {
        Voucher voucher = voucherService.getVoucherById(voucherId);
        voucher.setIsDeleted(true);
        voucher = voucherService.handleSaveVoucher(voucher);
        DeletedVoucherResponse response = VoucherMapper.INSTANCE.toDeletedVoucherResponse(voucher);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{voucherId}/recovery")
    public ResponseEntity<RecoveredVoucherResponse> recoverVoucher(@PathVariable Long voucherId) {
        Voucher voucher = voucherService.getVoucherById(voucherId);
        voucher.setIsDeleted(false);
        voucher = voucherService.handleSaveVoucher(voucher);
        RecoveredVoucherResponse response = VoucherMapper.INSTANCE.toRecoveredVoucherResponse(voucher);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{voucherId}/visibility/{isVisible}")
    public ResponseEntity<DisplayedVoucherResponse> displayVoucher(@PathVariable Long voucherId, @PathVariable Boolean isVisible) {
        Voucher voucher = voucherService.getVoucherById(voucherId);
        voucher.setIsVisible(isVisible);
        voucher = voucherService.handleSaveVoucher(voucher);
        DisplayedVoucherResponse response = VoucherMapper.INSTANCE.toDisplayedVoucherResponse(voucher);
        return ResponseEntity.ok(response);
    }
}
