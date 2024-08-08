package main.java.com.example.library.service;

import main.java.com.example.library.dto.MemberDTO;
import  main.java.com.example.library.entity.Member;
import  main.java.com.example.library.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<MemberDTO> getAllMembers() {
        return memberRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public MemberDTO addMember(MemberDTO memberDTO) {
        Member member = convertToEntity(memberDTO);
        member = memberRepository.save(member);
        return convertToDTO(member);
    }

    public MemberDTO updateMember(Long id, MemberDTO memberDTO) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("Member not found"));
        member.setName(memberDTO.getName());
        member.setEmail(memberDTO.getEmail());
        member = memberRepository.save(member);
        return convertToDTO(member);
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    private MemberDTO convertToDTO(Member member) {
        return new MemberDTO(member.getId(), member.getName(), member.getEmail());
    }

    private Member convertToEntity(MemberDTO memberDTO) {
        return new Member(memberDTO.getId(), memberDTO.getName(), memberDTO.getEmail());
    }
}
