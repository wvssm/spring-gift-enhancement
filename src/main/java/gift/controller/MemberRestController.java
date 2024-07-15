package gift.controller;

import gift.dto.response.TokenResponseDto;
import gift.dto.request.MemberRequestDto;
import gift.service.JwtUtil;
import gift.service.MemberService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberRestController {
    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    public MemberRestController(MemberService memberService, JwtUtil jwtUtil) {
        this.memberService = memberService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerMember(@RequestBody MemberRequestDto request){
        memberService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> giveAccessToken(@RequestBody MemberRequestDto request) {
        memberService.checkMemberExistsByIdAndPassword(request.email(),request.password());
        String token = jwtUtil.generateToken(request.email());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(new TokenResponseDto(token));
    }
}
