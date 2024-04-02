package com.capstone.app.entity.dto.filter;

import com.capstone.app.entity.dto.pagination.PaginationRequest;
import com.capstone.app.entity.type.Status;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarFilterRequest extends PaginationRequest {
    public Integer ownerId;
    public String carModel;
    public Integer brandId;
    public Status status;
}
