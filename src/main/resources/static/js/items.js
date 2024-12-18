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

// 데이터를 화면에 이미지로 표시
function displayItems(items) {
    const displayElement = document.getElementById("queryDisplay");
    displayElement.innerHTML = ""; // 기존 내용 초기화

    items.forEach(item => {
        // 완성 아이템 이미지
        const craftedItemDiv = document.createElement("div");
        craftedItemDiv.className = "crafted-item";

        const craftedImage = document.createElement("img");
        craftedImage.src = item.craftedImageUrl;
        craftedImage.alt = item.craftedName;
        craftedItemDiv.appendChild(craftedImage);

        const craftedName = document.createElement("p");
        craftedName.textContent = item.craftedName;
        craftedItemDiv.appendChild(craftedName);

        // 재료 아이템 이미지
        const materialsDiv = document.createElement("div");
        materialsDiv.className = "materials";

        const materialImage1 = document.createElement("img");
        materialImage1.src = item.materialImageUrl1;
        materialImage1.alt = item.materialName1;
        materialsDiv.appendChild(materialImage1);

        const materialImage2 = document.createElement("img");
        materialImage2.src = item.materialImageUrl2;
        materialImage2.alt = item.materialName2;
        materialsDiv.appendChild(materialImage2);

        // 아이템과 재료를 묶어서 추가
        const itemContainer = document.createElement("div");
        itemContainer.className = "item-container";
        itemContainer.appendChild(craftedItemDiv);
        itemContainer.appendChild(materialsDiv);

        displayElement.appendChild(itemContainer);
    });
}
