package HELLOz.hello.springz.repository;

import HELLOz.hello.springz.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
//테스트를 먼저 만들고 구현하는 것 : 테스트 주도 개발(TDD)
class MemoryMemberRepositoryTest {
     MemoryMemberRepository repository = new MemoryMemberRepository();

     /*clear안하면? 객체에 이미 값 들어있어 test할 때 값 충돌남*/
     @AfterEach //메소드 실행 끝날 때마다 실행되는, 콜백함수
     public void afterEach(){
         repository.clearStore();
     }

     @Test
    public void save(){
         Member member = new Member();
         member.setName("scone lee");

         repository.save(member);

         Member result = repository.findById(member.getId()).get();
         //member와 result의 값 비교하기 위한 2가지
         //1번
         Assertions.assertEquals(member, result);
         // (expect, actual)의 순서임. 둘이 같을 경우 잘 작동, 둘이 다를 경우 테스트 에러

         //2번
         org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);
     }
     @Test
    public void findByName(){
         Member member1 = new Member();
         member1.setName("baguette lee");
         repository.save(member1);

         Member member2 = new Member();
         member2.setName("croissant lee");
         repository.save(member2);

         Member result = repository.findByName("baguette lee").get();

         org.assertj.core.api.Assertions.assertThat(result).isEqualTo(member1);
     }

     @Test
    public void findAll(){
         Member member1 = new Member();
         member1.setName("baguette lee");
         repository.save(member1);

         Member member2 = new Member();
         member2.setName("croissant lee");
         repository.save(member2);

         List<Member> result = repository.findAll();

         org.assertj.core.api.Assertions.assertThat(result.size()).isEqualTo(2);
     }

}
