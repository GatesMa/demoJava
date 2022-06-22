package token;

public class TokenServiceSpringServiceTest {
  
    private static final TokenService tokenService = new TokenService();

    public static void main(String[] args) throws Exception {
        // om crm
        System.out.println("om_crm prod: "+tokenService.encode("om_crm","om_crm","58006f979159di0FHAtN!5qJ1h4DRM#-"));
        // crm
        System.out.println("crm prod: "+tokenService.encode("crm","crm","54bb173c23469s,B9Snwt*J~PMV2)U~h"));
        System.out.println("crm dns: "+tokenService.encode("crm","crm","54bb173c2164bRNCd.~wWhzTL~~F}NHa"));
        System.out.println("crm dev: "+tokenService.encode("crm","crm","54bb173c20e76,NfsiXRM{dveEnj}W6!"));
        // audit
        System.out.println("audit prod: "+tokenService.encode("audit","audit","54e2321677a48J,FWh3H,hZtlG7SP$6K"));
        System.out.println("audit dns: "+tokenService.encode("audit","audit","54e2321675ce0s$p$IUWppukg85QNbgp"));
        // customer_service
        System.out.println("cs prod: "+tokenService.encode("customer_service","customer_service","8d7c6e3e6e2az#SKIbpnEHYPgiFd2b$2"));
        System.out.println("cs dns: "+tokenService.encode("customer_service","customer_service","8d7c6e3e5c05*)Y{r(6{QQt#$aYKMz9{"));
        System.out.println("cs dev: "+tokenService.encode("customer_service","customer_service","8d7c6e3e574eS%}}6i1Nttx0LGX5GodQ"));
        System.out.println("cs sandbox: " + tokenService.encode("customer_service", "customer_service", "557cc9de2734apEX#FZKJm|dKp|Vv+72"));
        System.out.println("cs sandbox: " + tokenService.encode("business_manager", "business_manager", "557cc9de2734apEX#FZKJm|dKp|Vv+72"));

        //sso
        System.out.println("sso prod: " + tokenService.encode("sso","sso","57259d33ca6bfR5zF{)dB05V}&NRiT#&"));
        //new_agency
        System.out.println("new_agency prod: " + tokenService.encode("'new_agency'","'new_agency'","55890a294232b)|yJQWj,L|me7+-xv54"));
        //report-service
        System.out.println("report-serVice prod: " + tokenService.encode("crm", "crm", "54bb173c23469s,B9Snwt*J~PMV2)U~h"));

        System.out.println("orbac prod: " + tokenService.encode(null, "2348", "dsX82F<k}\\v&rBoP"));

        // bm
        System.out.println("bm prod: " +
                tokenService.encode(null, "agency-center", "588344e46288fuor,{VO$x7Hzyp|u.T%"));

        // agent-center

        System.out.println("agent-center prod: " +
                tokenService.encode(null, "agency_web", "1)#1AYu@a16qeoW@2Jg!yBCjW52$CojY"));

    }
}
