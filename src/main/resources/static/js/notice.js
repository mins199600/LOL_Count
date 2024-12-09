document.addEventListener('DOMContentLoaded', () => {
    console.log('공지사항 게시판이 로드되었습니다.');

    document.getElementById('noticeWriteButton').onclick = () => {
        window.location.href = '/board/notice-write'; // 이동할 페이지의 경로를 입력하세요
    };
});