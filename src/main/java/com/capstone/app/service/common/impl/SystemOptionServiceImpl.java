package com.capstone.app.service.common.impl;

import com.capstone.app.entity.SystemOption;
import com.capstone.app.entity.dto.common.SystemOptionResponseDTO;
import com.capstone.app.mapper.SystemOptionMapper;
import com.capstone.app.repository.SystemOptionRepo;
import com.capstone.app.service.common.SystemOptionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SystemOptionServiceImpl implements SystemOptionService {

	private final SystemOptionRepo systemOptionRepo;
	private final SystemOptionMapper systemOptionMapper;

	@Override
	public List<SystemOption> getAllSystemOption() {
		return systemOptionRepo.findAll();
	}

	@Override
	public void saveSystemOption(SystemOption systemOption) {
		this.systemOptionRepo.save(systemOption);
	}

	@Override
	public void updateSystemOption(String optionKey, String optionValue) {
		SystemOption systemOption = systemOptionRepo.findByOptionkey(optionKey);
		if (systemOption != null) {
			systemOption.setOptionValue(optionValue);
			systemOptionRepo.save(systemOption);
		} else {
			throw new RuntimeException("System option with key " + optionKey + " not found.");
		}
	}

	@Override
	public void updateMoneyRelatedOption(String optionKey, String valueToAdd) {
		if (!optionKey.trim().equals("system_balance") || !optionKey.trim().equals("system_deposit_out")) {
			throw new RuntimeException("Invalid system option");
		}
		Double value = null;
		Double originalValue = null;
		SystemOption systemOption = systemOptionRepo.findByOptionkey(optionKey);
		if (systemOption != null) {
			try {
				value = Double.parseDouble(valueToAdd);
				originalValue = Double.parseDouble(systemOption.getOptionValue());
			} catch (Exception e) {
				e.printStackTrace();
			}
			systemOption.setOptionValue(String.valueOf(Double.sum(value, originalValue)));
			systemOptionRepo.save(systemOption);
		} else {
			throw new RuntimeException("System Option not found");
		}
	}

	@Override
	public SystemOption findSystemOptionById(Integer id) {
		Optional<SystemOption> systemOption = this.systemOptionRepo.findById(id);
		if (systemOption.isPresent()) {
			return systemOption.get();
		}
		throw new RuntimeException("Can not find system option with id:: " + id);
	}

	@Override
	public void deleteSystemOptionById(Integer id) {
		this.systemOptionRepo.deleteById(id);
	}

	@Override
	public String findSystemOptionValueByKey(String optionKey) {
		SystemOption systemOption = systemOptionRepo.findByOptionkey(optionKey);
		if(systemOption != null){
			return systemOption.getOptionValue();
		}
		return "";
	}

	@Override
	public List<SystemOptionResponseDTO> getAllFrontSystemOption() {
		List<String> options = List.of(
				"system_balance",
				"system_deposit_out"
		);
		return systemOptionMapper.toListDTO(systemOptionRepo.findAllExceptForDashboardOption(options));
	}
}
