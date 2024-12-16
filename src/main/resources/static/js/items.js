// 공격력 아이템
function getAttackItem() {
    fetchItems("PhysicalWeapon");
}

// 주문력 아이템
function getMagicItem() {
    fetchItems("MagicItem");
}

// 탱커 아이템
function getTankItem() {
    fetchItems("ShieldItem");
}

// 공통 함수: REST API 호출
function fetchItems(type) {
    fetch(`/api/items/${type}`) // REST API 호출
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to fetch data");
            }
            return response.json();
        })
        .then(data => {
            displayItems(data); // 데이터를 화면에 표시
        })
        .catch(error => {
            console.error("Error fetching data:", error);
        });
}

// 데이터를 화면에 표시
function displayItems(items) {
    const displayElement = document.getElementById("queryDisplay");

    // 데이터를 HTML로 변환하여 표시
    const queries = items.map(item =>
        `INSERT INTO crafted_material_mapping(crafted_id, material_id_1, material_id_2) VALUES (${item.craftedId}, ${item.materialId1}, ${item.materialId2});`
    ).join("\n");

    displayElement.textContent = queries;
    displayElement.style.whiteSpace = "pre-wrap";
    displayElement.style.marginTop = "20px";
    displayElement.style.padding = "10px";
    displayElement.style.border = "1px solid #ccc";
    displayElement.style.backgroundColor = "#f9f9f9";
}