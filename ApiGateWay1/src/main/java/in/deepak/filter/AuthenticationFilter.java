package in.deepak.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import com.netflix.discovery.converters.Auto;

import in.deepak.exception.TokenException;
import in.deepak.utils.JwtUtils;
import in.deepak.utils.RouteRoleMapper;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;

//import com.netflix.spectator.impl.Config;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private RouteRoleMapper routeRoleMapper;

	@Autowired
	private RouteValidator routeValidator;

	public AuthenticationFilter() {
		super(Config.class);
	}

	public static class Config {
	}

	 @Override
	    public GatewayFilter apply(Config config) {
	        return (((exchange, chain) -> {
	        	
	        	System.out.println("------------API Gateway Filter Start-----------");
	        	
	            ServerHttpRequest request = null;
	            String requestPath = exchange.getRequest().getURI().getPath();
	            System.out.println("------------Request Path ------------"+requestPath);
	            String requiredRole = routeRoleMapper.getRequiredRoleForPath(requestPath);
	            
	            System.out.println("------------Request--"+" "+requiredRole);
	            if (routeValidator.isSecured.test(exchange.getRequest())) {
	            	System.out.println("-------------In If Statement---The Path is Secured");
	                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {

	                	System.out.println("-------------In second If Statement---The Path is Secured");
	                	System.out.println("Missing authorization header");
	                	throw new TokenException("Missing authorization header");
	                }
	                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
	                
	                System.out.println("----------AuthHeader----"+authHeader);
	                if (authHeader != null && authHeader.startsWith("Bearer ")) {
	                    authHeader = authHeader.substring(7);
	                }
	                try {
	                    Claims claims = jwtUtils.getAllClaimsFromToken(authHeader);
	                    String userRole = claims.get("role", String.class);
	                    Integer user_id = claims.get("userId", Integer.class);
	                    
	                    if (userRole == null || !userRole.equals(requiredRole)) {
	                    	
	                        throw new TokenException("Unauthorized access ");
	                    }
	                    if (jwtUtils.isTokenExpired(authHeader)) {
	                    	
	                        throw new TokenException("Token expired");
	                    }
	                    jwtUtils.validateToken(authHeader);
	                    
	                    exchange.getRequest().mutate().header("user_id",String.valueOf(user_id));
	                    System.out.println("Token check Complete from api Gateway now forwad ");
	                } catch (TokenException e) {
	                	
	                    throw new TokenException("Unauthorized access ");
	                }
	                catch (Exception e) {
	                }
	            }
	            System.out.print("---------------Api Gateway End --------------------");
	            return chain.filter(exchange.mutate().request(request).build());
	        }));
	    }
}
