package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

// 컨+r 이전실행 그대로
class MemberServiceTest {


    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach  //각 메소드 끝날때마다 실행(각 테스트 저장소 클리어)
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();   //회원 객체 하나 만들어서
        member.setName("hello");        //이름 넣어주고

        //when
        Long saveId = memberService.join(member);   // 회원가입한 회원의 id를 saveId에 저장

        //then
        Member findMember = memberService.findOne(saveId).get();    //그 saveId로 역으로 회원을 찾아서
        org.assertj.core.api.Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());   //이름이 같은지 확인
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));   //오른쪽 로직을 실행하면 Ill~ 예외가 터지냐
/*

        try {
            memberService.join(member2);
            fail("예외가 발생해야 합니다.");
        } catch (IllegalStateException e){
            Assertions.assertEquals(e.getMessage(),("이미 존재하는 회원입니다.123"));
        }
*/

        //then


    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
// 커+쉬+t 테스트 껍데기 자동 생성
// 테스트는 한글로 바꿔도 상관없다(그저 테스트니까 - 직관적)