package com.capstone.app.service.dash.impl;

import com.capstone.app.config.auth.UserDetailsCustom;
import com.capstone.app.entity.Image;
import com.capstone.app.entity.Member;
import com.capstone.app.entity.dto.dashboard.request.MemberRequestDTO;
import com.capstone.app.entity.dto.dashboard.response.MemberResponseDTO;
import com.capstone.app.entity.type.MemberRole;
import com.capstone.app.mapper.MemberMapper;
import com.capstone.app.repository.AdminRepo;
import com.capstone.app.service.common.FilesStorageService;
import com.capstone.app.service.dash.IAdminService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService implements IAdminService {

	private final AdminRepo adminRepo;
	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;
	private final FilesStorageService filesStorageService;

	@Override
	public MemberResponseDTO getMemberByID(Integer id) {
		return memberMapper.entityToResponseDTO(adminRepo.findById(id).get());
	}

	@Override
	public Page<MemberResponseDTO> getAllMember(Integer index, Integer size) {
		List<MemberRole> roles = List.of(MemberRole.ROLE_OWNER, MemberRole.ROLE_CUSTOMER);
		Page<Member> members = adminRepo.getAll(roles, getPaging(index, size));
		Page<MemberResponseDTO> memberDTOs = memberMapper.toListResponseDTO(members);
		return memberDTOs;
	}

	@Override
	public Page<MemberResponseDTO> getAllMemberByRole(MemberRole role, Integer index, Integer size) {
		Page<Member> members = adminRepo.getAllByRole(role.toString(), this.getPaging(index, size));
		Page<MemberResponseDTO> memberDTOs = memberMapper.toListResponseDTO(members);
		return memberDTOs;
	}

	@Override
	public MemberRequestDTO addNewMember(MemberRequestDTO memberRequestDTO) {
		Member member = memberMapper.requestDTOToEntity(memberRequestDTO);
		Image image = filesStorageService.save(memberRequestDTO.getFeatureImage());
		member.setFeatureImage(image);
		member.setPassword(passwordEncoder.encode(memberRequestDTO.getPassword()));
		member.setCreatedDate(LocalDateTime.now());
		member.setLastModifiedDate(LocalDateTime.now());
		member.setWalletBalance(.0);
		member.setIsActive(true);
		adminRepo.save(member);
		return memberRequestDTO;
	}

	@Override
	public MemberRequestDTO updateMember(MemberRequestDTO memberRequestDTO) {
		Member member = adminRepo.findById(memberRequestDTO.getMemberId()).get();
		member.setNationalId(memberRequestDTO.getNationalId());
		member.setFullName(memberRequestDTO.getFullName());
		if(memberRequestDTO.getFeatureImage() != null){
			Image newImage = filesStorageService.save(memberRequestDTO.getFeatureImage());
			if(member.getFeatureImage() != null){
				filesStorageService.delete(member.getFeatureImage());
			}
			member.setFeatureImage(newImage);
		}
		member.setDateOfBirth(memberRequestDTO.getDateOfBirth());
		member.setEmail(memberRequestDTO.getEmail());
		member.setPhoneNumber(memberRequestDTO.getPhoneNumber());
		member.setAddress(memberRequestDTO.getAddress());
		member.setUserName(memberRequestDTO.getUserName());
		member.setLastModifiedDate(LocalDateTime.now());
		adminRepo.save(member);
		return memberRequestDTO;
	}

	@Override
	public MemberResponseDTO changeMemberActiveStatus(Integer id, Boolean status) {
		Member member = adminRepo.findById(id).get();
		if (status) {
			member.setIsActive(true);
		} else {
			member.setIsActive(false);
		}
		adminRepo.save(member);
		return memberMapper.entityToResponseDTO(member);
	}

	@Override
	public MemberResponseDTO getMemberFromContext() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsCustom userDetail = null;
		if (authentication != null) {
			userDetail = (UserDetailsCustom) authentication.getPrincipal();
		}
		Member member = userDetail.getMember();
		return memberMapper.entityToResponseDTO(member);

	}

	@Override
	public MemberResponseDTO deleteMember(Integer id) {
		Member member = adminRepo.findById(id).get();
		adminRepo.deleteById(id);
		return memberMapper.entityToResponseDTO(member);
	}

	public Pageable getPaging(Integer pageNum, Integer pageSize){
		return PageRequest.of(pageNum, pageSize);
	}

}
