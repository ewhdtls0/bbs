package my.bbs.service.member;

import lombok.RequiredArgsConstructor;
import my.bbs.domain.member.Member;
import my.bbs.dto.member.MemberJoinRequest;
import my.bbs.dto.member.MemberResponse;
import my.bbs.dto.member.MemberUpdateRequest;
import my.bbs.repository.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Transactional // 예외 발생 시 롤백
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 멤버 조회
     * @param memberId
     * @return
     */
    public MemberResponse findMember(Long memberId){
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("멤버 정보가 없습니다."));

        MemberResponse response = new MemberResponse();
        response.setMemberId(findMember.getId());
        response.setAccount(findMember.getAccount());
        response.setName(findMember.getName());
        response.setGender(findMember.getGender());

        return response;
    }

    /**
     * 멤버 전체 조회
     * @return
     */
    public List<MemberResponse> findAllMembers() {
        return memberRepository.findAll()
                .stream()
                .map(member -> {
                    MemberResponse response = new MemberResponse();
                    response.setMemberId(member.getId());
                    response.setAccount(member.getAccount());
                    response.setName(member.getName());
                    response.setGender(member.getGender());

                    return response;
                }).toList();
    }

    /**
     * 멤버 회원가입
     * @param request
     */
    public void joinMember(MemberJoinRequest request) {
        Member memberEntity = new Member();
        memberEntity.setAccount(request.getAccount());
        memberEntity.setPassword(request.getPassword());
        memberEntity.setName(request.getName());
        memberEntity.setGender(request.getGender());

        memberRepository.save(memberEntity);
    }

    /**
     * 멤버 수정(비밀번호 변경)
     * @param memberId
     * @param request
     */
    public void updateMember(Long memberId, MemberUpdateRequest request) {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("멤버 정보가 없습니다."));

        if(StringUtils.hasText(request.getPassword())) {
            findMember.setPassword(request.getPassword());
        }
    }

    /**
     * 멤버 삭제
     * @param memberId
     */
    public void deleteMember(Long memberId) {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("멤버 정보가 없습니다."));
        
        memberRepository.delete(findMember);
    }
}
