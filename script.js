// 모든 th:name="1cost" 요소를 선택
const oneCostElements = document.querySelectorAll('[th\\:name="1cost"]');

// 슬라이드 인 애니메이션 적용
function slideInElements() {
    oneCostElements.forEach(element => {
        element.classList.remove('slide-out'); // 이전 애니메이션 제거
        element.classList.add('slide-in');    // 슬라이드 인 추가
    });
}

// 슬라이드 아웃 애니메이션 적용
function slideOutElements() {
    oneCostElements.forEach(element => {
        element.classList.remove('slide-in'); // 이전 애니메이션 제거
        element.classList.add('slide-out');   // 슬라이드 아웃 추가
    });
}

// 테스트용: 3초 후 슬라이드 인, 6초 후 슬라이드 아웃
setTimeout(slideInElements, 3000); // 3초 후 슬라이드 인
setTimeout(slideOutElements, 6000); // 6초 후 슬라이드 아웃