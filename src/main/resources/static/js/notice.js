ocument.addEventListener('DOMContentLoaded', () => {
    console.log('공지사항 게시판이 로드되었습니다.');

    // 글쓰기 버튼 클릭 이벤트
    const noticeWriteButton = document.getElementById('noticeWriteButton');
    if (noticeWriteButton) {
        noticeWriteButton.onclick = () => {
            window.location.href = '/board/notice-write'; // 글쓰기 페이지로 이동
        };
    }

    // 공지사항 목록의 각 항목에 클릭 이벤트 추가
    const noticeItems = document.querySelectorAll('.notice-item');
    noticeItems.forEach((item) => {
        const noticeId = item.getAttribute('data-id'); // data-id 속성 값 가져오기
        item.addEventListener('click', () => {
            abc(noticeId); // 클릭 시 abc 함수 호출
        });
    });
});

// abc 함수
function abc(a) {
    console.log('공지사항 ID:', a);
    window.location.href = `/noticeDetail?id=${a}`;
}