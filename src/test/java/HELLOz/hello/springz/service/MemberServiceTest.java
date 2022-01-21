package HELLOz.hello.springz.service;

import HELLOz.hello.springz.domain.Member;
import HELLOz.hello.springz.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//ctrl + shift + T누르면 테스트클래스 자동 생성됨
class MemberServiceTest {
    /*
    문제: MemberService와 MemberServiceTest에서 각각 memberRepository를 생성하고 있음
    즉, 서로 다른 repository! 다른 db라는 말! 다른 repository로 테스트중임
    -> 해결 : 의존성 주입
    */
      /*
    기존 각각 repository생성 했을 때의 코드
    MemberService memberService = new MemberService();
    MemoryMemberRepository memberRepository  = new MemoryMemberRepository();
*/
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){ // 테스트 돌때마다 메모리 clear
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() { //test는 빌드되지 않기에 편의를 위해 한글로 작성많이 함
        //테스트 익숙하지 않으면 given-when-then 패턴 이용 추천

        //given
        Member member = new Member();
        member.setName("scone lee");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("baguette lee");

        Member member2 = new Member();
        member2.setName("baguette lee");

        //when
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //1예외처리 : member2를 join하면 IllegalStateExeption이 터져야해


        /*2예외 처리
        try {
            memberService.join(member2); //예외가 되야함. 중복이니까
            fail();
        }catch (IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/
        }


}