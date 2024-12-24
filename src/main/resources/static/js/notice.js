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
        const noticeId = item.getAttribute('data-id'); // data-id 속성 값 가져오기

        // 항목 클릭 시 상세 페이지로 이동
        item.onclick = () => {
            window.location.href = `/noticeDetail?id=${noticeId}`;
        };
    });
    //삭제 버튼 클릭 이벤트
    var check_arr = []; //checkbox 클릭시 배열값을 담는 역활
    const deleteAllButton = document.getElementById('deleteAllButton');
    if (deleteAllButton) {
        deleteAllButton.addEventListener('click', () => {
            var ea = document.getElementsByClassName("notice-checkbox");
            var cks = 0
            var counts = 0;
            while(cks < ea.length){
                if(ea[cks].checked == true){
                    check_arr[counts] = ea[cks].getAttribute("dataId");
                    counts++;
                }
                cks++;
            }
            console.log(check_arr);

            if (confirm('정말 모든 공지사항을 삭제하시겠습니까?')) {
                // POST 요청 전송 (개별삭제)
                fetch('/deleteAllNotices?checkBoxData', {
                    method: 'POST',
                    body:JSON.stringify(check_arr)
                }).then((response) => {
                    if (response.ok) {
                        alert('삭제되었습니다.');
                        location.reload(); // 페이지 새로고침
                    } else {
                        alert('전체 삭제에 실패했습니다.');
                    }
                }).catch((error) => {
                    console.error('전체 삭제 요청 중 오류 발생:', error);
                    alert('전체 삭제 요청 중 오류가 발생했습니다.');
                })
            }
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