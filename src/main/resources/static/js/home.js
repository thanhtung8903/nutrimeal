const listImage = document.querySelector(".list-image");
const imgs = document.getElementsByClassName("image-with-text");
const length = imgs.length;
const btnleft = document.querySelector(".btn-left");
const btnright = document.querySelector(".btn-right");
let current = 0;

const handleChangesSlide = () => {
    if (current == length - 1) {
        current = 0;
        let width = imgs[0].offsetWidth;
        listImage.style.transform = `translateX(0px)`;
        document.querySelector(".active").classList.remove("active");
        document.querySelector(".index-items-" + current).classList.add("active");
    } else {
        current++;
        let width = imgs[0].offsetWidth;
        listImage.style.transform = `translateX(${width * -1 * current}px)`;
        document.querySelector(".active").classList.remove("active");
        document.querySelector(".index-items-" + current).classList.add("active");
    }
};
let handleEventChangeSlide = setInterval(handleChangesSlide, 4000);

btnright.addEventListener("click", () => {
    clearInterval(handleEventChangeSlide);
    handleChangesSlide();
    handleEventChangeSlide = setInterval(handleChangesSlide, 4000);
});

btnleft.addEventListener("click", () => {
    clearInterval(handleEventChangeSlide);
    if (current == 0) {
        current = length - 1;
        let width = imgs[0].offsetWidth;
        listImage.style.transform = `translateX(${width * -1 * current}px)`;
        document.querySelector(".active").classList.remove("active");
        document.querySelector(".index-items-" + current).classList.add("active");
    } else {
        current--;
        let width = imgs[0].offsetWidth;
        listImage.style.transform = `translateX(${width * -1 * current}px)`;
        document.querySelector(".active").classList.remove("active");
        document.querySelector(".index-items-" + current).classList.add("active");
    }
    handleEventChangeSlide = setInterval(handleChangesSlide, 4000);
});
