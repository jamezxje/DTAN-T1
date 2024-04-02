package com.capstone.app.entity.dto.pagination;

import lombok.Getter;
import lombok.Setter;

@Getter
public class PaginationRequest {
    @Setter
    private int pageIndex = 0;
    private int pageSize = 10;

}
