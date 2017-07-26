/**
 * Created by lxl on
 */

$('.owl-dot').bind('click', function () {
    $(this).addClass('active').siblings().removeClass('active');
    var i = $(this).index();
    $('.owl-item').eq(i).addClass('active').siblings().removeClass('active');
});

$(function () {
    $('#scrollViewBtn li').eq(0).addClass('active');
    $('#scrollViewContent div').eq(0).addClass('active');
    $("#myCarousel").carousel('cycle');
    initRecommend();
    init($('#category1'), 1, -1);
    init($('#category2'), 2, -1);
    init($('#category3'), 0, 1);
});
function initRecommend(dom, categoryId, isvip) {
    $.ajax({
        url: '/books/recommends/data',
        type: "GET",
        data: {},
        success: function (data) {
            console.info(data);
            var html = '';
            var i = 0;
            $.each(data.data, function (n, value) {

                if (i % 4 == 0) {
                    i == 0;
                    html = html + getHeadUl();
                }
                i = i + 1;
                html = html + getContent(value);
            });
            html = html + '</div></div></ul>';
            $('#recommend1').empty().append(html);
            return true;
        }
    });
}
function init(dom, categoryId, isvip) {
    $.ajax({
        url: '/books/categories/' + categoryId + '/data',
        type: "GET",
        data: {
            "pageindex": 0,
            "isvip": isvip,
            "length": 4
        },
        success: function (data) {
            console.info(data);
            var html = '';
            var i = 0;
            $.each(data.data, function (n, value) {

                if (i % 4 == 0) {
                    i == 0;
                    html = html + getHeadUl();
                }
                i = i + 1;
                html = html + getContent(value);
            });
            html = html + '</div></div></ul>';
            dom.empty().append(html);
            return true;
        }
    });
}

function getHeadUl() {
    return '</div></div></ul><ul class="products owl-carousel owl-theme owl-responsive-1000 owl-loaded" style="margin-top: 15px;"><div class="owl-stage-outer"><div class="owl-stage" style="transform: translate3d(0px, 0px, 0px); transition: all 0s ease 0s; width: 1740px;">';
}
function getContent(value) {
    var html = value.isVIP == 1 ? '<div class="price"><img src="/web/img/bookvip.png" style="height: 21px;width: 39px;"></div>' : '';
    return '<div class="owl-item active" style="width: 270px; margin-right: 20px;" onclick="bookDetail(\'' + value.bookEncodeId + '\')"><li class="first last post-31 product type-product status-publish has-post-thumbnail product_cat-audiobooks product_cat-books shipping-taxable purchasable product-type-simple product-cat-audiobooks product-cat-books instock">'
        + '<div class="product-wrapper padding30"><div class="product-thumb"><a href="#"><img onerror="this.onearror=null;this.src=\'/web/img/booknocover.png\'" src="' + value.cover + '" class="attachment-shop_catalog size-shop_catalog wp-post-image" alt="product-4" width="220" height="290"> </a> </div><div class="sale-flash-wrapper"> </div><div class="price-wrapper">'
        + html + '</div> </div><a href="#" style="text-align: center;max-width: 200px;margin: 0 auto;"><h4>' + value.bookName + '</h4></a></li></div>';
}