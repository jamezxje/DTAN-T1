package com.capstone.app.service.dash.impl;

import com.capstone.app.config.auth.UserDetailsCustom;
import com.capstone.app.entity.dto.dashboard.request.MemberRequestDTO;
import com.capstone.app.exception.NotFoundException;
import com.capstone.app.mapper.MemberMapper;
import com.capstone.app.repository.MemberRepo;
import com.capstone.app.service.dash.IMemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements IMemberService {

	private final MemberRepo memberRepo;
	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;

	@Override
	public MemberRequestDTO register(MemberRequestDTO memberRequestDTO) {
		memberRequestDTO.setCreatedDate(LocalDateTime.now());
		memberRequestDTO.setLastModifiedDate(LocalDateTime.now());
		memberRequestDTO.setWalletBalance(.0);
		memberRequestDTO.setIsActive(true);
		memberRequestDTO.setPassword(passwordEncoder.encode(memberRequestDTO.getPassword()));
		memberRepo.save(memberMapper.requestDTOToEntity(memberRequestDTO));
		return memberRequestDTO;
	}

	@Override
	public Double getCurrentWalletBalance() {
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) return 0.0;
		UserDetailsCustom userDetails = (UserDetailsCustom) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return memberRepo.findById(userDetails.getMember().getMemberId()).orElseThrow(NotFoundException::new).getWalletBalance();
	}
}
