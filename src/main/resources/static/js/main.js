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
            deckCard.className = 'deckCard';

            // 덱 이름 추가
            const deckName = document.createElement('h3');
            deckName.textContent = deck.deckName;
            deckCard.appendChild(deckName);

            // 덱 특성 추가
            const deckTraits = document.createElement('div');
            deckTraits.className = 'deck-traits';

            const traits = deck.championNames.split(','); // 특성이 쉼표로 구분된다고 가정
            traits.forEach((trait) => {
                const traitTag = document.createElement('span');
                traitTag.className = 'trait-tag';
                traitTag.textContent = trait.trim(); // 여분의 공백 제거
                deckTraits.appendChild(traitTag);
            });

            deckCard.appendChild(deckTraits);

            // 덱의 특정 챔피언 추가
            const deckChamps = document.createElement('div');
            deckChamps.className = 'deck-champs';

            traits.forEach((champ) => {
                const champDiv = document.createElement('div');
                champDiv.className = 'champ-div';

                const champImg = document.createElement('img');
                champImg.src = `https://cdn.dak.gg/tft/images2/sets/set13/portraits/${champ.trim()}.jpg`; // 실제 이미지 URL로 대체
                champImg.alt = champ.trim();
                champDiv.appendChild(champImg);

                const champName = document.createElement('span');
                champName.textContent = champ.trim();
                champDiv.appendChild(champName);

                deckChamps.appendChild(champDiv);
            });

            deckCard.appendChild(deckChamps);

            deckListElement.appendChild(deckCard);
        });
    } catch (error) {
        console.error('Error loading recommended decks:', error);
    }
};
