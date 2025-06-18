$(function (){
    // 테스트
    $("input[name=name]").val('홍길동');
    $("input[name=email]").val('test6@naver.com');
    $("input[name=password]").val('test6666');
    $("input[name=confirm_password]").val('test6666');
    $("input[name=phone]").val('010-1111-1111');
    $("input[name=marketingAgree]").prop("checked", true);
    $("input[name=passportName]").val('KILDONGHONG');
    $("input[name=nationality]").val("KOREA");
});

function validateStep1() {
    const email = $("input[name=email]").val();
    const password = $("input[name=password]").val();
    const confirm = $("input[name=confirm_password]").val();
    const name = $("input[name=name]").val();
    const phone = $("input[name=phone]").val();
    const marketing = $("#marketing").is(":checked");

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const nameRegex = /^[가-힣a-zA-Z\s]+$/;
    const phoneRegex = /^[0-9\-]{9,13}$/;

    if (!emailRegex.test(email)) {
        alert("유효한 이메일을 입력하세요.");
        return;
    }
    if (password.length < 6) {
        alert("비밀번호는 6자 이상이어야 합니다.");
        return;
    }
    if (password !== confirm) {
        alert("비밀번호가 일치하지 않습니다.");
        return;
    }
    if (!nameRegex.test(name)) {
        alert("이름에는 특수문자를 사용할 수 없습니다.");
        return;
    }
    if (phone && !phoneRegex.test(phone)) {
        alert("유효한 전화번호를 입력하세요.");
        return;
    }
    if (!marketing) {
        alert("마케팅 정보 수신에 동의해주세요.");
        return;
    }

    goToStep2(); // 유효하면 다음 단계로
}

function validateStep2() {
    const birth = $("input[name=birthDate]").val();
    const passport = $("input[name=passportName]").val();
    const nationality = $("input[name=nationality]").val();

    const birthRegex = /^\d{4}-\d{2}-\d{2}$/;
    const passportRegex = /^[a-zA-Z\s]{2,}$/;
    const nationRegex = /^[가-힣a-zA-Z\s]{2,}$/;

    if (!birth || !birthRegex.test(birth)) {
        alert("생년월일을 yyyy-mm-dd 형식으로 입력해주세요.");
        return false;
    }

    if (passport && !passportRegex.test(passport)) {
        alert("여권 영문 이름은 영문자만 입력 가능합니다.");
        return false;
    }

    if (nationality && !nationRegex.test(nationality)) {
        alert("국적은 한글 또는 영문으로 입력해주세요.");
        return false;
    }

    return true; // 유효성 통과 → submit 진행
}

// 스텝 인디케이터
function goToStep2() {
    document.getElementById("step1").style.display = "none";
    document.getElementById("step2").style.display = "block";
    document.getElementById("stepDot1").classList.remove("active");
    document.getElementById("stepDot2").classList.add("active");
}

function goToStep1() {
    document.getElementById("step2").style.display = "none";
    document.getElementById("step1").style.display = "block";
    document.getElementById("stepDot2").classList.remove("active");
    document.getElementById("stepDot1").classList.add("active");
}