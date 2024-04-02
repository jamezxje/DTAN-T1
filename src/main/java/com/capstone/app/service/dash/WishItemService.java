package com.capstone.app.service.dash;

import com.capstone.app.entity.Car;
import com.capstone.app.entity.WishItem;
import com.capstone.app.entity.WishItemId;
import com.capstone.app.entity.dto.front.response.WishItemResponseDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WishItemService {
    List<WishItemResponseDTO> findAllWishItemResponseDTO();

    boolean saveWishItem(Car car);

    boolean deleteWishItemByMemberIdAndCarId(Integer carId);

    boolean existsByWishItemId(WishItemId wishItemId);

}
