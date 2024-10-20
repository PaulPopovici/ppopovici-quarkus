package org.ppopovici.kitchensink.repositories;

import jakarta.enterprise.context.ApplicationScoped;

import org.ppopovici.kitchensink.entities.MemberEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class MemberRepository implements PanacheRepository<MemberEntity> {

	public MemberEntity findByEmail(String email) {
		return find("email", email).firstResult();
	}
}
