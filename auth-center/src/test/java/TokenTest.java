import com.venn.constants.StaticConstants;
import com.venn.service.TokenService;
import com.venn.service.impl.TokenServiceImpl;
import org.junit.Test;

public class TokenTest {

    @Test
    public void testGenerateToken() {
        TokenService tokenService = new TokenServiceImpl();
        String token1 = tokenService.generateToken();
        assert (null != token1 && !StaticConstants.EMPTY_STR.equals(token1.trim()));
    }

    @Test
    public void testDeleteToken() {
        TokenService tokenService = new TokenServiceImpl();
        String token2 = tokenService.generateToken();
        assert (!tokenService.checkToken(StaticConstants.EMPTY_STR, 2));
        assert (!tokenService.checkToken(StaticConstants.BLANK_STR, 2));
        assert (tokenService.checkToken(token2, 20));
        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
//            System.out.println();
        }
        assert (!tokenService.checkToken(token2, 1));


    }

    @Test
    public void testInvalidateToken() {

        TokenService tokenService = new TokenServiceImpl();
        String token2 = tokenService.generateToken();
        assert (tokenService.invalidateToken(token2));
        assert (!tokenService.invalidateToken(null));
        assert (!tokenService.invalidateToken(StaticConstants.EMPTY_STR));

    }
}
