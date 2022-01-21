package HELLOz.hello.springz.service;

import HELLOz.hello.springz.domain.Member;
import HELLOz.hello.springz.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService { //service는 비지니스를 처리하는 역할(비지니스 의존적이게 설계)

    /*기존 코드
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    이렇게 하면 serviceTest에서의 repository와 다른 각각의 repositry 생기니까
     */
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public Long join(Member member){
    //회원가입하면 중복 회원 검증 후 통과하면 save
        validateDuplicateMember(member);//중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
        .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }
    public List<Member> findMembers(){ // 전체 회원 조회
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
