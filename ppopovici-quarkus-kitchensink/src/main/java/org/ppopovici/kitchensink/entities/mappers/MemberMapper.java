package org.ppopovici.kitchensink.entities.mappers;

import org.ppopovici.kitchensink.entities.MemberEntity;
import org.ppopovici.kitchensink.models.Member;

public final class MemberMapper
{
    private MemberMapper( )
    {
    }

    public static Member fromEntity( MemberEntity entity )
    {
        if( entity == null )
        {
            return null;
        }

        Member model = new Member( );

        model.setId( entity.getId( ) );
        model.setName( entity.getName( ) );
        model.setEmail( entity.getEmail( ) );
        model.setPhoneNumber( entity.getPhoneNumber());

        return model;
    }

	public static MemberEntity toEntity(Member model) {
		if (model == null) {
			return null;
		}

		MemberEntity entity = new MemberEntity();

		entity.setId(model.getId());
		entity.setName(model.getName());
		entity.setEmail(model.getEmail());
		entity.setPhoneNumber(model.getPhoneNumber());

		return entity;
	}
}
