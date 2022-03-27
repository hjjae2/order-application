package com.toy.apps;

import com.toy.apps.member.entity.Member;
import com.toy.apps.member.service.ToyMemberDetailService;
import com.toy.apps.member.service.ToyMemberDetails;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;

@WebMvcTest
public class SecuritySetUp4ControllerTest {

    @MockBean
    protected ToyMemberDetailService toyMemberDetailService;

    protected final Member normalUser = Member.builder().email("user@toy.com").type(Member.Type.NORMAL).build();
    protected final Member sellerUser = Member.builder().email("seller@toy.com").type(Member.Type.SELLER).build();
    protected final Member adminUser = Member.builder().email("admin@toy.com").type(Member.Type.ADMIN).build();

    /**
     * [NOTE]<br>
     * 추가적인 권한 설정(ROLE 설정 등)이 필요할 경우 각 하위 클래스에서 재정의하거나 해당 클래스의 메서드를(setUpAdminRole, ...)를 호출합니다.<br>
     */
    protected void setUpNormalUserRole() {
        ToyMemberDetails toyMemberDetails = ToyMemberDetails.of(normalUser);

        Mockito.when(toyMemberDetailService.loadUserByUsername(any())).thenReturn(toyMemberDetails);
    }

    protected void setUpSellerRole() {
        ToyMemberDetails toyMemberDetails = ToyMemberDetails.of(sellerUser);

        Mockito.when(toyMemberDetailService.loadUserByUsername(any())).thenReturn(toyMemberDetails);
    }

    protected void setUpAdminRole() {
        ToyMemberDetails toyMemberDetails = ToyMemberDetails.of(adminUser);

        Mockito.when(toyMemberDetailService.loadUserByUsername(any())).thenReturn(toyMemberDetails);
    }
}
