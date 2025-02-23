package com.spring.project.organicfoodshop.service.mapper;

import com.spring.project.organicfoodshop.domain.Voucher;
import com.spring.project.organicfoodshop.domain.request.management.voucher.AddVoucherRequest;
import com.spring.project.organicfoodshop.domain.response.common.voucher.GotAllVouchersResponse;
import com.spring.project.organicfoodshop.domain.response.common.voucher.GotVoucherDetailResponse;
import com.spring.project.organicfoodshop.domain.response.management.voucher.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VoucherMapper {

    VoucherMapper INSTANCE = Mappers.getMapper(VoucherMapper.class);

    Voucher toVoucher(AddVoucherRequest createVoucherRequest);

    AddedVoucherResponse toAddedVoucherResponse(Voucher voucher);

    EditedVoucherResponse toEditedVoucherResponse(Voucher voucher);

    List<GotAllVouchersResponse.Item> toGotAllVoucherItemsResponse(List<Voucher> vouchers);

    GotVoucherDetailResponse toGotVoucherDetailResponse(Voucher voucher);

    DeletedVoucherResponse toDeletedVoucherResponse(Voucher voucher);

    RecoveredVoucherResponse toRecoveredVoucherResponse(Voucher voucher);

    DisplayedVoucherResponse toDisplayedVoucherResponse(Voucher voucher);
}
