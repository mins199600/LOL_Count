document.addEventListener('DOMContentLoaded', function() {
    // 페이지가 로드되면 추천 덱을 불러오고 모달 관련 이벤트를 초기화
    loadRecommendedDecks();

    const modal = document.getElementById('modal'); // 모달 요소
    const closeModal = document.getElementsByClassName('close')[0]; // 모달 닫기 버튼 가져오기

    // 모달 닫기 버튼 클릭 시 모달 닫기
    closeModal.onclick = function() {
        modal.style.display = 'none';
    };

    // 모달 외부를 클릭했을 때 모달 닫기
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = 'none';
        }
    };
});

// 추천 덱을 불러오는 비동기 함수
const loadRecommendedDecks = async () => {
    try {
        // 추천 덱 API 호출
        const response = await fetch('/api/decks/recommend');
        const decks = await response.json(); // API 응답을 JSON으로 변환

        const deckListElement = document.getElementById('deckList'); // 덱 리스트를 표시할 요소 가져오기
        deckListElement.innerHTML = ''; // 기존 덱 리스트 초기화

        // 각 덱을 순회하며 HTML 요소 생성
        decks.forEach((deck) => {
            const deckCard = document.createElement('div'); // 덱 카드 컨테이너 생성
            deckCard.className = 'deckCard fade-in'; // 클래스 추가

            const deckName = document.createElement('h3'); // 덱 이름 요소 생성
            deckName.textContent = deck.deckName; // 덱 이름 설정
            deckCard.appendChild(deckName); // 덱 카드에 덱 이름 추가

            const championsContainer = document.createElement('div'); // 챔피언 컨테이너 생성
            championsContainer.className = 'deck-champions'; // 클래스 추가

            // 챔피언 이름 목록을 분리하여 순회
            const championNames = deck.championNames.split(',').map(name => name.trim());
            championNames.forEach(championName => {
                const championDiv = document.createElement('div'); // 챔피언 개별 컨테이너 생성
                championDiv.className = 'deck-champion'; // 클래스 추가

                const championImg = document.createElement('img'); // 챔피언 이미지 생성
                championImg.src = `https://cdn.dak.gg/tft/images2/sets/set13/portraits/${championName}.jpg`; // 이미지 경로 설정
                championImg.alt = championName; // 이미지 대체 텍스트 설정

                // 이미지 로드 실패 시 기본 이미지로 변경
                championImg.onerror = function() {
                    this.src = 'https://cdn.dak.gg/tft/images2/sets/set13/portraits/default.jpg';
                };

                const championNameSpan = document.createElement('span'); // 챔피언 이름 텍스트 생성
                championNameSpan.textContent = championName; // 챔피언 이름 설정

                championDiv.appendChild(championImg); // 챔피언 이미지 추가
                championDiv.appendChild(championNameSpan); // 챔피언 이름 추가
                championsContainer.appendChild(championDiv); // 챔피언 컨테이너에 추가
            });

            deckCard.appendChild(championsContainer); // 덱 카드에 챔피언 컨테이너 추가
            deckListElement.appendChild(deckCard); // 덱 리스트에 덱 카드 추가

            // 덱 카드 클릭 이벤트 추가
            deckCard.onclick = function() {
                // 모달에 덱 이름과 설명 표시
                document.getElementById('modalDeckName').textContent = deck.deckName;
                document.getElementById('modalDeckDescription').textContent = deck.description;

                // 덱 아이템이 있는 경우 모달에 아이템 추가
                if (deck.items) {
                    deck.items.forEach(item => {
                        const itemDiv = document.createElement('div'); // 아이템 컨테이너
                        itemDiv.className = 'item'; // 클래스 추가

                        const itemImg = document.createElement('img'); // 아이템 이미지
                        itemImg.src = item.imageUrl; // 이미지 경로 설정
                        itemImg.alt = item.name; // 이미지 대체 텍스트 설정

                        const itemNameSpan = document.createElement('span'); // 아이템 이름 텍스트

                        itemDiv.appendChild(itemImg); // 아이템 이미지 추가
                        itemDiv.appendChild(itemNameSpan); // 아이템 이름 추가
                        document.getElementById('modalItems').appendChild(itemDiv); // 모달에 아이템
                    });
                }

                modal.style.display = 'block'; // 모달 표시
            };
        });
    } catch (error) {
        // API 호출 실패 시 에러 로그 출력
        console.error('Error loading recommended decks:', error);
    }
};