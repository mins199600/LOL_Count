document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.getElementById('loginForm');

    loginForm.addEventListener('submit', function(e) {
        const username = document.getElementById('username');
        const password = document.getElementById('password');

        // 기본 유효성 검사
        if (!username.value.trim()) {
            alert('소환사명을 입력해주세요.');
            e.preventDefault();
            username.focus();
            return;
        }

        if (!password.value.trim()) {
            alert('비밀번호를 입력해주세요.');
            e.preventDefault();
            password.focus();
            return;
        }
         // 로그인 처리
        // 여기에 로그인 로직을 추가하세요.
        alert('로그인이 완료되었습니다.');
    });
});