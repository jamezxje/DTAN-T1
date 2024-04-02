package com.capstone.app.service.common;

import com.capstone.app.entity.SystemOption;
import com.capstone.app.entity.dto.common.SystemOptionResponseDTO;

import java.util.List;

public interface SystemOptionService {

    List<SystemOption> getAllSystemOption();

    void saveSystemOption(SystemOption systemOption);

    void updateSystemOption(String optionKey, String optionValue);

    void updateMoneyRelatedOption(String optionKey, String valueToAdd);

    SystemOption findSystemOptionById(Integer id);

    void deleteSystemOptionById(Integer id);

    String findSystemOptionValueByKey(String optionKey);

    List<SystemOptionResponseDTO> getAllFrontSystemOption();

}
