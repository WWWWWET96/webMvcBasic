package HELLOz.hello.springz.repository;

import HELLOz.hello.springz.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    /* Optional이란? findByID해서 그 값이 null일 수 있으니까
    그냥 null 그 자체를 가져오는게 아닌 Optional로 감싸서 반환*/

    List<Member> findAll();
}
