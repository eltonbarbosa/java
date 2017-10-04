package br.com.inatel.jersey;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.inatel.security.Credentials;
import br.com.inatel.security.Token;

/*Autenticando user*/
@Path("/login")
public class LoginService {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response authenticateUser(Credentials credentials) {
		try {
			validateCrentials(credentials);
			return Response.ok(Token.TOKEN).build();
		} catch (Exception e) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}
	
	private void validateCrentials(Credentials credentials) throws Exception{
		if (!credentials.getPassword().equals("admin") || !credentials.getUsername().equals("admin")) {
			throw new Exception("Credenciais inválidas!");
		}
	}
}
