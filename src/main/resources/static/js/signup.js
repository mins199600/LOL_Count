document.addEventListener('DOMContentLoaded', function() {
    const signupForm = document.getElementById('signupForm');

    signupForm.addEventListener('submit', function(e) {
        e.preventDefault();

        const memberId = document.getElementById('member_id');
        const memberPwd = document.getElementById('member_pwd');
        const confirmPwd = document.getElementById('confirm_pwd');

        // 클라이언트 측 유효성 검사
        if (!memberId.value.trim()) {
            alert('소환사명을 입력해주세요.');
            memberId.focus();
            return;
        }

        if (!/^[A-Za-z0-9]{1,24}$/.test(memberId.value)) {
            alert('소환사명은 영문과 숫자 조합으로 24자 이내로 입력해주세요.');
            memberId.focus();
            return;
        }

        if (memberPwd.value.length < 8) {
            alert('비밀번호는 8자 이상이어야 합니다.');
            memberPwd.focus();
            return;
        }

        if (memberPwd.value !== confirmPwd.value) {
            alert('비밀번호가 일치하지 않습니다.');
            confirmPwd.focus();
            return;
        }

        // 폼 데이터 생성
        const formData = new FormData(signupForm);

        // 서버로 데이터 전송
        fetch(signupForm.action, {
            method: 'POST',
            body: formData
        })
            .then(response => {
                if (response.ok) {
                    return response.text();
                } else {
                    return response.text().then(errorMessage => {
                        throw new Error(errorMessage);
                    });
                }
            })
            .then(message => {
                alert(message);
                window.location.href = '/login';
            })
            .catch(error => {
                console.error('Error:', error);
                alert(error.message);
            });
    });
});
