package com.lease.web.app.mapper.agreement;

import com.lease.model.entity.LeaseAgreement;
import com.lease.web.app.vo.agreement.AgreementDetailVo;
import com.lease.web.app.vo.agreement.AgreementItemVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LeaseAgreementMapper {

    @Mapping(source = "apartmentInfo.name", target = "apartmentName")
    @Mapping(source = "roomInfo.roomNumber", target = "roomNumber")
    @Mapping(source = "paymentType.name", target = "paymentTypeName")
    @Mapping(source = "leaseTerm.monthCount", target = "leaseTermMonthCount")
    @Mapping(source = "leaseTerm.unit", target = "leaseTermUnit")
    @Mapping(source = "apartmentInfo.id", target = "apartmentId")
    @Mapping(source = "roomInfo.id", target = "roomId")
    @Mapping(source = "paymentType.id", target = "paymentTypeId")
    @Mapping(source = "leaseTerm.id", target = "leaseTermId")
    AgreementDetailVo toDetailVo(LeaseAgreement leaseAgreement);

    @Mapping(source = "apartmentInfo.name", target = "apartmentName")
    @Mapping(source = "roomInfo.roomNumber", target = "roomNumber")
    @Mapping(source = "status", target = "leaseStatus")
    @Mapping(source = "roomInfo.rent", target = "rent")
    @Mapping(source = "roomInfo.id", target = "roomId")
    AgreementItemVo toItemVo(LeaseAgreement leaseAgreement);
    List<AgreementItemVo> toItemVoList(List<LeaseAgreement> leaseAgreementList);
}