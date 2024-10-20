package org.ppopovici.kitchensink.services;

import java.util.List;

import org.ppopovici.kitchensink.ApplicationException;
import org.ppopovici.kitchensink.entities.mappers.MemberMapper;
import org.ppopovici.kitchensink.models.Member;
import org.ppopovici.kitchensink.repositories.MemberRepository;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RequestScoped
public class MemberService {

	@Inject
	MemberRepository memberRepository;

	@Transactional
	public void register(@Valid Member member) {

		Member existingMember = MemberMapper.fromEntity(memberRepository.findByEmail(member.getEmail()));
		if (existingMember != null) {
			throw new ApplicationException("Emails exists", ApplicationException.ERROR_EMAIL_EXISTS);
		}

		try {
			memberRepository.persistAndFlush(MemberMapper.toEntity(member));
		}

		catch (Exception ex) {
			throw new ApplicationException("Internal error", ApplicationException.ERROR_INTERNAL, ex);
		}
	}

	public Member findMemberById(long id) {
		Member member = MemberMapper.fromEntity(memberRepository.findById(id));
		return member;
	}

	public List<Member> listAllMembers() {
		return memberRepository.listAll(Sort.by("id")).stream().map(MemberMapper::fromEntity).toList();
	}

}
