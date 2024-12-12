document.addEventListener('DOMContentLoaded', function() {
    loadRecommendedDecks();
});

const loadRecommendedDecks = async () => {
    try {
        const response = await fetch('/api/decks/recommend');
        const decks = await response.json();

        const deckListElement = document.getElementById('deckList');
        deckListElement.innerHTML = ''; // 기존 내용 지우기

        decks.forEach((deck) => {
            const deckCard = document.createElement('div');
            deckCard.className = 'deckCard fade-in';

            // 덱 이름 추가
            const deckName = document.createElement('h3');
            deckName.textContent = deck.deckName;
            deckCard.appendChild(deckName);

            // 챔피언 목록 컨테이너
            const championsContainer = document.createElement('div');
            championsContainer.className = 'deck-champions';

            // 챔피언 이름 배열로 변환
            const championNames = deck.championNames.split(',').map(name => name.trim());

            // 각 챔피언에 대한 요소 생성
            championNames.forEach(championName => {
                const championDiv = document.createElement('div');
                championDiv.className = 'deck-champion';

                const championImg = document.createElement('img');
                championImg.src = `https://cdn.dak.gg/tft/images2/sets/set13/portraits/${championName}.jpg`;
                championImg.alt = championName;
                championImg.onerror = function() {
                    this.src = 'https://cdn.dak.gg/tft/images2/sets/set13/portraits/default.jpg'; // 기본 이미지
                };

                const championNameSpan = document.createElement('span');
                championNameSpan.textContent = championName;

                championDiv.appendChild(championImg);
                championDiv.appendChild(championNameSpan);
                championsContainer.appendChild(championDiv);
            });

            deckCard.appendChild(championsContainer);
            deckListElement.appendChild(deckCard);
        });
    } catch (error) {
        console.error('Error loading recommended decks:', error);
    }
};

