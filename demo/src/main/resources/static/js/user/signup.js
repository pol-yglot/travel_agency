$(function (){
    // 테스트
    $("input[name=name]").val('홍길동');
    $("input[name=email]").val('teset@naver.com');
    $("input[name=password]").val('test1111');
    $("input[name=confirm_password]").val('test1111');
    $("input[name=phone]").val('010-1111-1111');
    $("input[name=marketingAgree]").val(true);
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
