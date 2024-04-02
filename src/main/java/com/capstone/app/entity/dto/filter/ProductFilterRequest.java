package com.capstone.app.entity.dto.filter;

import com.capstone.app.entity.dto.pagination.PaginationRequest;
import com.capstone.app.entity.type.Status;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductFilterRequest extends PaginationRequest {
    public String productName;
    public Integer categoryId;
    public Integer colorId;
    public Integer sizeId;
    public Status status;
}
