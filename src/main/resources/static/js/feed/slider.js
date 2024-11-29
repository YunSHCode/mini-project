window.addEventListener('DOMContentLoaded', () => {
    const navPosition = document.getElementById('nav_position');
    let lastScrollY = window.scrollY;


    window.addEventListener('scroll', () => {
        const currentScrollY = window.scrollY;

        if (currentScrollY > lastScrollY) {
            // 스크롤 내리면 헤더 숨기기
            navPosition.style.transform = "translateY(-100%)";
        } else {
            // 스크롤 올리면 헤더 보이기
            console.log("test2")
            navPosition.style.transform = "translateY(0)";
        }

        lastScrollY = currentScrollY;
    });
});
