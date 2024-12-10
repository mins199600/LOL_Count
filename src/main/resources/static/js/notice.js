document.addEventListener('DOMContentLoaded', () => {
    console.log('공지사항 게시판이 로드되었습니다.');

    // 글쓰기 버튼 클릭 이벤트
    const noticeWriteButton = document.getElementById('noticeWriteButton');
    if (noticeWriteButton) {
        noticeWriteButton.onclick = () => {
            window.location.href = '/board/noticeWrite'; // 글쓰기 페이지로 이동
        };
    }

    // 공지사항 목록의 각 항목에 클릭 이벤트 추가
    const noticeItems = document.querySelectorAll('.notice-item');
    noticeItems.forEach((item) => {
        const noticeId = item.getAttribute('data-id'); // data-id 속성 값 가져오기

        // 항목 클릭 시 상세 페이지로 이동
        item.onclick = () => {
            window.location.href = `/noticeDetail?id=${noticeId}`;
        };
    });

    // 공지사항 삭제를 위한 체크박스 이벤트 추가
    const checkboxes = document.querySelectorAll('.notice-checkbox'); // 체크박스 선택
    checkboxes.forEach((checkbox) => {
        checkbox.addEventListener('click', (event) => {
            event.stopPropagation(); // 클릭 이벤트가 부모로 전파되는 것을 막음

            const noticeId = checkbox.getAttribute('data-id'); // 체크박스의 data-id 가져오기
            if (confirm('정말 삭제하시겠습니까?')) {
                // POST 요청 전송 (삭제 요청)
                fetch('/deleteNotice', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                    },
                    body: `id=${noticeId}` // POST 요청에 데이터 전달
                }).then((response) => {
                    if (response.ok) {
                        alert('삭제되었습니다.');
                        location.reload(); // 페이지 새로고침
                    } else {
                        alert('삭제에 실패했습니다.');
                    }
                }).catch((error) => {
                    console.error('삭제 요청 중 오류 발생:', error);
                    alert('삭제 요청 중 오류가 발생했습니다.');
                });
            }
        });
    });

    // 전체 삭제 버튼 클릭 이벤트
    const deleteAllButton = document.getElementById('deleteAllButton');
    if (deleteAllButton) {
        deleteAllButton.addEventListener('click', () => {
            if (confirm('정말 모든 공지사항을 삭제하시겠습니까?')) {
                // POST 요청 전송 (전체 삭제)
                fetch('/deleteAllNotices', {
                    method: 'POST',
                }).then((response) => {
                    if (response.ok) {
                        alert('모든 공지사항이 삭제되었습니다.');
                        location.reload(); // 페이지 새로고침
                    } else {
                        alert('전체 삭제에 실패했습니다.');
                    }
                }).catch((error) => {
                    console.error('전체 삭제 요청 중 오류 발생:', error);
                    alert('전체 삭제 요청 중 오류가 발생했습니다.');
                });
            }
        });
    }
});