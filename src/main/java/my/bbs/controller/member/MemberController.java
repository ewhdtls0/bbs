package my.bbs.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.bbs.dto.member.MemberJoinRequest;
import my.bbs.dto.member.MemberResponse;
import my.bbs.dto.member.MemberUpdateRequest;
import my.bbs.service.member.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    /**
     * 멤버 전체 조회 핸들러
     * @return
     */
    @GetMapping("")
    public ResponseEntity<List<MemberResponse>> findAllMembers() {
        List<MemberResponse> allMembers = memberService.findAllMembers();

        return ResponseEntity.ok(allMembers);
    }

    /**
     * 멤버 조회 핸들러
     * @param memberId
     * @return
     */
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponse> findMember(@PathVariable Long memberId) {
        MemberResponse member = memberService.findMember(memberId);

        return ResponseEntity.ok(member);
    }

    /**
     * 멤버 등록 핸들러
     * @param request
     * @return
     */
    @PostMapping("")
    public ResponseEntity<Void> joinMember(@RequestBody MemberJoinRequest request) {
        memberService.joinMember(request);

        return ResponseEntity.created(null).build();
    }

    /**
     * 멤버 수정 핸들러
     * @param memberId
     * @param request
     * @return
     */
    @PutMapping("/{memberId}")
    public ResponseEntity<Void> updateMember(
            @PathVariable Long memberId,
            @RequestBody MemberUpdateRequest request
    ) {
        memberService.updateMember(memberId, request);

        return ResponseEntity.noContent().build();
    }

    /**
     * 멤버 삭제 핸들러
     * @param memberId
     * @return
     */
    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);

        return ResponseEntity.noContent().build();
    }
}
