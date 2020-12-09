var ovf, slider;

$(function(){
    ovf = this.getElementById("overflow");
    slider = this.getElementById("slider");
    winResize();
    $(window).bind({resize: winResize, scroll: winScroll});
});

function winResize(){   
    ovf.style.top = slider.offsetHeight + "px";
}

function winScroll(){
    var op = 1 - (window.pageYOffset / slider.offsetHeight);
    slider.style.opacity = op; 
}
