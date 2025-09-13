
    // 폼 제출 처리
    document.getElementById('loginForm').addEventListener('submit', function(e) {
    e.preventDefault(); // 기본 폼 제출 방지

    const gameName = document.getElementById('gameName').value;
    const tagLine = document.getElementById('tagLine').value;
    const errorMessage = document.getElementById('errorMessage');

    // AJAX 요청 생성
    const xhr = new XMLHttpRequest();
    xhr.open('POST', '/login', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    xhr.onload = function() {
    if (xhr.status === 200) {
    try {
    const response = JSON.parse(xhr.responseText);
    if (response.success) {
    // 로그인 성공 시 리다이렉트
    window.location.href = '/login/login_success';
} else {
    // 로그인 실패 시 에러 메시지 표시
    errorMessage.textContent = response.message || '아이디와 비밀번호를 확인해주세요.';
    errorMessage.style.display = 'block';
}
} catch (e) {
    console.error('응답 파싱 오류:', e);
    errorMessage.textContent = '로그인 처리 중 오류가 발생했습니다.';
    errorMessage.style.display = 'block';
}
} else {
    errorMessage.textContent = '서버 오류가 발생했습니다.';
    errorMessage.style.display = 'block';
}
};

    xhr.onerror = function() {
    errorMessage.textContent = '네트워크 오류가 발생했습니다.';
    errorMessage.style.display = 'block';
};

    // 데이터 전송
    xhr.send('gameName=' + encodeURIComponent(gameName) + '&tagLine=' + encodeURIComponent(tagLine));
});
