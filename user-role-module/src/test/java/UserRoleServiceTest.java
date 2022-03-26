import com.venn.constants.StaticConstants;
import com.venn.po.Role;
import com.venn.po.User;
import com.venn.service.RoleService;
import com.venn.service.TokenService;
import com.venn.service.UserRoleService;
import com.venn.service.UserService;
import com.venn.service.impl.RoleServiceImpl;
import com.venn.service.impl.TokenServiceImpl;
import com.venn.service.impl.UserRoleServiceImpl;
import com.venn.service.impl.UserServiceImpl;
import org.junit.Test;

import java.util.List;

public class UserRoleServiceTest /*extends UserRoleServiceImpl*/ {

    UserService userService = new UserServiceImpl();
    RoleService roleService = new RoleServiceImpl();
    TokenService tokenService = new TokenServiceImpl();
    UserRoleService userRoleService = new UserRoleServiceImpl();


    @Test
    public void testAddRole() {

        userService.createUser("user1", "pwd1");
        userService.createUser("user2", "pwd2");
        roleService.createRole("role1");
        roleService.createRole("role2");

        assert (userRoleService.addRole(new User("user1", "pwd1"), new Role("role1")));
        assert (userRoleService.addRole(new User("user1", "pwd1"), new Role("role2")));
        assert (userRoleService.addRole(new User("user2", "pwd2"), new Role("role1")));
        assert (!userRoleService.addRole(new User("use2", "pwd2"), new Role("role1")));
        assert (!userRoleService.addRole(new User("user1", "pwd1"), new Role("role3")));
        assert (!userRoleService.addRole(null, new Role("role3")));
        assert (!userRoleService.addRole(new User("user1", "pwd1"), null));


    }

    @Test
    public void testCheckUserRole() {
        userService.createUser("user1", "pwd1");
        userService.createUser("user2", "pwd2");
        roleService.createRole("role1");
        roleService.createRole("role2");
        userRoleService.addRole(new User("user1", "pwd1"), new Role("role1"));
        userRoleService.addRole(new User("user1", "pwd1"), new Role("role2"));
        userRoleService.addRole(new User("user2", "pwd2"), new Role("role1"));
        String token1 = tokenService.generateToken();
        assert (userRoleService.checkUserRole(new User("user1", "pwd1"), new Role("role1"), token1));
        assert (!userRoleService.checkUserRole(null, new Role("role1"), token1));
        assert (!userRoleService.checkUserRole(new User("user1", "pwd1"), null, token1));
        assert (!userRoleService.checkUserRole(new User("user1", "pwd1"), new Role("role1"), null));
        assert (userRoleService.checkUserRole(new User("user2", "pwd2"), new Role("role1"), token1));
        assert (!userRoleService.checkUserRole(new User("user2", "pwd2"), new Role("role2"), token1));
        try {
            Thread.sleep(4 * 1000);
        } catch (InterruptedException e) {
        }
        assert (!userRoleService.checkUserRole(new User("user2", "pwd2"), new Role("role1"), token1));

    }

    @Test
    public void testAuthenticateUser() {
        userService.createUser("user1", "pwd1");

        String token1 = userRoleService.authenticateUser("user1", "pwd1");
        String token2 = userRoleService.authenticateUser(StaticConstants.BLANK_STR, "pwd1");
        String token3 = userRoleService.authenticateUser("user1", StaticConstants.BLANK_STR);
        String token4 = userRoleService.authenticateUser(null, "pwd1");
        String token5 = userRoleService.authenticateUser("user1", null);
        assert (null != token1);
        assert (null == token2 && null == token3 && null == token4 && null == token5);

    }

    @Test
    public void testGetAllRolesByUser() {
        userService.createUser("user1", "pwd1");
        userService.createUser("user2", "pwd2");
        roleService.createRole("role1");
        roleService.createRole("role2");
        userRoleService.addRole(new User("user1", "pwd1"), new Role("role1"));
        userRoleService.addRole(new User("user1", "pwd1"), new Role("role2"));
        userRoleService.addRole(new User("user2", "pwd2"), new Role("role1"));
        String token = tokenService.generateToken();
        List<Role> roles = userRoleService.getAllRolesByUser(new User("user1", "pwd1"), token);
        List<Role> roles2 = userRoleService.getAllRolesByUser(new User("user2", "pwd2"), token);
        List<Role> roles3 = userRoleService.getAllRolesByUser(new User(StaticConstants.BLANK_STR, "pwd1"), token);
        List<Role> roles4 = userRoleService.getAllRolesByUser(new User("user2", StaticConstants.BLANK_STR), token);
        List<Role> roles5 = userRoleService.getAllRolesByUser(new User("user2", "pwd2"), StaticConstants.BLANK_STR);
        assert (roles.size() == 2 && roles2.size() == 1);
        assert (null == roles3 && null == roles4 && null == roles5);
        try {
            Thread.sleep(4 * 1000);
        } catch (InterruptedException e) {
        }
        List<Role> roles6 = userRoleService.getAllRolesByUser(new User("user1", "pwd1"), token);
        assert (null == roles6);
    }

}
