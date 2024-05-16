package kh.Dionysus.Controller;
import kh.Dionysus.Dao.JjimDao;
import kh.Dionysus.Dao.MemberDelDao;
import kh.Dionysus.Dao.MemberUpdateDao;
import kh.Dionysus.Dao.ReviewDao;
import kh.Dionysus.Dto.JjimDto;
import kh.Dionysus.Dto.MemberDto;
import kh.Dionysus.Dto.ReviewDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/mypage")
public class MypageController {
    @PostMapping("/memberupdate")
    public ResponseEntity<Boolean> memberUpdate(@RequestBody MemberDto Dto) {
        MemberUpdateDao dao = new MemberUpdateDao();
        Boolean isTrue= dao.memberUpdate(Dto);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }
    @PostMapping("/memberdel")
    public ResponseEntity<Boolean> memberdel(@RequestBody MemberDto Dto) {
        MemberDelDao dao = new MemberDelDao();
        boolean isTrue = dao.memberDelete(Dto.getUser_id(), Dto.getUser_name(), Dto.getUser_jumin());
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }

    @PostMapping("/review")
    public ResponseEntity<ReviewDto> review(@RequestBody ReviewDto Dto) {
        ReviewDao dao = new ReviewDao();
        ReviewDto review = dao.reviewSelect(Dto.getUser_id());
        return new ResponseEntity<ReviewDto>(review, HttpStatus.OK);
    }
    @PostMapping("/jjim")
    public ResponseEntity<JjimDto> jjim(@RequestBody JjimDto Dto) {
        JjimDao dao = new JjimDao();
        JjimDto jjim = dao.jjimSelect(Dto.getUser_id());
        return new ResponseEntity<JjimDto>(jjim, HttpStatus.OK);
    }

}
