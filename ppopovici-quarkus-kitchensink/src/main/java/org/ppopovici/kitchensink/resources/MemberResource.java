package org.ppopovici.kitchensink.resources;

import org.ppopovici.kitchensink.ApplicationException;
import org.ppopovici.kitchensink.models.Member;
import org.ppopovici.kitchensink.services.MemberService;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/members")
@RequestScoped
public class MemberResource {

	@Inject
	MemberService memberService;

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAllMembers() {
		Log.info("List all members request");
		return Response.ok(memberService.listAllMembers()).build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response lookupMemberById(@PathParam("id") long id) {
		Log.infov("Get member request with id {0}", id);

		Member member = memberService.findMemberById(id);

		if (member == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		Log.infov("Get member response: {0}", member);
		return Response.ok(member).build();
	}

	@POST
	@Path("register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createMember(Member request) {

		Log.infov("Create member request {0}", request);

		try {
			memberService.register(request);
		} catch (ApplicationException ex) {

			Log.error("Failed to create new member", ex);
			if (ex.getErrorCode() == ApplicationException.ERROR_EMAIL_EXISTS) {
				return Response.status(Status.CONFLICT).build();
			}

			return Response.status(Status.INTERNAL_SERVER_ERROR).build();

		} catch (Exception ex) {
			Log.error("Failed to create new member", ex);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

		Log.info("Created member");
		return Response.ok().build();
	}

}
