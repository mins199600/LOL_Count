document.addEventListener('DOMContentLoaded', function() {
    loadRecommendedDecks();

    const modal = document.getElementById('modal');
    const closeModal = document.getElementsByClassName('close')[0];

    // 모달 닫기
    closeModal.onclick = function() {
        modal.style.display = 'none';
    }

    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = 'none';
        }
    }
});

const loadRecommendedDecks = async () => {
    try {
        const response = await fetch('/api/decks/recommend');
        const decks = await response.json();

        const deckListElement = document.getElementById('deckList');
        deckListElement.innerHTML = '';

        decks.forEach((deck) => {
            const deckCard = document.createElement('div');
            deckCard.className = 'deckCard fade-in';

            const deckName = document.createElement('h3');
            deckName.textContent = deck.deckName;
            deckCard.appendChild(deckName);

            const championsContainer = document.createElement('div');
            championsContainer.className = 'deck-champions';

            const championNames = deck.championNames.split(',').map(name => name.trim());

            championNames.forEach(championName => {
                const championDiv = document.createElement('div');
                championDiv.className = 'deck-champion';

                const championImg = document.createElement('img');
                championImg.src = `https://cdn.dak.gg/tft/images2/sets/set13/portraits/${championName}.jpg`;
                championImg.alt = championName;
                championImg.onerror = function() {
                    this.src = 'https://cdn.dak.gg/tft/images2/sets/set13/portraits/default.jpg';
                };

                const championNameSpan = document.createElement('span');
                championNameSpan.textContent = championName;

                championDiv.appendChild(championImg);
                championDiv.appendChild(championNameSpan);
                championsContainer.appendChild(championDiv);
            });

            deckCard.appendChild(championsContainer);
            deckListElement.appendChild(deckCard);

            // 덱 카드 클릭 이벤트 추가
            deckCard.onclick = function() {
                document.getElementById('modalDeckName').textContent = deck.deckName;
                document.getElementById('modalDeckDescription').textContent = deck.description;

                if (deck.items) {
                    deck.items.forEach(item => {
                        const itemDiv = document.createElement('div');
                        itemDiv.className = 'item';

                        const itemImg = document.createElement('img');
                        itemImg.src = item.imageUrl;
                        itemImg.alt = item.name;

                        const itemNameSpan = document.createElement('span');
                        itemNameSpan.textContent = item.name;

                        itemDiv.appendChild(itemImg);
                        itemDiv.appendChild(itemNameSpan);
                        document.getElementById('modalItems').appendChild(itemDiv);
                    });
                }

                modal.style.display = 'block';
            }
        });
    } catch (error) {
        console.error('Error loading recommended decks:', error);
    }
};
