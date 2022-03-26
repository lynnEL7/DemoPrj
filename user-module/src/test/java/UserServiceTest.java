import com.venn.constants.StaticConstants;
import com.venn.po.User;
import com.venn.service.UserService;
import com.venn.service.impl.UserServiceImpl;
import org.junit.Test;

public class UserServiceTest {

    @Test
    public void testDeleteUser() {
        UserService userService = new UserServiceImpl();
        userService.createUser("test1", "password1");
        userService.createUser("test2", "password2");
        User user1 = new User("test1", "password1");
        assert (userService.deleteUser(user1));
        User user2 = new User("test3", "password3");
        User user3 = new User(null, null);
        User user4 = new User(StaticConstants.BLANK_STR, null);
        assert (!userService.deleteUser(user2));
        assert (!userService.deleteUser(user3));
        assert (!userService.deleteUser(user4));

    }

    @Test
    public void testCreateUser() {
        UserService userService = new UserServiceImpl();
        assert (userService.createUser("test4", "password4"));
        assert (!userService.createUser("test4", "password4"));
        assert (!userService.createUser(StaticConstants.BLANK_STR, "password5"));
        assert (!userService.createUser("test4", ""));
        assert (!userService.createUser(null, "password5"));
        assert (!userService.createUser("test4", null));
    }

    @Test
    public void testIsExist() {
        UserService userService = new UserServiceImpl();
        assert (userService.createUser("test6", "password6"));
        assert (userService.isValidate("test6", "password6"));
        assert (!userService.isValidate("test6", "password7"));
        assert (!userService.isValidate(StaticConstants.BLANK_STR, "password8"));
        assert (!userService.isValidate(null, "password8"));
    }
}
