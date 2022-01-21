package HELLOz.hello.springz.repository;

import HELLOz.hello.springz.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>(); // 값 저장하기 위한 map
    private static long sequence = 0L;
    /*
    seqeunce객체: 순차적으로 정수값을 자동으로 생성하는 객체
    0은 int값 의미, 0L은 long형 0을 의미 */
    @Override
    public Member save(Member member) {
         member.setId(++sequence);
         store.put(member.getId(), member);
         return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));//반환이 null일경우 optional로 감싸서 반환.
        //optional일 경우 클라이언트 측에서 수습 가능능
        }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); //끝까지 찾았는데 없으면 optional로 감싸서 null 반환
    }

    @Override
    public List<Member> findAll() { //자바에서 실무할 때 list많이 씀
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
