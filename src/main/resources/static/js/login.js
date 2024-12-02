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

        // 추가적인 클라이언트 사이드 유효성 검사 가능
        // 예: 비밀번호 길이, 특수문자 포함 등
    });
});