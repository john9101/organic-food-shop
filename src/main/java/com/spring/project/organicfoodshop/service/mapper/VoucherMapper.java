package com.spring.project.organicfoodshop.service.mapper;

import com.spring.project.organicfoodshop.domain.Voucher;
import com.spring.project.organicfoodshop.domain.request.management.voucher.CreateVoucherRequest;
import com.spring.project.organicfoodshop.domain.response.management.voucher.CreatedVoucherResponse;
import com.spring.project.organicfoodshop.domain.response.management.voucher.EditedVoucherResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VoucherMapper {

    VoucherMapper INSTANCE = Mappers.getMapper(VoucherMapper.class);

    Voucher toVoucher(CreateVoucherRequest createVoucherRequest);

    CreatedVoucherResponse toCreatedVoucherResponse(Voucher voucher);

    EditedVoucherResponse toEditedVoucherResponse(Voucher voucher);
}
