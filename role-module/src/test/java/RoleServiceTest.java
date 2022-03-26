import com.venn.constants.StaticConstants;
import com.venn.po.Role;
import com.venn.service.RoleService;
import com.venn.service.impl.RoleServiceImpl;
import org.junit.Test;

public class RoleServiceTest {

    @Test
    public void testCreateRole() {

        RoleService roleService = new RoleServiceImpl();
        Role role1 = new Role("role1");
        roleService.createRole("role1");
        assert (!roleService.deleteRole(null));
        assert (roleService.deleteRole(role1));
        assert (!roleService.deleteRole(role1));

    }


    @Test
    public void testDeleteRole() {
        RoleService roleService = new RoleServiceImpl();
        assert (roleService.createRole("role3"));
        assert (!roleService.createRole("role3"));
        assert (!roleService.createRole(StaticConstants.BLANK_STR));

    }

}
