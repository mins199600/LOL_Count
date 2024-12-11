// 스크롤 애니메이션
document.addEventListener('DOMContentLoaded', function() {
    // 네비게이션 스무스 스크롤
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function (e) {
            e.preventDefault();
            const target = document.querySelector(this.getAttribute('href'));
            if (target) {
                target.scrollIntoView({
                    behavior: 'smooth',
                    block: 'start'
                });
            }
        });
    });

    // 챔피언 카드 lazy 로딩
    const observerOptions = {
        root: null,
        rootMargin: '0px',
        threshold: 0.1
    };

    const observer = new IntersectionObserver((entries, observer) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('fade-in');
                observer.unobserve(entry.target);
            }
        });
    }, observerOptions);

    // 챔피언 카드에 페이드인 효과 적용
    document.querySelectorAll('.champion-card').forEach(card => {
        observer.observe(card);
    });

    // 덱 필터링 기능
    const searchDeck = (searchTerm) => {
        const deckCards = document.querySelectorAll('.deck-card');
        deckCards.forEach(card => {
            const deckName = card.querySelector('h3').textContent.toLowerCase();
            const traits = Array.from(card.querySelectorAll('.trait-tag'))
                .map(trait => trait.textContent.toLowerCase());

            const isMatch = deckName.includes(searchTerm.toLowerCase()) ||
                traits.some(trait => trait.includes(searchTerm.toLowerCase()));

            card.style.display = isMatch ? 'block' : 'none';
        });
    };

    // 동적 헤더 스크롤 효과
    let lastScroll = 0;
    const header = document.querySelector('.header');

    window.addEventListener('scroll', () => {
        const currentScroll = window.pageYOffset;

        if (currentScroll <= 0) {
            header.classList.remove('scroll-up');
            return;
        }

        if (currentScroll > lastScroll && !header.classList.contains('scroll-down')) {
            // 아래로 스크롤
            header.classList.remove('scroll-up');
            header.classList.add('scroll-down');
        } else if (currentScroll < lastScroll && header.classList.contains('scroll-down')) {
            // 위로 스크롤
            header.classList.remove('scroll-down');
            header.classList.add('scroll-up');
        }
        lastScroll = currentScroll;
    });
});

// 챔피언 정보 툴팁
const createTooltip = (element, content) => {
    const tooltip = document.createElement('div');
    tooltip.className = 'tooltip';
    tooltip.textContent = content;

    element.addEventListener('mouseenter', (e) => {
        document.body.appendChild(tooltip);
        const rect = element.getBoundingClientRect();
        tooltip.style.top = `${rect.top - tooltip.offsetHeight - 10}px`;
        tooltip.style.left = `${rect.left + (rect.width/2) - (tooltip.offsetWidth/2)}px`;
    });

    element.addEventListener('mouseleave', () => {
        if (tooltip.parentNode) {
            tooltip.parentNode.removeChild(tooltip);
        }
    });
};

// 챔피언 카드에 툴팁 적용
document.querySelectorAll('.champion-card').forEach(card => {
    const championName = card.querySelector('h3').textContent;
    createTooltip(card, `${championName}의 상세 정보를 보려면 클릭하세요`);
});