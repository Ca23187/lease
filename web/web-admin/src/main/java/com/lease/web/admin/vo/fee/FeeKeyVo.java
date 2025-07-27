package com.lease.web.admin.vo.fee;

import com.lease.model.entity.FeeKey;
import com.lease.model.entity.FeeValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class FeeKeyVo extends FeeKey {

    @Schema(description = "杂费value列表")
    private List<FeeValue> feeValueList;

    public FeeKeyVo(Long id, String name, List<FeeValue> feeValueList) {
        super.setId(id);
        super.setName(name);
        this.setFeeValueList(feeValueList);
    }
}
