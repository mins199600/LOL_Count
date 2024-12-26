document.addEventListener('DOMContentLoaded', () => {
    console.log('공지사항 게시판이 로드되었습니다.');

    // 글쓰기 버튼 클릭 이벤트
    const noticeWriteButton = document.getElementById('noticeWriteButton');
    if (noticeWriteButton) {
        noticeWriteButton.onclick = () => {
            window.location.href = '/board/noticeWrite'; // 글쓰기 페이지로 이동
        };
    }

    // 검색 버튼 이벤트 추가
    const searchButton = document.getElementById('searchButton');
    if (searchButton) {
        searchButton.addEventListener('click', () => {
            const keyword = document.getElementById('searchInput').value;
            window.location.href = `/board/notice?keyword=${encodeURIComponent(keyword)}`;
        });
    }

    // 공지사항 목록의 각 항목에 클릭 이벤트 추가
    const noticeItems = document.querySelectorAll('.notice-item');
    noticeItems.forEach((item) => {
        const noticeId = item.getAttribute('data-id');
        // 항목 클릭 시 상세 페이지로 이동
        item.onclick = () => {
            window.location.href = `/noticeDetail?id=${noticeId}`;
        };
    });

    // 전체 선택 체크박스 클릭 이벤트
    const selectAllCheckbox = document.getElementById('selectAllCheckbox'); //전체 체크 박스 선택
    const checkboxes = document.querySelectorAll('.notice-checkbox[data-id]'); // 개별 체크박스 선택
    if (selectAllCheckbox) {
        selectAllCheckbox.addEventListener('change', (e) => {
            checkboxes.forEach((checkbox) => {
                checkbox.checked = e.target.checked;
            });
        });
    }

    // 삭제 버튼 클릭 이벤트
    const deleteButton = document.getElementById('deleteAllButton');
    if (deleteButton) {
        deleteButton.addEventListener('click', () => {
            const isAllSelected = selectAllCheckbox && selectAllCheckbox.checked; // 전체 선택 여부 확인
            const selectedIds = Array.from(checkboxes)
                .filter((checkbox) => checkbox.checked)
                .map((checkbox) => checkbox.getAttribute('data-id'));

            // 요청 데이터 준비
            let requestData;
            if (isAllSelected) {
                requestData = { id: "all" }; // 전체 삭제 요청
            } else if (selectedIds.length > 0) {
                requestData = { id: selectedIds }; // 선택 삭제 요청
            } else {
                alert('삭제할 항목을 선택하세요.');
                return;
            }

            // Ajax 요청
            fetch('/deleteNotice', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(requestData),
            })
                .then((response) => {
                    if (response.ok) {
                        alert('삭제가 완료되었습니다.');
                        location.reload(); // 페이지 새로고침
                    } else {
                        alert('삭제 중 오류가 발생했습니다.');
                    }
                })
                .catch((error) => {
                    console.error('삭제 요청 실패:', error);
                    alert('삭제 요청 중 오류가 발생했습니다.');
                });
        });
    }

    // 필터 버튼 이벤트 추가
    const filterButton = document.getElementById('filterButton');
    if (filterButton) {
        filterButton.addEventListener('click', () => {
            const author = document.getElementById('filterInput').value;
            window.location.href = `/board/notice/filter?author=${author}`;
        });
    }
});